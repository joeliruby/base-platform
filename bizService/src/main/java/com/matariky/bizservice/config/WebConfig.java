package com.matariky.bizservice.config;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


public class WebConfig extends WebMvcConfigurationSupport {

	@Bean
	@PostConstruct
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofBytes(2000000000)); // KB,MB
		factory.setMaxRequestSize(DataSize.ofBytes(2000000000));
		return factory.createMultipartConfig();
	}

}
