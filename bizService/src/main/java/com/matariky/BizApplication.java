package com.matariky;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import com.alibaba.excel.util.DateUtils;
import com.matariky.constant.RedisKey;
import com.matariky.constant.TracerConstants;
import com.matariky.redis.RedisUtils;
import com.matariky.utils.OpenTelemetryUtil;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan(value = { "com.matariky.bizservice.**.mapper*", "com.matariky.userservice.**.mapper*",
		"com.matariky.orderservice.**.mapper*", "com.matariky.commonservice.**.mapper*",
		"com.matariky.clickhouse.**.mapper*", })
@ComponentScan(basePackages = { "com.matariky.bizservice", "com.matariky.commonservice", "com.matariky.userservice",
		"com.matariky.orderservice", "com.matariky.config", "com.matariky.mybatis", "com.matariky.redis",
		"com.matariky.aop", "com.matariky.exception",
		"com.matariky.clickhouse" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {}))
@EnableSwagger2
public class BizApplication {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext application = SpringApplication.run(BizApplication.class, args);
		Environment env = application.getEnvironment();
		OpenTelemetryUtil.init("bizService", env.getProperty("signoz.tracer.url"));
		String port = env.getProperty("server.port");
		InputStream inputStreamCN = com.matariky.BizApplication.class
				.getResourceAsStream("/language/message-CN.properties");
		Properties cnProperties = new Properties();
		cnProperties.load(inputStreamCN);
		RedisUtils redisUtils = application.getBean(RedisUtils.class);
		for (String propName : cnProperties.stringPropertyNames()) {
			redisUtils.hSet(RedisKey.MENU_NAMES + "CN", propName, cnProperties.get(propName), RedisUtils.NOT_EXPIRE);
		}

		InputStream inputStreamEN = com.matariky.BizApplication.class
				.getResourceAsStream("/language/message-EN.properties");
		Properties enProperties = new Properties();
		enProperties.load(inputStreamEN);
		for (String propName : enProperties.stringPropertyNames()) {
			redisUtils.hSet(RedisKey.MENU_NAMES + "EN", propName, enProperties.get(propName), RedisUtils.NOT_EXPIRE);
		}

		Tracer tracer = OpenTelemetryUtil.getTracer();
		Span span = tracer.spanBuilder(TracerConstants.APPSTART).startSpan();

		try (Scope scope = span.makeCurrent()) {
			span.setAttribute(TracerConstants.APPNAME, "bizService");
			span.setAttribute(TracerConstants.HASERROR, "false");
			Attributes eventAttributes = Attributes.of(AttributeKey.stringKey(TracerConstants.SUCCESS),
					TracerConstants.SUCCESS, AttributeKey.stringKey(TracerConstants.EVENT_TIME),
					DateUtils.format(new Date(System.currentTimeMillis()), DateUtils.DATE_FORMAT_19));
			span.addEvent(TracerConstants.APPSTART, eventAttributes);

		} catch (Throwable t) {
			span.setStatus(StatusCode.ERROR, t.getMessage());
			span.setAttribute(TracerConstants.HASERROR, "true");
			Attributes eventAttributes = Attributes.of(AttributeKey.stringKey(TracerConstants.ERROR), t.getMessage());
			span.addEvent(TracerConstants.APPSTART, eventAttributes);
		} finally {
			OpenTelemetryUtil.getExporter().flush();
			span.end();
		}

		SpringApplication.getShutdownHandlers().add(new Thread() {
			public void run() {
				Span span = tracer.spanBuilder(TracerConstants.APPSTOP).startSpan();
				span.setAttribute(TracerConstants.APPNAME, "bizService");
				span.setAttribute(TracerConstants.HASERROR, "false");
				Attributes eventAttributes = Attributes.of(

						AttributeKey.stringKey(TracerConstants.ERROR), TracerConstants.SUCCESS);
				span.addEvent(TracerConstants.APPSTOP, eventAttributes);
				OpenTelemetryUtil.getExporter().flush();

				span.end();
			}
		});

		System.out.print("\n----------------------------------------------------------\n\t"
				+ "Generic Platform is running! Access URL:\n\t" + "Local: \t\thttp://localhost:" + port
				+ "/swagger-ui/index.html\n\t" + "----------------------------------------------------------");
	}

}
