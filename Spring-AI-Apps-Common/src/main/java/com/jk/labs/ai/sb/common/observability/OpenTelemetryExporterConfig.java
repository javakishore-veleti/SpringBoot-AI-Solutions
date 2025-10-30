package com.jk.labs.ai.sb.common.observability;

import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryExporterConfig {

    @Bean
    public OtlpGrpcLogRecordExporter otelLogRecordExporter(@Value("${opentelemetry.exporter.oltp.endpoint}")String url) {
        return OtlpGrpcLogRecordExporter.builder().setEndpoint(url).build();
    }

    @Bean
    public OtlpGrpcSpanExporter otlpGrpcSpanExporter(@Value("${opentelemetry.exporter.oltp.endpoint}")String url) {
        return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
    }
}
