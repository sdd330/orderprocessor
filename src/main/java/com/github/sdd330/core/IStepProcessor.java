package com.github.sdd330.core;

import com.github.sdd330.domain.Order;
import com.github.sdd330.domain.StepType;

public interface IStepProcessor {

	public StepType getStep();
	
	public StepType nextStep();
	
	public boolean processStep(Order order) throws StepProcessException;
	
}
