package com.github.sdd330.core;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.sdd330.domain.Order;
import com.github.sdd330.domain.OrderRepository;
import com.github.sdd330.domain.Step;
import com.github.sdd330.domain.StepStatus;
import com.github.sdd330.domain.StepRepository;
import com.github.sdd330.domain.OrderStatus;

public abstract class AbstractStepProcessor implements IStepProcessor {
	
	@Autowired
	protected StepRepository stepRepository;
	
    @Autowired
    private OrderRepository orderRepository;
	
	protected final static int STEP_TIMOUT = 5000;
	protected final static double FAILURE_RATE = 0.05;  
	protected static boolean doWork() throws InterruptedException{
		Thread.sleep(STEP_TIMOUT);
		if(Math.random() > FAILURE_RATE){
			return true;
		}
		return false;
	}
	
	protected boolean internalProcessStep(Order order) throws Exception {
		return doWork();
	}
	
	private Step prepareStep(Order order){
		Step step = new Step();
		step.setOrder(order);
		step.setStep(getStep());
		step.setStartTime(Calendar.getInstance().getTime());
		stepRepository.saveAndFlush(step);
		
		order.setCurrentStep(getStep());
		orderRepository.saveAndFlush(order);
		return step;
	}
	
	@Override
    public boolean processStep(Order order) throws StepProcessException {
		try {
			Step step = prepareStep(order);
			if(internalProcessStep(order)){
				step.setEndTime(Calendar.getInstance().getTime());
				step.setStepStatus(StepStatus.COMPLETED);
				stepRepository.saveAndFlush(step);
				return true;
			} else {
				step.setEndTime(Calendar.getInstance().getTime());
				step.setStepStatus(StepStatus.FAILED);
				order.setOrderStatus(OrderStatus.FAILED);
				stepRepository.saveAndFlush(step);
			}
		} catch (Exception ex) {
			String fault = String.format("Process failed for order %s at step %s", order.getId(), getStep());
			throw new StepProcessException(fault, ex);
		}
		return false;
	}
	
}
