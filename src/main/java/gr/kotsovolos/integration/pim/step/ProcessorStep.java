package gr.kotsovolos.integration.pim.step;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.kotsovolos.integration.pim.config.ProcessorConfig;

public class ProcessorStep implements Step {

	private static final Logger logger = LoggerFactory.getLogger(ProcessorStep.class);
	private String nodeJSApllicationLocation, nodeJSApplicationName;

	public ProcessorStep(ProcessorConfig config) {
		logger.info("Creating step: {}", getStepName());
		nodeJSApllicationLocation = config.getNodeJSConfig().getNodeJSApllicationLocation();
		nodeJSApplicationName = config.getNodeJSConfig().getNodeJSApplicationName();
	}

	@Override
	public void run() {
		logger.info("Running {}, Load data", getStepName());

		// run node app

		String absoluteNodeJSPath = nodeJSApllicationLocation + File.separator + nodeJSApplicationName;
		logger.info("Constructed absolute path for nodeJS app : {}", absoluteNodeJSPath);

//		ProcessBuilder pb = new ProcessBuilder("node", "app.js");
//		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//		pb.redirectError(ProcessBuilder.Redirect.INHERIT);
//		try {
//			Process p = pb.start();
//
//			List<String> results = readOutput(p.getInputStream());
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}

	}

	@Override
	public String getStepName() {
		return "Step 2 (Processor)";
	}

}
