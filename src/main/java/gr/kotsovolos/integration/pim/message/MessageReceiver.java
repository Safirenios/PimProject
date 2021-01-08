package gr.kotsovolos.integration.pim.message;

import java.util.List;


public interface MessageReceiver<V> {

	List<V> receiveAndProcessMessages();
	
}
