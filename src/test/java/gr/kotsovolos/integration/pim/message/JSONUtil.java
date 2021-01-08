package gr.kotsovolos.integration.pim.message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.json.JSONArray;

public class JSONUtil {

	public static JSONArray parseJSONFile(String filename) {

		InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);

		String text = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
				.collect(Collectors.joining(System.lineSeparator()));

		return new JSONArray(text);
	}

}
