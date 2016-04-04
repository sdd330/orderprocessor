package com.github.sdd330.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.sdd330.domain.StepType;

@Component
public class StepProcessorFactory {
	
	@Autowired
	SchedulingStepProcessor schedulingStepProcessor;
	
	@Autowired
	PreProcessingStepProcessor preProcessingStepProcessor;
	
	@Autowired
	ProcessingStepProcessor processingStepProcessor;
	
	@Autowired
	PostProcessingStepProcessor postProcessingStepProcessor;
	
	public IStepProcessor getStepProcessor(StepType step){
		
		if(step == null){
			return null;
		}
		
		switch(step){
			case SCHEDULING:
				return schedulingStepProcessor;
			case PREPROCESSING:
				return preProcessingStepProcessor;
			case PROCESSING:
				return processingStepProcessor;
			case POSTPROCESSING:
				return postProcessingStepProcessor;
			default:
				return null;
		}
		
	}
	
}
