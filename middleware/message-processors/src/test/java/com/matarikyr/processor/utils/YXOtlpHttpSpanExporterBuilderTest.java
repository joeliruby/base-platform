package com.matarikyr.processor.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.processor.utils.YXOtlpHttpSpanExporter;
import com.matariky.processor.utils.YXOtlpHttpSpanExporterBuilder;

import io.opentelemetry.api.metrics.MeterProvider;

@SpringBootTest
public class YXOtlpHttpSpanExporterBuilderTest {

    @InjectMocks
    private YXOtlpHttpSpanExporterBuilder yxotlphttpspanexporterbuilder;

    @Mock
    private MeterProvider meterProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetTimeoutWithTimeUnit() {
        // Given
        long timeout = 10;
        TimeUnit unit = TimeUnit.SECONDS;

        // When
        yxotlphttpspanexporterbuilder.setTimeout(timeout, unit);

        // Then
        // Add assertions to verify the timeout was set correctly
    }

    @Test
    void testSetTimeoutWithDuration() {
        // Given
        Duration timeout = Duration.ofSeconds(5);

        // When
        yxotlphttpspanexporterbuilder.setTimeout(timeout);

        // Then
        // Add assertions to verify the timeout was set correctly
    }

    @Test
    void testSetEndpoint() {
        // Given
        String endpoint = "http://example.com/v1/traces";

        // When
        yxotlphttpspanexporterbuilder.setEndpoint(endpoint);

        // Then
        // Add assertions to verify the endpoint was set correctly
    }

    @Test
    void testSetCompression() {
        // Given
        String compressionMethod = "gzip";

        // When
        yxotlphttpspanexporterbuilder.setCompression(compressionMethod);

        // Then
        // Add assertions to verify the compression method was set correctly
    }

    @Test
    void testAddHeader() {
        // Given
        String key = "Authorization";
        String value = "Bearer token";

        // When
        yxotlphttpspanexporterbuilder.addHeader(key, value);

        // Then
        // Add assertions to verify the header was added correctly
    }

    @Test
    void testSetTrustedCertificates() {
        // Given
        byte[] trustedCertificatesPem = "certificate".getBytes();

        // When
        yxotlphttpspanexporterbuilder.setTrustedCertificates(trustedCertificatesPem);

        // Then
        // Add assertions to verify the trusted certificates were set correctly
    }

    @Test
    void testSetClientTls() {
        // Given
        byte[] privateKeyPem = "privateKey".getBytes();
        byte[] certificatePem = "certificate".getBytes();

        // When
        yxotlphttpspanexporterbuilder.setClientTls(privateKeyPem, certificatePem);

        // Then
        // Add assertions to verify the client TLS was set correctly
    }

    @Test
    void testSetMeterProvider() {
        // Given
        // MeterProvider is already mocked

        // When
        yxotlphttpspanexporterbuilder.setMeterProvider(meterProvider);

        // Then
        // Add assertions to verify the meter provider was set correctly
    }

    @Test
    void testBuild() {
        // When
        YXOtlpHttpSpanExporter exporter = yxotlphttpspanexporterbuilder.build();

        // Then
        assertNotNull(exporter);
    }
}
