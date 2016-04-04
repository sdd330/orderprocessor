package com.github.sdd330.core;

import org.springframework.stereotype.Component;

import com.github.sdd330.domain.StepType;

@Component
public class SchedulingStepProcessor extends AbstractStepProcessor {

	@Override
	public StepType getStep() {
		return StepType.SCHEDULING;
	}

	@Override
	public StepType nextStep() {
		return StepType.PREPROCESSING;
	}
	
}
