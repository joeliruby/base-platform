package com.matariky.processor.utils;

import com.matariky.processor.constants.TracerConstants;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;

public class OpenTelemetryUtils {

    private static YXOtlpHttpSpanExporter otlpHttpSpanExporter = null;

    private static SimpleSpanProcessor simpleSpanProcessor;

    private static BatchSpanProcessor batchSpanProcessor;

    public static void init(String serviceName, String signozTracerUrl) {
        Resource otelResource = Resource.getDefault().merge(
                Resource.create(
                        Attributes.of(
                                // 请将 <your-service-name> 替换为您的 App Name
                                ResourceAttributes.SERVICE_NAME, serviceName, // app Name
                                // 请将 <your-host-name> 替换为您的主机 Name
                                ResourceAttributes.HOST_NAME, "localhost"// Device Server Name
                        )));

        /* 使用HTTP协议上报链路 Data */
        YXOtlpHttpSpanExporter exporter = YXOtlpHttpSpanExporter.builder()
                .setEndpoint(signozTracerUrl) // 例如
                                              // http://tracing-analysis-dc-hz.aliyuncs.com/adapt_xxxx@xxxx_xxxx@xxxx/api/otlp/traces
                .build();
        otlpHttpSpanExporter = exporter;
        simpleSpanProcessor = (SimpleSpanProcessor) SimpleSpanProcessor.create(LoggingSpanExporter.create());
        batchSpanProcessor = BatchSpanProcessor.builder(exporter).build();

        SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(simpleSpanProcessor) // 可选 ,将链路 Data Print 到 Log /命令行 ,如不需要请注释这一行 // 请将<HTTP-endpoint>
                                                       // 替换为从前提条件中 Retrieve的接入点
                .addSpanProcessor(batchSpanProcessor)
                .setResource(otelResource)
                .build();

        OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(sdkTracerProvider)
                .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance())).build();

        // Retrievetracer ,用来Create Span
        tracer = openTelemetry.getTracer(TracerConstants.TRACER, TracerConstants.TRACERVERSION);
    }

    public static YXOtlpHttpSpanExporter getExporter() {
        return otlpHttpSpanExporter;
    }

    public static BatchSpanProcessor getBatchProcessor() {
        return batchSpanProcessor;
    }

    public static SimpleSpanProcessor getSimpleProcessor() {
        return simpleSpanProcessor;
    }

    private static Tracer tracer;

    public static Tracer getTracer() {
        return tracer;
    }
}
