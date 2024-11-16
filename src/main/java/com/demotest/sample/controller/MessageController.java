package com.demotest.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demotest.sample.consumer.RabbitMQConsumer;
import com.demotest.sample.dto.User;
import com.demotest.sample.producer.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
	
	@Autowired
	private RabbitMQProducer producer;
	
	@Autowired
	private RabbitMQConsumer consumer;
	
	
	public MessageController(RabbitMQProducer producer) {
		this.producer = producer;
	}
	
	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
		producer.sendMessage(message);
		return ResponseEntity.ok("Message send to RabbitMQ.");
	}
	
	@PostMapping("/publishJson")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
		producer.sendJsonMessage(user);
		return ResponseEntity.ok("Json Message send to RabbitMQ.");
	}

}
