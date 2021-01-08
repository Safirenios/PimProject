package gr.kotsovolos.integration.pim.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.kotsovolos.integration.pim.config.PreprocessorConfig;
import gr.kotsovolos.integration.pim.message.ASBMessageReceiver;
import gr.kotsovolos.integration.pim.message.DataloadCSVFile;
import gr.kotsovolos.integration.pim.message.MessageReceiver;

/**
 * 
 * @author trail
 *
 */
public class PreprocessorStep implements Step {

	private static final Logger logger = LoggerFactory.getLogger(PreprocessorStep.class);
	private MessageReceiver<DataloadCSVFile> messageReceiver;

	public PreprocessorStep(PreprocessorConfig config) {
		logger.info("Creating step: {}", getStepName());
		this.messageReceiver = new ASBMessageReceiver(config);
	}

	@Override
	public void run() {
		logger.info("Running step: {}", getStepName());

		// Get transformed messages
		List<DataloadCSVFile> transformedMessages = messageReceiver.receiveAndProcessMessages();
		
		// Consolidate CSV files
		
		
		// Save to file system
		transformedMessages.forEach((csv) -> csv.save());	// DUMMY code to save to file
	}

	@Override
	public String getStepName() {
		return "Step 1 (Preprocessor)";
	}

}
