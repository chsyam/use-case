package com.microservices.userregistration.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {
	@Bean
	NewTopic kafkaTopic() {
		return TopicBuilder.name("user").partitions(5).build();
	}
}
