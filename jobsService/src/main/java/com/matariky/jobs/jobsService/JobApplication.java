package com.matariky.jobs.jobsService;

import java.util.Date;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.alibaba.excel.util.DateUtils;
import com.matariky.constant.TracerConstants;
import com.matariky.utils.OpenTelemetryUtil;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @package: com.matariky.jobs.jobsService
 * @description: 启动器
 * @version: V1.0
 */
@MapperScan(basePackages = { "com.matariky.**.mapper*" })
@SpringBootApplication(scanBasePackages = { "com.matariky.**.*" }, exclude = {
        org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration.class })

public class JobApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext cac = SpringApplication.run(JobApplication.class, args);
        Environment env = cac.getBean(Environment.class);
        OpenTelemetryUtil.init("jobService", env.getProperty("signoz.tracer.url"));
        // RetrieveTracer
        Tracer tracer = OpenTelemetryUtil.getTracer();
        // Create Span
        // Operation Name
        Span span = tracer.spanBuilder(TracerConstants.APPSTART).startSpan();

        try (Scope scope = span.makeCurrent()) {
            // RetrievetraceId
            // RetrievespanId
            // Configuration Properties
            span.setAttribute(TracerConstants.APPNAME, "jobService");// App Name
            span.setAttribute(TracerConstants.HASERROR, "false");// Wether There is Error
            // 以下是具体 Error Message
            Attributes eventAttributes = Attributes.of(
                    AttributeKey.stringKey(TracerConstants.SUCCESS), TracerConstants.SUCCESS,
                    AttributeKey.stringKey(TracerConstants.EVENT_TIME),
                    DateUtils.format(new Date(System.currentTimeMillis()), DateUtils.DATE_FORMAT_19));

            // Add Event
            span.addEvent(TracerConstants.APPSTART, eventAttributes);

        } catch (Throwable t) {
            span.setStatus(StatusCode.ERROR, t.getMessage());
            span.setAttribute(TracerConstants.HASERROR, "true");// Wether There is Error
            Attributes eventAttributes = Attributes.of(

                    AttributeKey.stringKey(TracerConstants.ERROR), t.getMessage());

            // Add Event
            span.addEvent(TracerConstants.APPSTART, eventAttributes);
        } finally {

            OpenTelemetryUtil.getExporter().flush();

            span.end();
        }

        // 在docker环境下容器不会调用System.exit 退出 ,也不会进这 Close HOOk。
        Runtime.getRuntime().addShutdownHook((new Thread() {
            public void run() {
                Span span = tracer.spanBuilder(TracerConstants.APPSTOP).startSpan();
                span.setAttribute(TracerConstants.APPNAME, "jobService");// App Name
                span.setAttribute(TracerConstants.HASERROR, "false");// Wether There is Error
                // 以下是具体 Error Message
                Attributes eventAttributes = Attributes.of(

                        AttributeKey.stringKey(TracerConstants.ERROR), TracerConstants.SUCCESS);

                // Add Event
                span.addEvent(TracerConstants.APPSTOP, eventAttributes);
                OpenTelemetryUtil.getExporter().flush();

                span.end();
            }
        }));

    }
}
