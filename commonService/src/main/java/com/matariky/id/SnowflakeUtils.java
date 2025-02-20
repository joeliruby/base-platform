package com.matariky.id;

public class SnowflakeUtils {
	/**
	 * 起始的 Time 戳
	 */
	private final static long START_STMP = 1567267200000L;// 2019-09-01 00:00:00

	/**
	 * 每一部分占用的位数
	 */
	private final static long SEQUENCE_BIT = 12; // Serial Number占用的位数
	private final static long MACHINE_BIT = 8; // 机器标识占用的位数 ,256个机器
	private final static long DATACENTER_BIT = 2;// Data 中心占用的位数 ,4个 Data 中心

	/**
	 * 每一部分的最大值
	 */
	private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
	private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
	private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

	/**
	 * 每一部分向左的位移
	 */
	private final static long MACHINE_LEFT = SEQUENCE_BIT;
	private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
	private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

	private long datacenterId; // Data 中心
	private long machineId; // 机器标识
	private long sequence = 0L; // Serial Number
	private long lastStmp = -1L;// Previous 次 Time 戳

	public SnowflakeUtils(long datacenterId, long machineId) {
		if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
			throw new IllegalArgumentException(
					"datacenterId can't be greater than " + MAX_DATACENTER_NUM + " or less than 0");
		}
		if (machineId > MAX_MACHINE_NUM || machineId < 0) {
			throw new IllegalArgumentException(
					"machineId can't be greater than " + MAX_MACHINE_NUM + " or less than 0");
		}
		this.datacenterId = datacenterId;
		this.machineId = machineId;
	}

	/**
	 * 产生下 one ID
	 *
	 * @return
	 */
	public synchronized long nextId() {
		long currStmp = getNewstmp();
		if (currStmp < lastStmp) {
			throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
		}

		if (currStmp == lastStmp) {
			// 相同毫秒内 ,Serial Number Incremental
			sequence = (sequence + 1) & MAX_SEQUENCE;
			// 同一毫秒的序列数已经达到最大
			if (sequence == 0L) {
				// 循环 Retrieve多几次 ,尽可能地避免不可能的可能
				for (int i = 0; i < 100; i++) {
					currStmp = getNextMill();
					if (currStmp != lastStmp) {
						break;
					}
				}
			}
		} else {
			// 不同毫秒内 ,Serial Number置为0
			sequence = 0L;
		}

		lastStmp = currStmp;

		return (currStmp - START_STMP) << TIMESTMP_LEFT // Time 戳部分
				| datacenterId << DATACENTER_LEFT // Data 中心部分
				| machineId << MACHINE_LEFT // 机器标识部分
				| sequence; // Serial Number部分
	}

	private long getNextMill() {
		long mill = getNewstmp();
		while (mill <= lastStmp) {
			mill = getNewstmp();
		}
		return mill;
	}

	public long getNewstmp() {
		return System.currentTimeMillis();
	}

	public static long getId() {
		SnowflakeUtils idGenerator = new SnowflakeUtils(1, 1);
		return idGenerator.nextId();
	}

}
