package gr.kotsovolos.integration.pim.samples;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusClientBuilder.ServiceBusReceiverClientBuilder;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;

class ServiceBusReceiveTest {

	private static final String CONNECTION_STRING = "Endpoint=sb://it-kotso-test.servicebus.windows.net/;SharedAccessKeyName=pim-sender-listener;SharedAccessKey=5cRuXOENMeYfkgiC96aiyeHciL+PxUV3C3nJX/p5eCE=";
	private static final String TOPIC_NAME = "pim-topic";
	private static final String SUBSCRIPTION_NAME = "pim-subscription";
	
	
	private static final String KOTSO_QA_CONNECTION_STRING = "Endpoint=sb://kotsovolos-production-servicebus.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=9PCP+PXKIYdBEO+XHkX1s2rRKp9N8p+vPS6w3ZocYVU=";
	private static final String KOTSO_TOPIC_NAME = "catalog-topic";
	private static final String KOTSO_SUBSCRIPTION_NAME = "hcl-kotsovolos-subscription";
	
	static int counter = 0;
	
	@Test
	void receive() {
		
		// Igor
		ServiceBusReceiverClientBuilder subscriptionName = new ServiceBusClientBuilder()
				.connectionString(CONNECTION_STRING)
				.receiver() 
				.topicName(TOPIC_NAME)
				.subscriptionName(SUBSCRIPTION_NAME);
		
		// Kotso
//		ServiceBusReceiverClientBuilder subscriptionName = new ServiceBusClientBuilder()
//		.connectionString(KOTSO_QA_CONNECTION_STRING)
//		.receiver() 
//		.topicName(KOTSO_TOPIC_NAME)
//		.subscriptionName(KOTSO_SUBSCRIPTION_NAME);

		
		
		
		final ServiceBusReceiverClient receiver = subscriptionName.buildClient();

		
		
//		for (int i = 0; i < 99; i++) {
//			receiver.receiveMessages(1).stream().forEach((message) -> {
////				printMessage(message);
//				
//				System.out.println(++counter);
//				
//				receiver.complete(message);
//				
//				assertNotNull(message);
//				
//			});
//		}
		
		receiver.receiveMessages(5).stream().forEach((message) -> {
			printMessage(message);
			
			System.out.println(++counter);
			
			receiver.complete(message);
//			receiver.abandon(message);
			
			assertNotNull(message);
			
		});
		
		receiver.close();
		
		
//		ServiceBusReceiverClient receiver2 = subscriptionName.buildClient();
//		receiver2.receiveMessages(1).stream().forEach((message) -> {
//			printMessage(message);
//			
//			receiver2.complete(message);
//			
//			assertNotNull(message);
//			
//		});
		
		
	}

	private void printMessage(ServiceBusReceivedMessage message) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("getContentType: ").append(message.getContentType()).append("\n")
			.append("getCorrelationId: ").append(message.getCorrelationId()).append("\n")
			.append("getDeadLetterErrorDescription: ").append(message.getDeadLetterErrorDescription()).append("\n")
			.append("getDeadLetterReason: ").append(message.getDeadLetterReason()).append("\n")
			.append("getDeadLetterSource: ").append(message.getDeadLetterSource()).append("\n")
			.append("getDeliveryCount: ").append(message.getDeliveryCount()).append("\n")
			.append("getEnqueuedSequenceNumber: ").append(message.getEnqueuedSequenceNumber()).append("\n")
			.append("getLockToken: ").append(message.getLockToken()).append("\n")
			.append("getMessageId: ").append(message.getMessageId()).append("\n")
			.append("getPartitionKey: ").append(message.getPartitionKey()).append("\n")
			.append("getReplyTo: ").append(message.getReplyTo()).append("\n")
			.append("getReplyToSessionId: ").append(message.getReplyToSessionId()).append("\n")
			.append("getSequenceNumber: ").append(message.getSequenceNumber()).append("\n")
			.append("getSessionId: ").append(message.getSessionId()).append("\n")
			.append("getSubject: ").append(message.getSubject()).append("\n")
			.append("getTo: ").append(message.getTo()).append("\n")
			.append("getApplicationProperties: ").append(message.getApplicationProperties()).append("\n")
			.append("getBody: ").append(message.getBody()).append("\n")
			.append("getEnqueuedTime: ").append(message.getEnqueuedTime()).append("\n")
			.append("getExpiresAt: ").append(message.getExpiresAt()).append("\n")
			.append("getLockedUntil: ").append(message.getLockedUntil()).append("\n")
			
			.append("getRawAmqpMessage: ").append(message.getRawAmqpMessage()).append("\n")
			.append("getRawAmqpMessage.getApplicationProperties: ").append(message.getRawAmqpMessage().getApplicationProperties()).append("\n")
			.append("getRawAmqpMessage.getBody: ").append(message.getRawAmqpMessage().getBody()).append("\n")
			.append("getRawAmqpMessage.getDeliveryAnnotations: ").append(message.getRawAmqpMessage().getDeliveryAnnotations()).append("\n")
			.append("getRawAmqpMessage.getFooter: ").append(message.getRawAmqpMessage().getFooter()).append("\n")
			.append("getRawAmqpMessage.getHeader: ").append(message.getRawAmqpMessage().getHeader()).append("\n")
			.append("getRawAmqpMessage.getMessageAnnotations: ").append(message.getRawAmqpMessage().getMessageAnnotations()).append("\n")
			.append("getRawAmqpMessage.getProperties: ").append(message.getRawAmqpMessage().getProperties()).append("\n")
			
			.append("getScheduledEnqueueTime: ").append(message.getScheduledEnqueueTime()).append("\n")
			.append("getTimeToLive: ").append(message.getTimeToLive()).append("\n");
			
		System.err.println(sb);
		
	}

}
