package gr.kotsovolos.integration.pim.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataloadCSVFile {
	
	private static final Logger logger = LoggerFactory.getLogger(DataloadCSVFile.class);

	private String type;
	private String csv;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCsv() {
		return csv;
	}
	public void setCsv(String csv) {
		this.csv = csv;
	}
	
	
	public void save() {
		logger.debug("Saving file {} to file system", getType());
		logger.debug("Transformed CSV {}", getCsv());
	}
	
	
	

}
