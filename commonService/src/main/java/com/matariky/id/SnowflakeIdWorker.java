package com.matariky.id;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.matariky.commonservice.upload.utils.SpringContextUtils;

public class SnowflakeIdWorker {

    private static final IdentifierGenerator generator = SpringContextUtils.getBean(SnowflakeIdGenerator.class);

    public static long getId() {
        return (long) generator.nextId(null);
    }

    private SnowflakeIdWorker() {

    }
}
