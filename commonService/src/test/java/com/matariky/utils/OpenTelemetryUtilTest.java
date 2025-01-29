package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;

@SpringBootTest
public class OpenTelemetryUtilTest {

    @InjectMocks
    private OpenTelemetryUtil opentelemetryutil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInit() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";

        // When
        OpenTelemetryUtil.init(serviceName, signozTracerUrl);

        // Then
        assertNotNull(OpenTelemetryUtil.getTracer());
        assertNotNull(OpenTelemetryUtil.getExporter());
        assertNotNull(OpenTelemetryUtil.getBatchProcessor());
        assertNotNull(OpenTelemetryUtil.getSimpleProcessor());
    }

    @Test
    void testGetExporter() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";
        OpenTelemetryUtil.init(serviceName, signozTracerUrl);

        // When
        var exporter = OpenTelemetryUtil.getExporter();

        // Then
        assertNotNull(exporter);
    }

    @Test
    void testGetBatchProcessor() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";
        OpenTelemetryUtil.init(serviceName, signozTracerUrl);

        // When
        BatchSpanProcessor batchProcessor = OpenTelemetryUtil.getBatchProcessor();

        // Then
        assertNotNull(batchProcessor);
    }

    @Test
    void testGetSimpleProcessor() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";
        OpenTelemetryUtil.init(serviceName, signozTracerUrl);

        // When
        SimpleSpanProcessor simpleProcessor = OpenTelemetryUtil.getSimpleProcessor();

        // Then
        assertNotNull(simpleProcessor);
    }

    @Test
    void testGetTracer() {
        // Given
        String serviceName = "testService";
        String signozTracerUrl = "http://localhost:4317";
        OpenTelemetryUtil.init(serviceName, signozTracerUrl);

        // When
        var tracer = OpenTelemetryUtil.getTracer();

        // Then
        assertNotNull(tracer);
    }
}
