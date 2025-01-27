package com.matariky.utils;

import com.matariky.constant.TracerConstants;

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

public class OpenTelemetryUtil {

    private static YXOtlpHttpSpanExporter otlpHttpSpanExporter = null;

    private static SimpleSpanProcessor simpleSpanProcessor;

    private static BatchSpanProcessor batchSpanProcessor;

    public static void init(String serviceName, String signozTracerUrl) {
        Resource otelResource = Resource.getDefault().merge(
                Resource.create(
                        Attributes.of(
                                // Replace <your-service-name> with your app name
                                ResourceAttributes.SERVICE_NAME, serviceName, // app name
                                // Replace <your-host-name> with your host name
                                ResourceAttributes.HOST_NAME, "localhost" // device server name
                        )));

        /* Use HTTP protocol to report trace data */
        YXOtlpHttpSpanExporter exporter = YXOtlpHttpSpanExporter.builder()
                .setEndpoint(signozTracerUrl) // e.g.
                                              // http://tracing-analysis-dc-hz.aliyuncs.com/adapt_xxxx@xxxx_xxxx@xxxx/api/otlp/traces
                .build();
        otlpHttpSpanExporter = exporter;
        simpleSpanProcessor = (SimpleSpanProcessor) SimpleSpanProcessor.create(LoggingSpanExporter.create());
        batchSpanProcessor = BatchSpanProcessor.builder(exporter).build();

        SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(simpleSpanProcessor) // Optional, print trace data to log/command line, comment out if
                                                       // not needed // Replace <HTTP-endpoint>
                                                       // with the endpoint retrieved from prerequisites
                .addSpanProcessor(batchSpanProcessor)
                .setResource(otelResource)
                .build();

        OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(sdkTracerProvider)
                .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance())).build();

        // Retrieve tracer to create span
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
