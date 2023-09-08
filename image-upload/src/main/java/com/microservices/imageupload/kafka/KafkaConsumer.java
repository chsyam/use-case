//package com.microservices.imageupload.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import com.microservices.imageupload.bean.Image;
//
//@Service
//public class KafkaConsumer {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
//
//	@KafkaListener(topics = "imageUpload", groupId = "myGroup")
//	public void consume(Image image) {
//		LOGGER.info(String.format("Message received"));
//	}
//}
