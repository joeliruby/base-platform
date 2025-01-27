package com.matarikyr.normalizers.deviceEvents.solid.SRE401X;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.normalizers.deviceEvents.solid.SRE401X.SolidSRE401XDeviceEventsNormlizer;
import com.matariky.processor.constants.TracerConstants;

@SpringBootTest
public class SolidSRE401XDeviceEventsNormlizerTest {

    @InjectMocks
    private SolidSRE401XDeviceEventsNormlizer solidsre401xdeviceeventsnormlizer;

    @Mock
    private Exchange exchange;

    @Mock
    private Message message;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(exchange.getIn()).thenReturn(message);
    }

    @Test
    void testProcessWithValidPayload() throws Exception {
        // Given
        String mqttTopic = "topic/deviceCode_123";
        String payloadOuter = "{\"cmd\":\"60005\",\"data\":{}}";
        when(message.getHeader(TracerConstants.MQTTTOPIC)).thenReturn(mqttTopic);
        when(message.getBody(String.class)).thenReturn(payloadOuter);

        // When
        solidsre401xdeviceeventsnormlizer.process(exchange);

        // Then
        verify(message).setHeader(eq(TracerConstants.NEXTREDISKEY),
                eq(TracerConstants.DEVICEUPDATED + "deviceCode_123"));
        verify(message).setBody(anyString());
    }

    @Test
    void testProcessWithInvalidPayload() throws Exception {
        // Given
        String mqttTopic = "topic/deviceCode_123";
        String payloadOuter = "{\"cmd\":\"12345\",\"data\":{}}";
        when(message.getHeader(TracerConstants.MQTTTOPIC)).thenReturn(mqttTopic);
        when(message.getBody(String.class)).thenReturn(payloadOuter);

        // When & Then
        assertThrows(Exception.class, () -> solidsre401xdeviceeventsnormlizer.process(exchange));
    }

    @Test
    void testProcessWithEmptyPayload() throws Exception {
        // Given
        String mqttTopic = "topic/deviceCode_123";
        String payloadOuter = "";
        when(message.getHeader(TracerConstants.MQTTTOPIC)).thenReturn(mqttTopic);
        when(message.getBody(String.class)).thenReturn(payloadOuter);

        // When
        solidsre401xdeviceeventsnormlizer.process(exchange);

        // Then
        verify(message, never()).setHeader(anyString(), anyString());
        verify(message, never()).setBody(anyString());
    }

    @Test
    void testProcessWithNullPayload() throws Exception {
        // Given
        String mqttTopic = "topic/deviceCode_123";
        String payloadOuter = null;
        when(message.getHeader(TracerConstants.MQTTTOPIC)).thenReturn(mqttTopic);
        when(message.getBody(String.class)).thenReturn(payloadOuter);

        // When
        solidsre401xdeviceeventsnormlizer.process(exchange);

        // Then
        verify(message, never()).setHeader(anyString(), anyString());
        verify(message, never()).setBody(anyString());
    }
}
