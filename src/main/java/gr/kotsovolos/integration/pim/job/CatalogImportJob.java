package gr.kotsovolos.integration.pim.job;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.kotsovolos.integration.pim.config.Config;
import gr.kotsovolos.integration.pim.step.Step;
import gr.kotsovolos.integration.pim.step.StepBuilder;

public class CatalogImportJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(CatalogImportJob.class);

	private Config config;

	public CatalogImportJob(Config config) {
		this.config = config;
		logger.info("Application configuration {}", config.toString());
	}

	public List<Step> getOrderedSteps() {

		List<Step> steps = new ArrayList<Step>();

		/*
		 * Preprocessor step
		 */
		steps.add(new StepBuilder().preprocessor().config(config.getPreprocessorConfig()).build());

		/*
		 * Processor step
		 */
		steps.add(new StepBuilder().processor().config(config.getProcessorConfig()).build());

		/*
		 * Post processor step
		 */
		steps.add(new StepBuilder().postprocessor().build());

		return steps;
	}

}
