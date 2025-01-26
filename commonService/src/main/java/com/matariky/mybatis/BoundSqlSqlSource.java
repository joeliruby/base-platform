package com.matariky.mybatis;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @description: BoundSqlSqlSource
 * @author: bo.chen
 * @create: 2023/9/6 16:36
 **/
public class BoundSqlSqlSource implements SqlSource {

    private BoundSql boundSql;

    public BoundSqlSqlSource(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    @Override
    public BoundSql getBoundSql(Object o) {
        return this.boundSql;
    }
}
