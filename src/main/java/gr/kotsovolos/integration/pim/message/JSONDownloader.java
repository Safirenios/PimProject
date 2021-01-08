package gr.kotsovolos.integration.pim.message;

import org.json.JSONArray;

public interface JSONDownloader {
	
	JSONArray download(String resouceId) throws JSONDownloaderException;

}
