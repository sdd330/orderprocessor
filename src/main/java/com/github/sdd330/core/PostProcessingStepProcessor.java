package com.github.sdd330.core;

import org.springframework.stereotype.Component;

import com.github.sdd330.domain.StepType;

@Component
public class PostProcessingStepProcessor extends AbstractStepProcessor {
	
	@Override
	public StepType getStep() {
		return StepType.POSTPROCESSING;
	}

	@Override
	public StepType nextStep() {
		return null;
	}

}
