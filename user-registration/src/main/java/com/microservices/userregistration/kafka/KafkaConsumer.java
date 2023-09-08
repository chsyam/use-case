package com.microservices.userregistration.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microservices.userregistration.bean.User;

@Service
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "user", groupId = "myGroup")
	public void consume(User user) {
		LOGGER.info(String.format("Message received"));
		LOGGER.info(user.toString());
	}

}
