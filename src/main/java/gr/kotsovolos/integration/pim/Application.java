package gr.kotsovolos.integration.pim;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.kotsovolos.integration.pim.config.Config;
import gr.kotsovolos.integration.pim.config.ConfigReader;
import gr.kotsovolos.integration.pim.job.CatalogImportJob;
import gr.kotsovolos.integration.pim.step.Step;

public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	
	public static void main(String[] args) {
		
		logger.info("Catalog-import tool started");
		
		String env = getEnv(args);
		
		Config config = ConfigReader.read(env);
		if (config == null) {
			logger.error("Cannot read main configuration file for env {}. Exiting aplication.", env);
			System.exit(-1);
		}
       
        List<Step> steps = new CatalogImportJob(config).getOrderedSteps();
        steps.forEach( (step) -> step.run() );
		
		logger.info("Catalog-import tool completed");
	}

	
	private static String getEnv(String[] args) {
		String env = "";
		if (args.length > 0) {
			env = args[0];
		}
		return env;
	}

}
