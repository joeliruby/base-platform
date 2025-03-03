package com.matariky.utils;

import io.opentelemetry.exporter.internal.okhttp.OkHttpExporter;
import io.opentelemetry.exporter.internal.otlp.traces.TraceRequestMarshaler;
import io.opentelemetry.sdk.common.CompletableResultCode;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import java.util.Collection;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Exports spans using OTLP via HTTP, using OpenTelemetry's protobuf model.
 *
 * @since 1.5.0
 */
@ThreadSafe
public final class YXOtlpHttpSpanExporter implements SpanExporter {

    private final OkHttpExporter<TraceRequestMarshaler> delegate;

    YXOtlpHttpSpanExporter(OkHttpExporter<TraceRequestMarshaler> delegate) {
        this.delegate = delegate;
    }

    /**
     * Returns a new
     * {@link io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter} using
     * the default values.
     *
     * <p>
     * To load configuration values from environment variables and system
     * properties, use <a
     * href=
     * "https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions/autoconfigure">opentelemetry-sdk-extension-autoconfigure</a>.
     *
     * @return a new
     *         {@link io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter}
     *         instance.
     */
    public static YXOtlpHttpSpanExporter getDefault() {
        return builder().build();
    }

    /**
     * Returns a new builder instance for this exporter.
     *
     * @return a new builder instance for this exporter.
     */
    public static YXOtlpHttpSpanExporterBuilder builder() {
        return new YXOtlpHttpSpanExporterBuilder();
    }

    /**
     * Submits all the given spans in a single batch to the OpenTelemetry collector.
     *
     * @param spans the list of sampled Spans to be exported.
     * @return the result of the operation
     */
    @Override
    public CompletableResultCode export(@javax.annotation.Nonnull Collection<SpanData> spans) {
        TraceRequestMarshaler exportRequest = TraceRequestMarshaler.create(spans);
        return delegate.export(exportRequest, spans.size());
    }

    /**
     * The OTLP exporter does not batch spans, so this method will immediately
     * return with success.
     *
     * @return always Success
     */
    @Override
    public CompletableResultCode flush() {
        return CompletableResultCode.ofSuccess();
    }

    /** Shutdown the exporter. */
    @Override
    public CompletableResultCode shutdown() {
        return delegate.shutdown();
    }
}
