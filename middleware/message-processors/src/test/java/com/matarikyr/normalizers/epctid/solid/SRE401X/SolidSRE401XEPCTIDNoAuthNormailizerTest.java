package com.matarikyr.normalizers.epctid.solid.SRE401X;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
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

import com.matariky.normalizers.epctid.solid.SRE401X.SolidSRE401XEPCTIDNoAuthNormailizer;

@SpringBootTest
public class SolidSRE401XEPCTIDNoAuthNormailizerTest {

    @InjectMocks
    private SolidSRE401XEPCTIDNoAuthNormailizer solidsre401xepctidnoauthnormailizer;

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
        String payload = "{\"cmd\":\"60001\",\"data\":{\"deviceType\":\"type1\",\"deviceNo\":\"123\",\"IP\":\"192.168.0.1\",\"epcList\":[{\"data\":\"data1\",\"epc\":\"epc1\",\"ant\":\"ant1\"}]}}";
        when(message.getBody(String.class)).thenReturn(payload);

        // When
        solidsre401xepctidnoauthnormailizer.process(exchange);

        // Then
        verify(message).setBody(anyString());
    }

    @Test
    void testProcessWithInvalidCmd() {
        // Given
        String payload = "{\"cmd\":\"99999\",\"data\":{\"deviceType\":\"type1\",\"deviceNo\":\"123\",\"IP\":\"192.168.0.1\",\"epcList\":[{\"data\":\"data1\",\"epc\":\"epc1\",\"ant\":\"ant1\"}]}}";
        when(message.getBody(String.class)).thenReturn(payload);

        // When & Then
        assertThrows(Exception.class, () -> solidsre401xepctidnoauthnormailizer.process(exchange));
    }

    @Test
    void testProcessWithEmptyPayload() throws Exception {
        // Given
        when(message.getBody(String.class)).thenReturn("");

        // When
        solidsre401xepctidnoauthnormailizer.process(exchange);

        // Then
        verify(message, never()).setBody(anyString());
    }

    @Test
    void testProcessWithNullPayload() throws Exception {
        // Given
        when(message.getBody(String.class)).thenReturn(null);

        // When
        solidsre401xepctidnoauthnormailizer.process(exchange);

        // Then
        verify(message, never()).setBody(anyString());
    }
}
