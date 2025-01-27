package com.matarikyr.processor.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.processor.utils.OpenTelemetryUtils;
import com.matariky.processor.utils.YXOtlpHttpSpanExporter;

import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;

@SpringBootTest
public class OpenTelemetryUtilsTest {

    @InjectMocks
    private OpenTelemetryUtils opentelemetryutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";

        // When
        OpenTelemetryUtils.init(serviceName, signozTracerUrl);

        // Then
        assertThat(OpenTelemetryUtils.getTracer()).isNotNull();
        assertThat(OpenTelemetryUtils.getExporter()).isNotNull();
        assertThat(OpenTelemetryUtils.getBatchProcessor()).isNotNull();
        assertThat(OpenTelemetryUtils.getSimpleProcessor()).isNotNull();
    }

    @Test
    void testGetExporter() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";
        OpenTelemetryUtils.init(serviceName, signozTracerUrl);

        // When
        YXOtlpHttpSpanExporter exporter = OpenTelemetryUtils.getExporter();

        // Then
        assertThat(exporter).isNotNull();
    }

    @Test
    void testGetBatchProcessor() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";
        OpenTelemetryUtils.init(serviceName, signozTracerUrl);

        // When
        BatchSpanProcessor batchProcessor = OpenTelemetryUtils.getBatchProcessor();

        // Then
        assertThat(batchProcessor).isNotNull();
    }

    @Test
    void testGetSimpleProcessor() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";
        OpenTelemetryUtils.init(serviceName, signozTracerUrl);

        // When
        SimpleSpanProcessor simpleProcessor = OpenTelemetryUtils.getSimpleProcessor();

        // Then
        assertThat(simpleProcessor).isNotNull();
    }

    @Test
    void testGetTracer() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";
        OpenTelemetryUtils.init(serviceName, signozTracerUrl);

        // When
        Tracer tracer = OpenTelemetryUtils.getTracer();

        // Then
        assertThat(tracer).isNotNull();
    }
}
