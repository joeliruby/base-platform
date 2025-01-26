package com.matariky.bizservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RFCConfig {

	@Bean
	Boolean setRequestTargetAllow() {
//		RFC 7230 and RFC 3986 specifications do not allow URL-related special characters. 
//		Manually specify Tomcat URLs to allow special symbols, such as {} as input parameters. 
//		Other symbols can be added as needed. See the Tomcat HttpParser source code.
		System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}");
		return true;
	}
}
