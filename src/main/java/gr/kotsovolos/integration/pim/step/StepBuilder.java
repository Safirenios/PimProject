package gr.kotsovolos.integration.pim.step;

import java.util.Objects;

import gr.kotsovolos.integration.pim.config.PreprocessorConfig;

public final class StepBuilder {
	
	/**
	 * Preprocessor
	 * 
	 */
	public PreprocessorStepBuilder preprocessor() {
		return new PreprocessorStepBuilder();
	}

	public final class PreprocessorStepBuilder {

		private PreprocessorConfig config;

		public PreprocessorStepBuilder config(PreprocessorConfig config) {
			Objects.requireNonNull(config, "PreprocessorConfig should no be null");
			Objects.requireNonNull(config.getServiceBusConfig().getConnectionString(),
					"Connection string should not be null");
			Objects.requireNonNull(config.getServiceBusConfig().getTopicName(), "Topic name should not be null");
			Objects.requireNonNull(config.getServiceBusConfig().getSubscriptionName(),
					"Subscription name should not be null");

			this.config = config;
			return this;
		}

		public PreprocessorStep build() {
			return new PreprocessorStep(config);
		}

	}

	/**
	 * Processor
	 * 
	 */
	public ProcessorStepBuilder processor() {
		return new ProcessorStepBuilder();
	}

	public final class ProcessorStepBuilder {

		public ProcessorStep build() {
			return new ProcessorStep();
		}

	}

	/**
	 * Postprocessor
	 * 
	 */
	public PostProcessorStepBuilder postprocessor() {
		return new PostProcessorStepBuilder();
	}

	public final class PostProcessorStepBuilder {

		public PostProcessorStep build() {
			return new PostProcessorStep();
		}

	}

}
