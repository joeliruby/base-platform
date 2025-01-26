package com.matariky.bizservice.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.matariky.bizservice.filter.ChinesePathFilter;

@Configuration
public class FilterConfiguration {

	@Bean
	FilterRegistrationBean<Filter> getChinesePathFilter() {
		ChinesePathFilter filter = new ChinesePathFilter();
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(filter);
		return registrationBean;
	}

}
