package com.matariky.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.matariky.automation.**.mapper")
@EntityScan(basePackages = "com.matariky.automation.**.bean")
public class AutomationApplication {

	@Bean
	SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(AutomationApplication.class, args);
	}
}
