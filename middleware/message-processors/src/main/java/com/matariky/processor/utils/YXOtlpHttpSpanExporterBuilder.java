package com.matariky.processor.utils;

import static io.opentelemetry.api.internal.Utils.checkArgument;
import static java.util.Objects.requireNonNull;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.MeterProvider;
import io.opentelemetry.exporter.internal.okhttp.OkHttpExporterBuilder;
import io.opentelemetry.exporter.internal.otlp.OtlpUserAgent;
import io.opentelemetry.exporter.internal.otlp.traces.TraceRequestMarshaler;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Builder utility for {@link OtlpHttpSpanExporter}.
 *
 * @since 1.5.0
 */
public final class YXOtlpHttpSpanExporterBuilder {

    private static final String DEFAULT_ENDPOINT = "http://localhost:4318/v1/traces";

    private final OkHttpExporterBuilder<TraceRequestMarshaler> delegate;

    YXOtlpHttpSpanExporterBuilder() {
        delegate = new OkHttpExporterBuilder<>("otlp", "span", DEFAULT_ENDPOINT);
        delegate.exportAsJson();
       // delegate.addHeader("signoz-access-token","cf258545-4c68-4f5b-a722-dd60b07e0a3e");
        OtlpUserAgent.addUserAgentHeader(delegate::addHeader);
    }

    public YXOtlpHttpSpanExporterBuilder setTimeout(long timeout, TimeUnit unit) {
        requireNonNull(unit, "unit");
        checkArgument(timeout >= 0, "timeout must be non-negative");
        delegate.setTimeout(timeout, unit);
        return this;
    }

    public YXOtlpHttpSpanExporterBuilder setTimeout(Duration timeout) {
        requireNonNull(timeout, "timeout");
        return setTimeout(5000l, TimeUnit.NANOSECONDS);
    }

    /**
     * Sets the OTLP endpoint to connect to. If unset, defaults to {@value DEFAULT_ENDPOINT}. The
     * endpoint must start with either http:// or https://, and include the full HTTP path.
     */
    public YXOtlpHttpSpanExporterBuilder setEndpoint(String endpoint) {
        requireNonNull(endpoint, "endpoint");
        delegate.setEndpoint(endpoint);
        return this;
    }

    /**
     * Sets the method used to compress payloads. If unset, compression is disabled. Currently
     * supported compression methods include "gzip" and "none".
     */
    public YXOtlpHttpSpanExporterBuilder setCompression(String compressionMethod) {
        requireNonNull(compressionMethod, "compressionMethod");
        checkArgument(
                compressionMethod.equals("gzip") || compressionMethod.equals("none"),
                "Unsupported compression method. Supported compression methods include: gzip, none.");
        delegate.setCompression(compressionMethod);
        return this;
    }

    /** Add header to requests. */
    public YXOtlpHttpSpanExporterBuilder addHeader(String key, String value) {
        delegate.addHeader(key, value);
        return this;
    }

    /**
     * Sets the certificate chain to use for verifying servers when TLS is enabled. The {@code byte[]}
     * should contain an X.509 certificate collection in PEM format. If not set, TLS connections will
     * use the system default trusted certificates.
     */
    public YXOtlpHttpSpanExporterBuilder setTrustedCertificates(byte[] trustedCertificatesPem) {
        delegate.configureTrustManager(trustedCertificatesPem);
        return this;
    }

    /**
     * Sets ths client key and the certificate chain to use for verifying client when TLS is enabled.
     * The key must be PKCS8, and both must be in PEM format.
     */
    public YXOtlpHttpSpanExporterBuilder setClientTls(byte[] privateKeyPem, byte[] certificatePem) {
        delegate.configureKeyManager(privateKeyPem, certificatePem);
        return this;
    }

    /**
     * Sets the {@link MeterProvider} to use to collect metrics related to export. If not set, uses
     * {@link GlobalOpenTelemetry#getMeterProvider()}.
     */
    public YXOtlpHttpSpanExporterBuilder setMeterProvider(MeterProvider meterProvider) {
        requireNonNull(meterProvider, "meterProvider");
        delegate.setMeterProvider(meterProvider);
        return this;
    }

    /**
     * Constructs a new instance of the exporter based on the builder's values.
     *
     * @return a new exporter's instance
     */
    public YXOtlpHttpSpanExporter build() {
        return new YXOtlpHttpSpanExporter(delegate.build());
    }
}
