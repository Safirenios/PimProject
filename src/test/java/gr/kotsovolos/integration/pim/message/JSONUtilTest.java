package gr.kotsovolos.integration.pim.message;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

class JSONUtilTest {

	@Test
	void should_ReadJSONFile_And_ConvertTo_JSONArray() {
		JSONArray file = JSONUtil.parseJSONFile("product-added_990180.json");
		assertNotNull(file);
		assertTrue(file.length() > 0);
	}

}
