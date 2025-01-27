package com.matarikyr.normalizers.heartbeat.solid.SRE401X;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.normalizers.heartbeat.solid.SRE401X.SolidSRE401XHeartbeatNormalizer;

@SpringBootTest
public class SolidSRE401XHeartbeatNormalizerTest {

    @InjectMocks
    private SolidSRE401XHeartbeatNormalizer solidsre401xheartbeatnormalizer;

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
        String payload = "{\"cmd\":\"60002\",\"data\":{\"deviceNo\":\"12345\",\"deviceType\":\"typeA\"}}";
        when(message.getBody(String.class)).thenReturn(payload);

        // When
        solidsre401xheartbeatnormalizer.process(exchange);

        // Then
        verify(message).setBody(anyString());
    }

    @Test
    void testProcessWithInvalidCmd() {
        // Given
        String payload = "{\"cmd\":\"99999\",\"data\":{\"deviceNo\":\"12345\",\"deviceType\":\"typeA\"}}";
        when(message.getBody(String.class)).thenReturn(payload);

        // When & Then
        assertThrows(Exception.class, () -> solidsre401xheartbeatnormalizer.process(exchange));
    }

    @Test
    void testProcessWithEmptyPayload() throws Exception {
        // Given
        when(message.getBody(String.class)).thenReturn("");

        // When
        solidsre401xheartbeatnormalizer.process(exchange);

        // Then
        verify(message, never()).setBody(anyString());
    }

    @Test
    void testProcessWithNullPayload() throws Exception {
        // Given
        when(message.getBody(String.class)).thenReturn(null);

        // When
        solidsre401xheartbeatnormalizer.process(exchange);

        // Then
        verify(message, never()).setBody(anyString());
    }

    @Test
    void testProcessWithValidPayloadAndCheckNormalizedMessage() throws Exception {
        // Given
        String payload = "{\"cmd\":\"60002\",\"data\":{\"deviceNo\":\"12345\",\"deviceType\":\"typeA\"}}";
        when(message.getBody(String.class)).thenReturn(payload);

        // When
        solidsre401xheartbeatnormalizer.process(exchange);

        // Then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(message).setBody(captor.capture());
        String expected = "{\"deviceType\":\"typeA\",\"deviceCode\":\"12345\",\"originalMessage\":\"" + payload
                + "\",\"operation\":\"HEARTBEAT\"}";
        assertEquals(expected, captor.getValue());
    }
}
