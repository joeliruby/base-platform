package com.matariky.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.matariky.tdengine.meter.mapper", sqlSessionTemplateRef = "tdengineSqlSessionTemplate")
public class TdEngineDataSourceConfig {

    @Bean(name = "tdengineDataSource")
    // @Primary
    public DataSource tdengineDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tdengineSqlSessionFactory")
    public SqlSessionFactory tdengineSqlSessionFactory(@Qualifier("tdengineDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // Add XML Table of contents
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(
                    resolver.getResources("classpath*:com/matariky/tdengine/mapper/tdengine/meter/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Bean(name = "tdengineTransactionManager")
    public DataSourceTransactionManager tdengineTransactionManager(
            @Qualifier("tdengineDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "tdengineSqlSessionTemplate")
    public SqlSessionTemplate tdengineSqlSessionTemplate(
            @Qualifier("tdengineSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
