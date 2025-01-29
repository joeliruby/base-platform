package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import io.opentelemetry.exporter.internal.okhttp.OkHttpExporter;
import io.opentelemetry.exporter.internal.otlp.traces.TraceRequestMarshaler;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.data.SpanData;
import org.mockito.Mock;
import java.util.Collection;
import java.util.Collections;

@SpringBootTest
public class YXOtlpHttpSpanExporterTest {

    @Mock
    private OkHttpExporter<TraceRequestMarshaler> delegate;

    @InjectMocks
    private YXOtlpHttpSpanExporter yxotlphttpspanexporter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExport() {
        // Given
        Collection<SpanData> spans = Collections.emptyList();
        TraceRequestMarshaler exportRequest = TraceRequestMarshaler.create(spans);
        when(delegate.export(exportRequest, spans.size())).thenReturn(CompletableResultCode.ofSuccess());

        // When
        CompletableResultCode result = yxotlphttpspanexporter.export(spans);

        // Then
        assertTrue(result.isSuccess());
        verify(delegate).export(exportRequest, spans.size());
    }

    @Test
    void testFlush() {
        // When
        CompletableResultCode result = yxotlphttpspanexporter.flush();

        // Then
        assertTrue(result.isSuccess());
    }

    @Test
    void testShutdown() {
        // Given
        when(delegate.shutdown()).thenReturn(CompletableResultCode.ofSuccess());

        // When
        CompletableResultCode result = yxotlphttpspanexporter.shutdown();

        // Then
        assertTrue(result.isSuccess());
        verify(delegate).shutdown();
    }

    // Add more test methods for other methods in YXOtlpHttpSpanExporter if needed
}
