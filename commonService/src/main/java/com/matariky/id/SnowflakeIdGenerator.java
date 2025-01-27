/**
 * Copyright (c) 2007 Beijing Shiji Kunlun Software Co., Ltd. All Rights Reserved.
 * $Id$
 */
package com.matariky.id;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class SnowflakeIdGenerator implements IdentifierGenerator {

    public static final int TOTAL_BITS = 1 << 6;

    // 2000-01-01T00:00:00Z / Sat, 01 Jan 2000 00:00:00 GMT
    // EpochSecond (Unix timestamps in seconds): 946684800L
    public static final LocalDateTime DEFAULT_BASE_DATE_TIME = LocalDateTime.of(2000, 1, 1, 0, 0, 0);

    /**
     * Bits for [sign-> second-> workId-> sequence]
     */
    protected int signBits = 1;

    // the start time, default is 2008-08-08T08:08:08.000 +08:00
    protected long baseEpochSeconds = 1218154088L;

    // delta seconds
    protected int timeBits = 32;

    // worker node id bits
    protected int workerBits = 16;

    // sequence bits
    protected int seqBits = 15;

    /** Volatile fields caused by nextId() */
    protected long sequence = 0L;

    protected long lastSecond = -1L;

    protected long maxDeltaSeconds;

    protected long maxWorkerId;

    protected long maxSequence;

    protected int timestampShift;

    protected int workerIdShift;

    protected long workerId;

    /**
     * Initialize a uid generator with specified settings.
     *
     * @param workerId
     *            specify an id for the worker, the worker is the app to generate uid
     * @param baseDateTime
     *            the base date the generator begin with
     * @param timeBits
     *            time bits length
     * @param workerBits
     *            worker id bits length
     * @param seqBits
     *            sequence bits length
     */
    public SnowflakeIdGenerator(long workerId, LocalDateTime baseDateTime, int timeBits, int workerBits, int seqBits) {
        LoggerFactory.getLogger(this.getClass()).info("SnowflakeUidGenerator workerId={}", workerId);

        this.workerId = workerId;

        this.baseEpochSeconds = baseDateTime.atOffset(ZoneOffset.UTC)
                .toInstant()
                .getEpochSecond();

        this.timeBits = timeBits;
        this.workerBits = workerBits;
        this.seqBits = seqBits;

        int allocateTotalBits = signBits + timeBits + workerBits + seqBits;

        if (allocateTotalBits != TOTAL_BITS) {
            throw new UidGenerateException("allocate not enough 64 bits");
        }

        // initialize max value
        this.maxDeltaSeconds = ~(-1L << timeBits);
        this.maxWorkerId = ~(-1L << workerBits);
        this.maxSequence = ~(-1L << seqBits);

        if (workerId > maxWorkerId) {
            throw new UidGenerateException(String.format("workerId exceed the max value %d", maxWorkerId));
        }

        // initialize shift
        this.timestampShift = workerBits + seqBits;
        this.workerIdShift = seqBits;
    }

    /**
     * Initialize a uid generator with specified settings.
     *
     * @param workerId
     *            specify an id for the worker, the worker is the app to generate uid
     * @param timeBits
     *            time bits length
     * @param workerBits
     *            worker id bits length
     * @param seqBits
     *            sequence bits length
     */
    public SnowflakeIdGenerator(long workerId, int timeBits, int workerBits, int seqBits) {

        this(workerId, DEFAULT_BASE_DATE_TIME, timeBits, workerBits, seqBits);
    }

    /**
     * Initialize a uid generator with specified settings.
     *
     * @param workerId
     *            specify an id for the worker, the worker is the app to generate uid
     */
    public SnowflakeIdGenerator(long workerId) {

        this(workerId, DEFAULT_BASE_DATE_TIME, 32, 16, 15);
    }

    public long getId() {

        try {
            return (long) nextId(null);
        } catch (Exception ex) {

            throw new UidGenerateException(ex);
        }
    }

    public String parseID(long uid) {

        // parse UID
        long sequence = (uid << (TOTAL_BITS - seqBits)) >>> (TOTAL_BITS - seqBits);
        long workerId = (uid << (timeBits + signBits)) >>> (TOTAL_BITS - workerBits);
        long deltaSeconds = uid >>> (workerBits + seqBits);

        Instant thatTime = Instant.ofEpochSecond(baseEpochSeconds + deltaSeconds);

        // format as string
        return String.format("{\"UID\":\"%d\",\"timestamp\":\"%s\",\"workerId\":\"%d\",\"sequence\":\"%d\"}",
                uid, thatTime.toString(), workerId, sequence);
    }

    /**
     * Get UID
     *
     * @return UID
     * @throws UidGenerateException
     *             in the case: Clock moved backwards; Exceeds the max timestamp
     */
    public synchronized Number nextId(Object entity) {

        long currentSecond = getCurrentSecond();

        // Clock moved backwards, refuse to generate uid
        if (currentSecond < lastSecond) {
            long refusedSeconds = lastSecond - currentSecond;
            throw new UidGenerateException("Clock moved backwards. Refusing to generate id for %d seconds", refusedSeconds);
        }

        // At the same second, increase sequence
        if (currentSecond == lastSecond) {
            sequence = (sequence + 1) & maxSequence;
            // Exceed the max sequence, we wait the next second to generate uid
            if (sequence == 0) {
                currentSecond = getNextSecond(lastSecond);
            }

            // At the different second, sequence restart from zero
        } else {
            sequence = 0L;
        }

        lastSecond = currentSecond;

        // Allocate bits for UID
        long deltaSeconds = currentSecond - baseEpochSeconds;

        return (deltaSeconds << timestampShift) | (workerId << workerIdShift) | sequence;
    }

    /**
     * Get next second.
     */
    private long getNextSecond(long lastTimestamp) {

        long timestamp = getCurrentSecond();
        while (timestamp <= lastTimestamp) {
            timestamp = getCurrentSecond();
        }

        return timestamp;
    }

    /**
     * Get current second
     */
    private long getCurrentSecond() {

        long currentEpochSecond = Instant.now().getEpochSecond();

        if (currentEpochSecond - baseEpochSeconds > maxDeltaSeconds) {
            throw new UidGenerateException("Timestamp bits is exhausted. Refusing UID generate. Now: " + currentEpochSecond);
        }

        return currentEpochSecond;
    }

    /**
     * Allocate bits for UID according to delta seconds & workerId & sequence<br>
     * <b>Note that: </b>The highest bit will always be 0 for sign
     *
     * @param deltaSeconds
     * @param workerId
     * @param sequence
     * @return
     */
    public long allocate(long deltaSeconds, long workerId, long sequence) {

        return (deltaSeconds << timestampShift) | (workerId << workerIdShift) | sequence;
    }

    public void setTimeBits(int timeBits) {

        if (timeBits > 0) {
            this.timeBits = timeBits;
        }
    }

    public void setWorkerBits(int workerBits) {

        if (workerBits > 0) {
            this.workerBits = workerBits;
        }
    }

    public void setSeqBits(int seqBits) {

        if (seqBits > 0) {
            this.seqBits = seqBits;
        }
    }

    /**
     * Get the worker id by using 16 bits of the local ip address
     */
    public static long getWorkerIdByIP() {

        return getWorkerIdByIP(16);
    }

    /**
     * Get the worker id by using the last x bits of the local ip address
     */
    public static long getWorkerIdByIP(int bits) {

        int shift = 64 - bits;

        String podIpAddress = Optional.ofNullable(System.getenv("MY_POD_IP"))
                .orElseGet(() -> {

                    InetAddress inetAddress;
                    try {
                        inetAddress = InetAddress.getLocalHost();
                        if (!inetAddress.isLoopbackAddress() && !inetAddress.isAnyLocalAddress()) {

                            return inetAddress.getHostAddress();
                        } else {
                            return "";
                        }
                    } catch (UnknownHostException ex) {
                        ex.printStackTrace();
                    }
                    return "";
                });

        long ip = ipToLong(podIpAddress);
        if (ip == 0) {
            ip = ThreadLocalRandom.current().nextLong();
        }

        return (ip << shift) >>> shift;
    }

    public static String longToIp(long ip) {

        return ((ip >> 24) & 0xFF) + "."
                + ((ip >> 16) & 0xFF) + "."
                + ((ip >> 8) & 0xFF) + "."
                + (ip & 0xFF);
    }

    public static long ipToLong(String ipAddress) {

        long result = 0;

        String[] ipAddressInArray = ipAddress.split("\\.");
        if (ipAddressInArray.length == 4) {
            for (int i = 3; i >= 0; i--) {

                long ip = Long.parseLong(ipAddressInArray[3 - i]);
                result |= ip << (i * 8);
            }
        }

        return result;
    }
}