package gr.kotsovolos.integration.pim.message;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gr.kotsovolos.integration.pim.config.ConfigReader;

class JSONAzureBlobDownloaderTest {

	private static JSONDownloader downloader;
	
	@BeforeAll
	static void beforeAll() {
		String localEnv = "";
		downloader = new JSONAzureBlobDownloader(ConfigReader.read(localEnv).getPreprocessorConfig().getStorageConfig());
	}
	
	@Test
	void testDownload() throws JSONDownloaderException {
		
		// given
		String blobId = "catalog/20201221102914789";
		
		// when
		JSONArray jsonObject = downloader.download(blobId);
		
		// then
		assertNotNull(jsonObject);
		assertTrue(jsonObject.length() > 0);
		assertNotNull(((JSONObject) jsonObject.get(0)).get("EntityType"));
	}

}
