package com.github.sdd330.web;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.sdd330.domain.Order;
import com.github.sdd330.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderRequestController {

    @Autowired
    protected OrderService orderService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public UUID newOrder() {
    	Order order = orderService.newOrder();
    	return order.getId();
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Order queryOrder(@RequestParam String orderId) {
    	return orderService.queryOrder(UUID.fromString(orderId));
    }
    
}