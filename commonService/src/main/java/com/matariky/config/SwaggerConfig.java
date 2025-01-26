package com.matariky.config;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

@EnableOpenApi
//@Import(BeanValidatorPluginsConfiguration.class)
@Component(value="Swagger")
public class SwaggerConfig {
	@Bean(name="bizDocket")
    public Docket createBizApi() {
    	Predicate<springfox.documentation.RequestHandler> selector1 = RequestHandlerSelectors.basePackage("com.matariky.bizservice");
    	Contact contact = new Contact("li_zhe","","li_zhe@yxemi.com");
        return new Docket(DocumentationType.SWAGGER_2).groupName("biz")
                .pathMapping("/")
                .select().apis(selector1)
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("盈芯通用技术平台用户服务")
                        .description("盈芯通用技术平台用户服务接口描述")
                        .version("9.0")
                        .contact(contact)
                        .license("The Apache License")
                        .licenseUrl("http://www.baidu.com")
                        .build());
    }
	
    @Bean(name="commonDocket")
    public Docket createCommonApi() {
    	Predicate<springfox.documentation.RequestHandler> selector2 = RequestHandlerSelectors.basePackage("com.matariky.commonservice");
    	Contact contact = new Contact("li_zhe","","li_zhe@yxemi.com");
        return new Docket(DocumentationType.SWAGGER_2).groupName("common")
                .pathMapping("/")
                .select().apis(selector2)
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("盈芯通用技术平台用户服务")
                        .description("盈芯通用技术平台用户服务接口描述")
                        .version("9.0")
                        .contact(contact)
                        .license("The Apache License")
                        .licenseUrl("http://www.baidu.com")
                        .build());
    }
    
    @Bean(name="userDocket")
    public Docket createUserApi() {
    	Predicate<springfox.documentation.RequestHandler> selector3 = RequestHandlerSelectors.basePackage("com.matariky.userservice");
    	Contact contact = new Contact("li_zhe","","li_zhe@yxemi.com");
        return new Docket(DocumentationType.SWAGGER_2).groupName("user")
                .pathMapping("/")
                .select().apis(selector3)
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("盈芯通用技术平台用户服务")
                        .description("盈芯通用技术平台用户服务接口描述")
                        .version("9.0")
                        .contact(contact)
                        .license("The Apache License")
                        .licenseUrl("http://www.baidu.com")
                        .build());
    }
    
    @Bean(name="orderDocket")
    public Docket createOrderApi() {
    	Predicate<springfox.documentation.RequestHandler> selector4 = RequestHandlerSelectors.basePackage("com.matariky.orderservice");
    	Contact contact = new Contact("li_zhe","","li_zhe@yxemi.com");
        return new Docket(DocumentationType.SWAGGER_2).groupName("order")
                .pathMapping("/")
                .select().apis(selector4)
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("盈芯通用技术平台用户服务")
                        .description("盈芯通用技术平台用户服务接口描述")
                        .version("9.0")
                        .contact(contact)
                        .license("The Apache License")
                        .licenseUrl("http://www.baidu.com")
                        .build());
    }
}
