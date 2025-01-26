package com.matariky.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.RandomUtil;
import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.constant.RedisKey;
import com.matariky.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * @description: 编号工具类
 * @author: bo.chen
 * @create: 2023/9/24 19:03
 **/
public class NoUtil implements Serializable {

    /**
     * @Description: Generate outbound and inbound serial numbers
     * @Author: bo.chen
     * @Date: 2023/9/5 16:37
     * @param type
     * @return java.lang.String
     **/
    public static String generateInoutSerialNo(int type) {
        return generateNo(type == 1 ? "TSI" : "TSO", type == 1 ? "tape_stock_in" : "tape_stock_out", 9, Boolean.FALSE);
    }

    /**
     * @Description: 生产盘点序列号
     * @Author: bo.chen
     * @Date: 2023/9/13 18:21
     * @return java.lang.String
     **/
    public static String generateInventorySerialNo() {
        return generateNo("TIN", "tape_inventory", 9, Boolean.FALSE);
    }

    /**
     * @Description: Generate Universal Number
     * @Author: bo.chen
     * @Date: 2023/4/27 11:11
     * @param prefix
     * @param  bizName
     * @param minSeqLen
     * @return java.lang.String
     **/
    private static String generateNo(String prefix, String bizName, int minSeqLen, boolean isRandom) {
        RedisUtils redisUtils =  SpringContextUtils.getBean(RedisUtils.class);
        String dateStr = LocalDate.now().format(DatePattern.PURE_DATE_FORMATTER);
        String redisKey = String.format(RedisKey.COMMON_NO_SEQ, bizName, dateStr);
        Long seq = redisUtils.increment(redisKey);
        if (seq == NumberUtils.LONG_ONE) {
            /** Set expiration time of one day **/
            redisUtils.expire(redisKey, 1, TimeUnit.DAYS);
        }
        String seqStr = seq.toString();
        if (seqStr.length() < minSeqLen) {
            seqStr = getZeroStr(minSeqLen - seqStr.length()) + seqStr;
        }
        /** prefix + YYYYMMDD + sequential number + 2-digit random number **/
        return prefix + dateStr + seqStr + (isRandom ? RandomUtil.randomNumbers(2) : "");
    }

    /**
     * @Description: 获取字符串0
     * @Author: bo.chen
     * @Date: 2023/3/24 19:15
     * @param len
     * @return java.lang.String
     **/
    private static String getZeroStr(int len) {
        StringBuffer buffer = new StringBuffer(len);
        for (int i = 0; i <len ; i++) {
            buffer.append("0");
        }
        return buffer.toString();
    }


    private NoUtil() {

    }
}
