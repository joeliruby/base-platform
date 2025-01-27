package com.matarikyr.processor.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.processor.utils.YXOtlpHttpSpanExporter;
import com.matariky.processor.utils.YXOtlpHttpSpanExporterBuilder;

import io.opentelemetry.exporter.internal.okhttp.OkHttpExporter;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.data.SpanData;

@SpringBootTest
public class YXOtlpHttpSpanExporterTest {

    @Mock
    private OkHttpExporter delegate;

    @InjectMocks
    private YXOtlpHttpSpanExporter yxotlphttpspanexporter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testExport() {
        // Given
        Collection<SpanData> spans = Collections.emptyList();
        when(delegate.export(any(), anyInt())).thenReturn(CompletableResultCode.ofSuccess());

        // When
        CompletableResultCode result = yxotlphttpspanexporter.export(spans);

        // Then
        assertThat(result.isSuccess()).isTrue();
        verify(delegate).export(any(), eq(spans.size()));
    }

    @Test
    void testFlush() {
        // When
        CompletableResultCode result = yxotlphttpspanexporter.flush();

        // Then
        assertThat(result.isSuccess()).isTrue();
    }

    @Test
    void testShutdown() {
        // Given
        when(delegate.shutdown()).thenReturn(CompletableResultCode.ofSuccess());

        // When
        CompletableResultCode result = yxotlphttpspanexporter.shutdown();

        // Then
        assertThat(result.isSuccess()).isTrue();
        verify(delegate).shutdown();
    }

    @Test
    void testGetDefault() {
        // When
        YXOtlpHttpSpanExporter exporter = YXOtlpHttpSpanExporter.getDefault();

        // Then
        assertThat(exporter).isNotNull();
    }

    @Test
    void testBuilder() {
        // When
        YXOtlpHttpSpanExporterBuilder builder = YXOtlpHttpSpanExporter.builder();

        // Then
        assertThat(builder).isNotNull();
    }
}
