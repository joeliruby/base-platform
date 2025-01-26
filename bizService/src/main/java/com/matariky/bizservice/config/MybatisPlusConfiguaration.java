package com.matariky.bizservice.config;

import com.matariky.id.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;

@Configuration
public class MybatisPlusConfiguaration {
	@Bean
	MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
		return plusProperties -> plusProperties.getGlobalConfig()
				.setIdentifierGenerator(new SnowflakeIdGenerator(SnowflakeIdGenerator.getWorkerIdByIP()));
	}

}
