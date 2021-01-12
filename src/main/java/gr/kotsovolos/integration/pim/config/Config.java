package gr.kotsovolos.integration.pim.config;

public class Config {

	private PreprocessorConfig preprocessorConfig;
	private ProcessorConfig processorConfig;

	public PreprocessorConfig getPreprocessorConfig() {
		return preprocessorConfig;
	}

	public void setPreprocessorConfig(PreprocessorConfig preprocessorConfig) {
		this.preprocessorConfig = preprocessorConfig;
	}

	public ProcessorConfig getProcessorConfig() {
		return processorConfig;
	}

	public void setProcessorConfig(ProcessorConfig processorConfig) {
		this.processorConfig = processorConfig;
	}

}
