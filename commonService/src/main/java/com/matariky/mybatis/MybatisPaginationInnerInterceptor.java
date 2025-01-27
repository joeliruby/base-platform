package com.matariky.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;
import org.apache.ibatis.executor.Executor;

import java.sql.Connection;

/**
 * @description: Pagination 拦截器
 * @author: bo.chen
 * @create: 2023/1/3 16:46
 **/
public class MybatisPaginationInnerInterceptor extends PaginationInnerInterceptor {

    @Override
    protected IDialect findIDialect(Executor executor) {
        try {
            Connection conn = executor.getTransaction().getConnection();
            String jdbcUrl = conn.getMetaData().getURL();
            if (jdbcUrl.startsWith("jdbc:mysql:")) {
                return DialectFactory.getDialect(DbType.MYSQL);
            } else {
                return DialectFactory.getDialect(DbType.POSTGRE_SQL);
            }
        } catch (Exception e) {
            return DialectFactory.getDialect(DbType.MYSQL);
        }
    }
}
