package gr.kotsovolos.integration.pim.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import gr.kotsovolos.integration.pim.config.StorageConfig;

public class JSONAzureBlobDownloader implements JSONDownloader {

	private static final Logger logger = LoggerFactory.getLogger(JSONAzureBlobDownloader.class);

	private BlobContainerClient blobContainerClient;

	public JSONAzureBlobDownloader(final StorageConfig storageConfig) {

		if (logger.isInfoEnabled()) {
			logger.info("Creating Json blob downloader.");
		}
		
		String storageAccountUrl = storageConfig.getStorageAccountUrl();
		String storageContainer = storageConfig.getStorageContainer();
		String storageSecretKey = storageConfig.getStorageSecretKey();

		if (logger.isDebugEnabled()) {
			logger.debug("Storage configuration:");
			logger.debug("Account URL = {}", storageAccountUrl);
			logger.debug("Container name = {}", storageContainer);
			logger.debug("Secret key = {}", storageSecretKey);
		}

		blobContainerClient = new BlobContainerClientBuilder().endpoint(storageConfig.getStorageAccountUrl() + "/"
				+ storageConfig.getStorageContainer() + "?" + storageConfig.getStorageSecretKey()).buildClient();

	}

	public JSONArray download(String resourceId) throws JSONDownloaderException {

		
		if(logger.isDebugEnabled()) {
			logger.debug("Downloading blob with id: {}", resourceId);
		}
		
		BlobClient blobClient = blobContainerClient.getBlobClient(resourceId);

		JSONArray outputJsonArray = new JSONArray(); // Defaulting to empty JSONArray
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			blobClient.download(outputStream);
			outputJsonArray = new JSONArray(outputStream.toString());

		} catch (IOException e) {
			logger.error("Failed to read downloaded JSON from Azure blob", e);
			throw new JSONDownloaderException(e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Json message: \n{}", outputJsonArray.toString());
		}

		return outputJsonArray;
	}

}
