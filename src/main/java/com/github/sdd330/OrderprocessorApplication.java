package com.github.sdd330;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class OrderprocessorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OrderprocessorApplication.class, args);
	}
	
}
