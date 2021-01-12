package gr.kotsovolos.integration.pim.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigReader {

	private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
	private static final String APPLICATION_CONFIG_FILE_NAME = "application%s.properties";

	public static Config read(String env) {

		Properties configProps = loadConfiguration(env);

		Config config = null;
		if (configProps != null) {
			config = new Config();

			// Load PreprocessorConfig properties
			PreprocessorConfig preprocessorCfg = new PreprocessorConfig();
			preprocessorCfg.getServiceBusConfig().setConnectionString(configProps.getProperty("service-bus-connection-string"));
			preprocessorCfg.getServiceBusConfig().setTopicName(configProps.getProperty("service-bus-topic-name"));
			preprocessorCfg.getServiceBusConfig().setSubscriptionName(configProps.getProperty("service-bus-subscription-name"));
			preprocessorCfg.getServiceBusConfig().setBatchSize(Integer.parseInt(configProps.getProperty("service-bus-batch-size")));
			preprocessorCfg.getServiceBusConfig().setMaxWaitTimeInSeconds(Long.parseLong(configProps.getProperty("service-bus-max-wait-time-in-seconds")));

			preprocessorCfg.getStorageConfig().setStorageAccountUrl(configProps.getProperty("storage-account-url"));
			preprocessorCfg.getStorageConfig().setStorageContainer(configProps.getProperty("storage-container"));
			preprocessorCfg.getStorageConfig().setStorageSecretKey(configProps.getProperty("storage-secret-key"));

			config.setPreprocessorConfig(preprocessorCfg);

			// Load ProcessorConfig properties
			ProcessorConfig processorCfg = new ProcessorConfig();
			processorCfg.getNodeJSConfig().setNodeJSApllicationLocation(configProps.getProperty("node-application-location"));
			processorCfg.getNodeJSConfig().setNodeJSApplicationName(configProps.getProperty("node-application-name"));

			config.setProcessorConfig(processorCfg);
		}

		return config;
	}

	private static Properties loadConfiguration(String env) {
		Properties properties = null;
		try {
			String fileName = String.format(APPLICATION_CONFIG_FILE_NAME, env.isEmpty() ? env : "." + env);
			logger.info("Loading application configuration from file {}", fileName);
			InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(fileName);

			if (systemResourceAsStream != null) {
				properties = new Properties();
				properties.load(systemResourceAsStream);
			}
			logger.debug("Loaded configuration: {}", properties);

		} catch (IOException e) {
			logger.error("Failed to load config file.", e);
		}
		return properties;
	}

}
