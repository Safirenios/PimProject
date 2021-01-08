package gr.kotsovolos.integration.pim.config;

public class PreprocessorConfig {
	
	private ServiceBusConfig serviceBusConfig = new ServiceBusConfig();
	private StorageConfig storageConfig = new StorageConfig();
	
	public ServiceBusConfig getServiceBusConfig() {
		return serviceBusConfig;
	}
	
	public StorageConfig getStorageConfig() {
		return storageConfig;
	}

}
