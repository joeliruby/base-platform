package com.matariky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

//import com.matariky.bizservice.CommonsApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.matariky.*.mapper"})//,"com.matariky.bizservice.mapper"})
@ComponentScan(basePackages ={"com.matariky.*","com.matariky.userservice.*",})
//		,
//		"com.matariky.userservice.config","com.matariky.processservice.config",
//		,"com.matariky.*.rest"
//		"com.matariky.processservice.servlet",
//		"com.matariky.processservice.api",

//		"org.flowable.ui.modeler.rest.app",
//		"org.flowable.ui.modeler.serviceapi",  "org.flowable.ui.modeler.service",
//		"org.flowable.ui.common.service.idm", "org.flowable.ui.idm.service",
//		"org.flowable.ui.modeler.rest.api","org.flowable.ui.modeler.repository","org.flowable.ui.common.repository","org.flowable.ui.common.tenant","org.flowable.ui.modeler.properties"
//@Import({
//	CommonsApplication.class
//    ApplicationConfiguration.class,
//    AppDispatcherServletConfiguration.class,
//    SpringSecurityConfiguration.class
//})
//@EnableConfigurationProperties({FlowableIdmAppProperties.class, FlowableModelerAppProperties.class})
@EnableCaching
public class UserseriviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserseriviceApplication.class, args);
    }
    
}
