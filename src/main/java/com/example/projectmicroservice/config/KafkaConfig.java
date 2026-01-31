package com.example.projectmicroservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createTopic() {
       return TopicBuilder.name("payment-created-topic")
               .partitions(3)
               .replicas(3)
               .configs(Map.of("min.insync.replicas", "1"))
               .build();
    }
}
