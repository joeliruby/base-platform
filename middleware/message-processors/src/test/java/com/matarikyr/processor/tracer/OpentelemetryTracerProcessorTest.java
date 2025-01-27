package com.matarikyr.processor.tracer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.processor.constants.TracerConstants;
import com.matariky.processor.tracer.OpentelemetryTracerProcessor;
import com.matariky.processor.utils.OpenTelemetryUtils;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.api.trace.Tracer;

@SpringBootTest
public class OpentelemetryTracerProcessorTest {

    @InjectMocks
    private OpentelemetryTracerProcessor opentelemetrytracerprocessor;

    @Mock
    private Exchange exchange;

    @Mock
    private Message message;

    @Mock
    private Tracer tracer;

    @Mock
    private Span span;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(exchange.getIn()).thenReturn(message);
        when(tracer.spanBuilder(anyString())).thenReturn(mock(SpanBuilder.class));
        when(tracer.spanBuilder(anyString()).startSpan()).thenReturn(span);
        OpenTelemetryUtils.setTracer(tracer);
    }

    @Test
    void testProcessWithValidPayload() throws Exception {
        // Given
        String payload = "{\"deviceCode\":\"123\",\"deviceType\":\"typeA\",\"operation\":\"op1\",\"status\":0}";
        when(message.getBody(String.class)).thenReturn(payload);

        // When
        opentelemetrytracerprocessor.process(exchange);

        // Then
        verify(span).setAttribute(TracerConstants.APPNAME, TracerConstants.KARAF);
        verify(span).setAttribute(TracerConstants.HASERROR, "false");
        verify(span).addEvent(eq("op1"), any(Instant.class));
        verify(span).end();
    }

    @Test
    void testProcessWithInvalidPayload() throws Exception {
        // Given
        String payload = "";
        when(message.getBody(String.class)).thenReturn(payload);

        // When
        opentelemetrytracerprocessor.process(exchange);

        // Then
        verify(span, never()).setAttribute(anyString(), anyString());
        verify(span, never()).addEvent(anyString(), any(Instant.class));
        verify(span, never()).end();
    }

    @Test
    void testProcessWithErrorStatus() throws Exception {
        // Given
        String payload = "{\"deviceCode\":\"123\",\"deviceType\":\"typeA\",\"operation\":\"op1\",\"status\":1}";
        when(message.getBody(String.class)).thenReturn(payload);

        // When
        opentelemetrytracerprocessor.process(exchange);

        // Then
        verify(span).setAttribute(TracerConstants.APPNAME, TracerConstants.KARAF);
        verify(span).setAttribute(TracerConstants.HASERROR, "true");
        verify(span).addEvent(eq("op1"), any(Instant.class));
        verify(span).end();
    }

    // Add more test methods for other scenarios in OpentelemetryTracerProcessor
}
