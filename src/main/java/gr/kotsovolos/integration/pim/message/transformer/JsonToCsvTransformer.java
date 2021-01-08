package gr.kotsovolos.integration.pim.message.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.kotsovolos.integration.pim.message.DataloadCSVFile;

public class JsonToCsvTransformer implements Transformer<JSONArray, List<DataloadCSVFile>> {

	private static final Logger logger = LoggerFactory.getLogger(JsonToCsvTransformer.class);
	
	public JsonToCsvTransformer() {
		logger.info("Creating Json to CSV transformer.");
	}
	
	@Override
	public List<DataloadCSVFile> transform(JSONArray input) {
		
		logger.debug("Running transformation on file");
		
		// TODO: Dummy implementation
		List<DataloadCSVFile> output = new ArrayList<DataloadCSVFile>();
		StreamSupport.stream(input.spliterator(), false).map(JSONObject.class::cast).forEach((json) -> {
			
			DataloadCSVFile file = new DataloadCSVFile();
			file.setType("Product");
			file.setCsv(json.toString().toUpperCase());
			
			output.add(file);
		});
		
		return output;
	}

}
