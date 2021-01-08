package gr.kotsovolos.integration.pim.samples;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

class ServiceBusSendToTopicTest {

	private static final String CONNECTION_STRING = "Endpoint=sb://it-kotso-test.servicebus.windows.net/;SharedAccessKeyName=pim-sender-listener;SharedAccessKey=5cRuXOENMeYfkgiC96aiyeHciL+PxUV3C3nJX/p5eCE=";
	private static final String TOPIC_NAME = "pim-topic";

	private static ServiceBusSenderClient sender;
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("before all");
		sender = new ServiceBusClientBuilder()
				.connectionString(CONNECTION_STRING)
				.sender()
				.topicName(TOPIC_NAME)
				.buildClient();
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("after all");
		sender.close();
	}
	
	
	@RepeatedTest(500)
	void should_SendMessage_To_Topic() {
		
		// given
		String id = String.valueOf(System.currentTimeMillis());
		String body = "{id: " + id + "}";
		
		ServiceBusMessage msg = new ServiceBusMessage(body).setMessageId(id);
		msg.getApplicationProperties().put("ActionType", "Updated");
		msg.getApplicationProperties().put("SchemaVersion", "1.0.0.0");
		msg.getApplicationProperties().put("EventType", "Update");
		msg.getApplicationProperties().put("Batch", "0");
		msg.getApplicationProperties().put("CorrelationId", "20201221102914789");
		msg.getApplicationProperties().put("Id", "110103");
		msg.getApplicationProperties().put("Timestamp", "Mon Dec 21 11:29:14 CET 2020");
		msg.getApplicationProperties().put("CatalogId", "253313");
		msg.getApplicationProperties().put("$attachment.blob", "catalog/20201221102914789");
		
		// when
		sender.sendMessage(msg);
		System.out.println("Message sent: " + body);
		
		// then
		assertTrue(true);
	}
	
}


































