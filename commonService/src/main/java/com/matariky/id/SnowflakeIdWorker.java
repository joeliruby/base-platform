package com.matariky.id;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.matariky.commonservice.upload.utils.SpringContextUtils;

/**
 * @description: 雪花算法id工作类
 * @author: bo.chen
 * @create: 2023/10/9 16:03
 **/
public class SnowflakeIdWorker {

    private static final IdentifierGenerator generator = SpringContextUtils.getBean(SnowflakeIdGenerator.class);

    public static long getId() {
        return (long) generator.nextId(null);
    }

    private SnowflakeIdWorker() {

    }
}
