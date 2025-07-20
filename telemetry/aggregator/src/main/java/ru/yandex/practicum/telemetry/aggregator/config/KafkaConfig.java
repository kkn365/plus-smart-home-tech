package ru.yandex.practicum.telemetry.aggregator.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Properties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("aggregator.kafka")
public class KafkaConfig {
    private final ProducerConfig producer;
    private final ConsumerConfig consumer;

    @Setter
    @Getter
    @AllArgsConstructor
    public static class ProducerConfig {
        private String topic;
        private Properties properties;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class ConsumerConfig {
        private String topic;
        private Duration pollTimeout;
        private Properties properties;
    }
}
