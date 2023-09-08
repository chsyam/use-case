package com.microservices.imageupload.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.microservices.imageupload.bean.Image;

@Service

public class KafkaProducer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired(required = true)

	private KafkaTemplate<String, Image> kafkatemplate;

	public KafkaProducer() {

		super();

	}

	public KafkaProducer(KafkaTemplate<String, Image> kafkatemplate) {
		super();
		this.kafkatemplate = kafkatemplate;
	}

	public void sendMessage(Image image) {
		logger.info(String.format("Message sent -> %s", image.toString()));
		Message<Image> msg = MessageBuilder.withPayload(image).setHeader(KafkaHeaders.TOPIC, "imageUpload").build();
		kafkatemplate.send(msg);
	}

}
