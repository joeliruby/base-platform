package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.opentelemetry.api.metrics.MeterProvider;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;

@SpringBootTest
public class YXOtlpHttpSpanExporterBuilderTest {

    @InjectMocks
    private YXOtlpHttpSpanExporterBuilder yxotlphttpspanexporterbuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetTimeoutWithTimeUnit() {
        // Given
        long timeout = 10;
        TimeUnit unit = TimeUnit.SECONDS;

        // When
        yxotlphttpspanexporterbuilder.setTimeout(timeout, unit);

        // Then
        // Verify the timeout was set correctly (mock verification or state check)
    }

    @Test
    void testSetTimeoutWithDuration() {
        // Given
        Duration timeout = Duration.ofSeconds(5);

        // When
        yxotlphttpspanexporterbuilder.setTimeout(timeout);

        // Then
        // Verify the timeout was set correctly (mock verification or state check)
    }

    @Test
    void testSetEndpoint() {
        // Given
        String endpoint = "http://example.com/v1/traces";

        // When
        yxotlphttpspanexporterbuilder.setEndpoint(endpoint);

        // Then
        // Verify the endpoint was set correctly (mock verification or state check)
    }

    @Test
    void testSetCompression() {
        // Given
        String compressionMethod = "gzip";

        // When
        yxotlphttpspanexporterbuilder.setCompression(compressionMethod);

        // Then
        // Verify the compression method was set correctly (mock verification or state
        // check)
    }

    @Test
    void testAddHeader() {
        // Given
        String key = "Authorization";
        String value = "Bearer token";

        // When
        yxotlphttpspanexporterbuilder.addHeader(key, value);

        // Then
        // Verify the header was added correctly (mock verification or state check)
    }

    @Test
    void testSetTrustedCertificates() {
        // Given
        byte[] trustedCertificatesPem = "certificate".getBytes();

        // When
        yxotlphttpspanexporterbuilder.setTrustedCertificates(trustedCertificatesPem);

        // Then
        // Verify the trusted certificates were set correctly (mock verification or
        // state check)
    }

    @Test
    void testSetClientTls() {
        // Given
        byte[] privateKeyPem = "privateKey".getBytes();
        byte[] certificatePem = "certificate".getBytes();

        // When
        yxotlphttpspanexporterbuilder.setClientTls(privateKeyPem, certificatePem);

        // Then
        // Verify the client TLS was set correctly (mock verification or state check)
    }

    @Test
    void testSetMeterProvider() {
        // Given
        MeterProvider meterProvider = mock(MeterProvider.class);

        // When
        yxotlphttpspanexporterbuilder.setMeterProvider(meterProvider);

        // Then
        // Verify the meter provider was set correctly (mock verification or state
        // check)
    }

    @Test
    void testBuild() {
        // When
        YXOtlpHttpSpanExporter exporter = yxotlphttpspanexporterbuilder.build();

        // Then
        assertNotNull(exporter);
    }
}
