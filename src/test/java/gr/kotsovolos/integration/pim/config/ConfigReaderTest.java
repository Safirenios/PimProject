package gr.kotsovolos.integration.pim.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConfigReaderTest {

	@Test
	void should_Read_Local_Config_When_NoDevParam() {
		
		// given
		String env = "";
		
		// when
		Config config = ConfigReader.read(env);
		
		// then
		assertNotNull(config);
		assertNotNull(config.getPreprocessorConfig());
		assertNotNull(config.getPreprocessorConfig().getServiceBusConfig().getConnectionString());
	}
	
	@Test
	void should_Read_Dev_Config_When_DevEnv() {
		
		// given
		String env = "dev";
		
		// when
		Config config = ConfigReader.read(env);
		
		// then
		assertNotNull(config);
		assertNotNull(config.getPreprocessorConfig());
		assertNotNull(config.getPreprocessorConfig().getServiceBusConfig().getConnectionString());
	}
	
	@Test
	void should_Fail_When_Wrong_Env() {
		
		// given
		String env = "wrong_env_name";
		
		// when
		Config config = ConfigReader.read(env);
		
		// then
		assertNull(config);
	}

}
