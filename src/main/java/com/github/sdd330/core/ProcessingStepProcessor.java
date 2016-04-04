package com.github.sdd330.core;

import org.springframework.stereotype.Component;

import com.github.sdd330.domain.StepType;

@Component
public class ProcessingStepProcessor extends AbstractStepProcessor {
	
	@Override
	public StepType getStep() {
		return StepType.PROCESSING;
	}

	@Override
	public StepType nextStep() {
		return StepType.POSTPROCESSING;
	}
	
}
