package gr.kotsovolos.integration.pim.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostProcessorStep implements Step {
	
	private static final Logger logger = LoggerFactory.getLogger(PostProcessorStep.class);

	public PostProcessorStep() {
		logger.info("Creating step: {}", getStepName());
	}
	
	@Override
	public void run() {
		logger.info("Running {}, Upload archive", getStepName());
		
		
		// upload csv/log to blob
		
	}

	@Override
	public String getStepName() {
		return "Step 3 (Postprocessor)";
	}

}
