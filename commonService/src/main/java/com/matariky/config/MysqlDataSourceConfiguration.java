package com.matariky.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.matariky.mybatis.MybatisPlusDataScopeInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.matariky.**.**.mapper*", sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
public class MysqlDataSourceConfiguration {

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
    public DataSource clickDruidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public MybatisPlusDataScopeInterceptor mybatisPlusDataScopeInterceptor() {
        return new MybatisPlusDataScopeInterceptor();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource)
            throws Exception {

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        // Configurationdatasource
        sqlSessionFactoryBean.setDataSource(dataSource);
        // Retrievemapper resource
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/matariky/**/mapper/*Mapper.xml");
        // Configurationmapper Position,3 paid attention to 3 mapper the corresponding
        // XML is placed in 3 different packages respectively
        sqlSessionFactoryBean.setMapperLocations(resources);

        // other Configurationps:sqlSessionFactoryBean Can Configuration yml Everything
        // Configuration
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // Wether Turn the line to the hump
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        // Configuration Log
        mybatisConfiguration.setLogImpl(StdOutImpl.class);

        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);

        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(mybatisPlusDataScopeInterceptor());
        sqlSessionFactoryBean.getConfiguration().addInterceptor(mybatisPlusInterceptor);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);

        return sqlSessionFactoryBean.getObject();

    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mysqlSqlSessionTemplate(
            @Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
