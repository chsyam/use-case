package com.microservices.imageupload.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {
	@Bean
	public NewTopic kafkaTopic() {
		return TopicBuilder.name("imageUpload").partitions(5).build();
	}
}
