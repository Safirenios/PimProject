package gr.kotsovolos.integration.pim.config;

public class StorageConfig {
	
	private String storageAccountUrl;
	private String storageContainer;
	private String storageSecretKey;
	
	public String getStorageAccountUrl() {
		return storageAccountUrl;
	}
	public void setStorageAccountUrl(String storageAccountUrl) {
		this.storageAccountUrl = storageAccountUrl;
	}
	public String getStorageContainer() {
		return storageContainer;
	}
	public void setStorageContainer(String storageContainer) {
		this.storageContainer = storageContainer;
	}
	public String getStorageSecretKey() {
		return storageSecretKey;
	}
	public void setStorageSecretKey(String storageSecretKey) {
		this.storageSecretKey = storageSecretKey;
	}
	
}
