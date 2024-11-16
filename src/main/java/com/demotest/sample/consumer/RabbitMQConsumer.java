package com.demotest.sample.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demotest.sample.dto.User;
import com.demotest.sample.producer.RabbitMQProducer;

@Service
public class RabbitMQConsumer {
		
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void receiveMessage(String message) {
		logger.info(String.format("Message Received ---> %s", message));
    }
    
    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void receiveJsonMessage(User user) {
		logger.info(String.format("Json Message Received ---> %s", user.toString()));
    }

}
