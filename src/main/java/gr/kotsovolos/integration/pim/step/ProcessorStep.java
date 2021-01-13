package gr.kotsovolos.integration.pim.step;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.kotsovolos.integration.pim.config.ProcessorConfig;

public class ProcessorStep implements Step {

	private static final Logger logger = LoggerFactory.getLogger(ProcessorStep.class);
	private String nodeJSApllicationLocation, nodeJSApplicationName, nodeJSCommand;

	public ProcessorStep(ProcessorConfig config) {
		logger.info("Creating step: {}", getStepName());
		nodeJSApllicationLocation = config.getNodeJSConfig().getNodeJSApllicationLocation();
		nodeJSApplicationName = config.getNodeJSConfig().getNodeJSApplicationName();
		nodeJSCommand = config.getNodeJSConfig().getNodeCommand();
	}

	@Override
	public void run() {
		logger.info("Running {}, Load data", getStepName());

		// run node app

		// Create the location of the nodeJS script.
		String absoluteNodeJSPath = nodeJSApllicationLocation + File.separator + nodeJSApplicationName;
		logger.info("Constructed absolute path for nodeJS app is: {}", absoluteNodeJSPath);

		// create and run the Process Builder
		ProcessBuilder pb = new ProcessBuilder(nodeJSCommand, absoluteNodeJSPath);
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		pb.redirectError(ProcessBuilder.Redirect.INHERIT);

		try {
			Process p = pb.start();
			int resultStatust = p.waitFor();
			logger.info("Result of running script: " + nodeJSApplicationName + " is: " + (resultStatust == 0 ? "Success" : "Failure"));

		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}

	}

	@Override
	public String getStepName() {
		return "Step 2 (Processor)";
	}

}
