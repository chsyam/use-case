//package com.microservices.userregistration.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//import com.microservices.userregistration.bean.User;
//
//@Service
//
//public class KafkaProducer {
//
//	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
//
//	@Autowired
//	private KafkaTemplate<String, User> kafkatemplate;
//
//	public KafkaProducer(KafkaTemplate<String, User> kafkatemplate) {
//		this.kafkatemplate = kafkatemplate;
//	}
//
//	public void sendMessage(User user) {
//		logger.info(String.format("Message sent -> %s", user.toString()));
//		Message<User> msg = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, "user").build();
//		kafkatemplate.send(msg);
//	}
//}

package com.microservices.userregistration.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.microservices.userregistration.bean.User;

@Service

public class KafkaProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, User> kafkatemplate;

	public KafkaProducer(KafkaTemplate<String, User> kafkatemplate) {
		this.kafkatemplate = kafkatemplate;
	}

	public void sendMessage(User user) {
		logger.info(String.format("Message sent -> %s", user.getName()));
		Message<User> msg = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, "user").build();
		kafkatemplate.send(msg);
	}
}
