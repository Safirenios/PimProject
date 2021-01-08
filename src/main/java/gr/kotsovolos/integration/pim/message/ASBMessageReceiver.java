package gr.kotsovolos.integration.pim.message;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusClientBuilder.ServiceBusReceiverClientBuilder;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;

import gr.kotsovolos.integration.pim.config.PreprocessorConfig;
import gr.kotsovolos.integration.pim.config.ServiceBusConfig;
import gr.kotsovolos.integration.pim.message.transformer.JsonToCsvTransformer;
import gr.kotsovolos.integration.pim.message.transformer.Transformer;

public class ASBMessageReceiver implements MessageReceiver<DataloadCSVFile> {

	private static final Logger logger = LoggerFactory.getLogger(ASBMessageReceiver.class);

	private ServiceBusReceiverClientBuilder receiverBuilder;

	private int batchSize;
	private long maxWaitTimeInSeconds;

	private JSONDownloader downloader;
	private Transformer<JSONArray, List<DataloadCSVFile>> transfomer;

	public ASBMessageReceiver(PreprocessorConfig config) {
		/*
		 * Create service bus builder
		 */
		ServiceBusConfig serviceBusConfig = config.getServiceBusConfig();
		String connectionString = serviceBusConfig.getConnectionString();
		String topicName = serviceBusConfig.getTopicName();
		String subscriptionName = serviceBusConfig.getSubscriptionName();

		batchSize = config.getServiceBusConfig().getBatchSize();
		maxWaitTimeInSeconds = config.getServiceBusConfig().getMaxWaitTimeInSeconds();

		if (logger.isDebugEnabled()) {
			logger.debug("Service bus configuration:");
			logger.debug("Connection string = {}", connectionString);
			logger.debug("Topic name = {}", topicName);
			logger.debug("Subscription name = {}", subscriptionName);
			logger.debug("Batch size = {}", batchSize);
			logger.debug("Max wait time (in sec) = {}", maxWaitTimeInSeconds);
		}

		receiverBuilder = new ServiceBusClientBuilder()
				.connectionString(connectionString)
				.receiver()
				.disableAutoComplete()
				.topicName(topicName)
				.subscriptionName(subscriptionName);

		/*
		 * Create JSON downloader
		 */
		this.downloader = new JSONAzureBlobDownloader(config.getStorageConfig());

		/*
		 * Create Transformer JSON -> CSV
		 */
		this.transfomer = new JsonToCsvTransformer();
	}

	@Override
	public List<DataloadCSVFile> receiveAndProcessMessages() {

		int counter = 0;
		List<DataloadCSVFile> allCsv = new ArrayList<DataloadCSVFile>();
		
		// Build client and open connection
		try (ServiceBusReceiverClient receiver = receiverBuilder.buildClient()) {
			
			do {
				counter++;
				logger.debug("Receiving message {} from batch", counter);

				Consumer<? super ServiceBusReceivedMessage> receiveAndProcessMessage = message -> {
					String attachmentBlob = (String) message.getApplicationProperties().get("$attachment.blob");

					try {
						JSONArray jsonArray = downloader.download(attachmentBlob);
						List<DataloadCSVFile> transform = transfomer.transform(jsonArray);

						// add json to allJons list
						allCsv.addAll(transform);

						// all is OK, we can complete the current message
						receiver.complete(message);

					} catch (JSONDownloaderException e) {
						logger.error("Failed to download/read JSON from blob. Abanding the message on the service bus for message correlation id: " + message.getCorrelationId(), e);
						receiver.abandon(message);
					}
				};
				
				receiver.receiveMessages(1, Duration.ofSeconds(maxWaitTimeInSeconds)).stream().forEach(receiveAndProcessMessage);

				logger.debug("Message {} receiving and processing complete", counter);

			} while (counter <= batchSize);
		}


		return allCsv;

	}

}
