package com.github.sdd330.service;

import java.util.Calendar;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sdd330.config.Constants;
import com.github.sdd330.domain.Order;
import com.github.sdd330.domain.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

	@Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;
    
    @Autowired
    private OrderRepository orderRepository;
    
    public Order newOrder() {
    	Order order = new Order();
    	order.setOrderDate(Calendar.getInstance().getTime());
    	orderRepository.saveAndFlush(order);
    	log.info("New order created:" + order.getId());
    	rabbitMessagingTemplate.convertAndSend(Constants.EXCHAGE_NAME, Constants.QUEUE_NAME, order.getId());
    	return order;
    }
    
    public Order queryOrder(UUID orderId) {
    	return orderRepository.findOne(orderId);
    }
    
}
