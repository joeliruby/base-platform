package com.matarikyr.normalizers.synch.solid.SRE401X;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

import com.matariky.normalizers.synch.solid.SRE401X.SolidSRE401XSynchNormalizer;
import com.matariky.processor.constants.TracerConstants;

@SpringBootTest
public class SolidSRE401XSynchNormalizerTest {

    @InjectMocks
    private SolidSRE401XSynchNormalizer solidsre401xsynchnormalizer;

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
        String payload = "{\"cmd\":\"60007\",\"data\":{}}";
        String mqttTopic = "some/topic/device_123";
        when(message.getBody(String.class)).thenReturn(payload);
        when(message.getHeader(TracerConstants.MQTTTOPIC)).thenReturn(mqttTopic);

        // When
        solidsre401xsynchnormalizer.process(exchange);

        // Then
        verify(message).setHeader(eq(TracerConstants.NEXTREDISKEY), eq(TracerConstants.DEVICESYNCHED + "device_123"));
        verify(message).setBody(anyString());
    }

    @Test
    void testProcessWithInvalidCmd() {
        // Given
        String payload = "{\"cmd\":\"12345\",\"data\":{}}";
        when(message.getBody(String.class)).thenReturn(payload);

        // When / Then
        assertThatThrownBy(() -> solidsre401xsynchnormalizer.process(exchange))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testProcessWithEmptyPayload() throws Exception {
        // Given
        String payload = "";
        when(message.getBody(String.class)).thenReturn(payload);

        // When
        solidsre401xsynchnormalizer.process(exchange);

        // Then
        verify(message, never()).setHeader(anyString(), anyString());
        verify(message, never()).setBody(anyString());
    }

    @Test
    void testProcessWithNullPayload() throws Exception {
        // Given
        when(message.getBody(String.class)).thenReturn(null);

        // When
        solidsre401xsynchnormalizer.process(exchange);

        // Then
        verify(message, never()).setHeader(anyString(), anyString());
        verify(message, never()).setBody(anyString());
    }
}
