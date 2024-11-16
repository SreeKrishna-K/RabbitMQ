package com.demotest.sample.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routingKey.name}")
	private String routingKey;
	
	@Value("${rabbitmq.json.queue.name}")
	private String jsonQueue;
	
	@Value("${rabbitmq.json.routingKey.name}")
	private String jsonRoutingKey;
	
	
	// spring bean for rabbitmq queue
	@Bean
	Queue queue() {
		return new Queue(queue,true);
	}
	
	@Bean
	Queue jsonQueue() {
		System.out.println(jsonQueue);
		return new Queue(jsonQueue,true);
	}
	
	@Bean
	TopicExchange exchange() {
		System.out.println(exchange);
		return new TopicExchange(exchange);
	}
	
	@Bean
	Binding binding() {
		System.out.println("Inside Binding Method");
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}
	
	@Bean
	Binding jsonBinding() {
		System.out.println(jsonRoutingKey);
		return BindingBuilder.bind(jsonQueue()).to(exchange()).with(jsonRoutingKey);
	}
	
	@Bean
	MessageConverter converter(){
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}