package gr.kotsovolos.integration.pim.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessorStep implements Step {
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessorStep.class);
	
	
	public ProcessorStep() {
		logger.info("Creating step: {}", getStepName());
	}
	
	@Override
	public void run() {
		logger.info("Running {}, Load data", getStepName());
		
		
		// run node app

	}

	@Override
	public String getStepName() {
		return "Step 2 (Processor)";
	}

}
