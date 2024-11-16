package com.demotest.sample.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demotest.sample.dto.User;

@Service
public class RabbitMQProducer {
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routingKey.name}")
	private String routingKey;
	
	@Value("${rabbitmq.json.routingKey.name}")
	private String jsonRoutingKey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);
	
	private RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(String message) {
		logger.info(String.format("Message Sent ---> %s", message));
		rabbitTemplate.convertAndSend(exchange,routingKey,message);
	}
	
	public void sendJsonMessage(User user) {
		logger.info(String.format("Json Message Sent ---> %s", user.toString()));
		try {
		 rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
