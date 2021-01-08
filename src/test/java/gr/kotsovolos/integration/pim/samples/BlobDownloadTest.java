package gr.kotsovolos.integration.pim.samples;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

class BlobDownloadTest {
	
	private static final String STORAGE_ACCOUNT_URL = "https://kotsovolosproduction.blob.core.windows.net";
	private static final String STORAGE_CONTAINER = "inriver";
	private static final String STORAGE_SECRET_KEY = "sv=2019-12-12&si=kotsovolos&sr=c&sig=6O9fQZgp6CRTMXcVbf7UkmTryTgv23rB24/rGsVMxCE=";

	@Test
	void should_DownloadJSON_From_Blob() {
		
		BlobContainerClient blobContainerClient = new BlobContainerClientBuilder()
			    .endpoint(STORAGE_ACCOUNT_URL + "/" + STORAGE_CONTAINER + "?" + STORAGE_SECRET_KEY)
			    .buildClient();
		
		
		
		String[] blobs = new String[]{"catalog/20201221104659384", "catalog/20201221104712290", "catalog/20201221104712650", "catalog/20201221104712962" , "catalog/20201221104713196", "catalog/20201221102629711", "catalog/20201221102914789"};
		
		for (String blob : blobs) {
			BlobClient blobClient = blobContainerClient.getBlobClient(blob);
			try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			    blobClient.download(outputStream);
			    
			    JSONArray file = new JSONArray(outputStream.toString());
			    
			    System.out.println("Blob: " + blob);
			    System.out.println(file);
			    System.out.println("##########");
			    
			} catch (IOException e) {
			    e.printStackTrace();
			}			
		}
		
		
		
		

		
	}

}
