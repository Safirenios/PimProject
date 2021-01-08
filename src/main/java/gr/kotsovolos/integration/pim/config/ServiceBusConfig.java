package gr.kotsovolos.integration.pim.config;

public class ServiceBusConfig {
	
	private String connectionString;
	private String topicName;
	private String subscriptionName;
	
	/**
	 * Batch size for retrieving messages in batches from service bus. Defaulting to 10
	 */
	private int batchSize = 10;
	
	/**
	 * Max time for batch to wait on a topic to get populated with batch size messages. Defaulting to 15s
	 */
	private long maxWaitTimeInSeconds = 15;
	
	
	
	
	public String getConnectionString() {
		return connectionString;
	}
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getSubscriptionName() {
		return subscriptionName;
	}
	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
	}
	public int getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	public long getMaxWaitTimeInSeconds() {
		return maxWaitTimeInSeconds;
	}
	public void setMaxWaitTimeInSeconds(long maxWaitTimeInSeconds) {
		this.maxWaitTimeInSeconds = maxWaitTimeInSeconds;
	}

}
