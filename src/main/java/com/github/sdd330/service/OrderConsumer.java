package com.github.sdd330.service;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sdd330.config.Constants;
import com.github.sdd330.core.IStepProcessor;
import com.github.sdd330.core.StepProcessException;
import com.github.sdd330.core.StepProcessorFactory;
import com.github.sdd330.domain.Order;
import com.github.sdd330.domain.OrderRepository;
import com.github.sdd330.domain.OrderStatus;
import com.github.sdd330.domain.StepType;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@EnableRabbit
@Component
@Slf4j
public class OrderConsumer {
	final static int PROCESS_TIME = 5000;
	private final ObjectMapper mapper = new ObjectMapper();
	
    @Autowired
    private OrderRepository orderRepository;
    
	@Autowired
	private StepProcessorFactory stepProcessorFactory;
	
	private void processOrderStep(Order order, StepType step) throws StepProcessException{
		IStepProcessor processor = stepProcessorFactory.getStepProcessor(step);
		while(processor != null && processor.processStep(order)){
    		processor = stepProcessorFactory.getStepProcessor(processor.nextStep());
    	}
	}
	
    private void processOrder(Order order) throws StepProcessException{
    	if(order.getCurrentStep() == null){
    		processOrderStep(order, StepType.SCHEDULING);	
    	} else {
    		processOrderStep(order, order.getCurrentStep());
    	}
    	
    	if(order.getOrderStatus() != OrderStatus.FAILED){
    		order.setOrderStatus(OrderStatus.COMPLETED);
    	}
    	
    	orderRepository.save(order);
    }
    
	@RabbitListener(queues = Constants.QUEUE_NAME, exclusive = false, containerFactory = "rabbitListenerContainerFactory")
    public void onMessage(final Message message, final Channel channel) {
		long deliveryTag = message.getMessageProperties().getDeliveryTag();
		log.info("Received Message:" + deliveryTag);
		
		try {
			UUID orderId = mapper.readValue(message.getBody(), UUID.class);
			
			Order order = orderRepository.findOne(orderId);
			if(order == null){
				log.error("Order not found:" + orderId);
				channel.basicReject(deliveryTag, false);
				return;
			}
			
			log.info("Start Processing:" + order.getId());
			processOrder(order);
			log.info("End Processing:" + order.getId());
			
		} catch (Exception ex) {
			log.error("Message Processing Error:" + deliveryTag, ex);
			try {
				channel.basicNack(deliveryTag, false, true);
			} catch (Exception e) {
				log.error("Message Ack Error:" + deliveryTag, e);
			}
			return;
		}

		try {
			channel.basicAck(deliveryTag, false);
		} catch (Exception e) {
			log.error("Message Ack Error:" + deliveryTag, e);
		}
		
    }
	
}
