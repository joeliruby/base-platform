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

	@Bean(name="mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
    public DataSource clickDruidDataSource(){
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
        //设置datasource
        sqlSessionFactoryBean.setDataSource(dataSource);
        //获取mapper资源
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:com/matariky/**/mapper/*Mapper.xml");
        //设置mapper的位置,注意3个mepper对应的xml得分别放在3个不同的包
        sqlSessionFactoryBean.setMapperLocations(resources);

        //其他配置 ps:sqlSessionFactoryBean内可以配置yml的一切配置
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // Wether 下划线转驼峰
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        //设置日志
        mybatisConfiguration.setLogImpl(StdOutImpl.class);
        //配置别名包
//        sqlSessionFactoryBean.setTypeAliasesPackage("org.gjw.bean.db1");

        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);

        MybatisPlusInterceptor mybatisPlusInterceptor =new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(mybatisPlusDataScopeInterceptor());
        sqlSessionFactoryBean.getConfiguration().addInterceptor(mybatisPlusInterceptor);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);

        return  sqlSessionFactoryBean.getObject();

    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
