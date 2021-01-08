package gr.kotsovolos.integration.pim.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import gr.kotsovolos.integration.pim.config.PreprocessorConfig;

class StepBuilderTest {

	
	@Nested
	@DisplayName("Preprocessor builder")
	class PreprocessorStepBuilderTest {
		
		@Test
		@DisplayName("Invalid connection string")
		void should_FailBuilding_PreProcessorStep_When_Invalid_ConnectionString() {
			
			// given
	     	PreprocessorConfig config = new PreprocessorConfig();
	     	config.getServiceBusConfig().setConnectionString("connectin string");
	     	config.getServiceBusConfig().setTopicName("topic name");
	     	config.getServiceBusConfig().setSubscriptionName("subs name");
	     	
			// when
	     	Executable executable = () -> {
	     		new StepBuilder().preprocessor().config(config).build();
	     	};
			
			// then
			assertThrows(IllegalArgumentException.class, executable);
		}
		
		@Test
		@DisplayName("Null config")
		void should_Fail_When_Building_Step_With_Null_Config() {
			
			// given
	     	PreprocessorConfig config = null;
	     	
			// when
			Executable executable = () -> {
				new StepBuilder().preprocessor().config(config).build();
			};
			
			// then
			assertThrows(NullPointerException.class, executable );
		}
		
		@Test
		@DisplayName("Null connection string")
		void should_Fail_When_Building_Step_With_Null_ConnectionString() {
			
			// given
			PreprocessorConfig config = new PreprocessorConfig();
			config.getServiceBusConfig().setTopicName("topic name");
			config.getServiceBusConfig().setSubscriptionName("subs name");
			
			// when
			Executable executable = () -> {
				new StepBuilder().preprocessor().config(config).build();
			};
			
			// then
			assertThrows(NullPointerException.class, executable );
		}	
		
		@Test
		@DisplayName("Null topic name")
		void should_Fail_When_Building_Step_With_Null_TopicName() {
			
			// given
			PreprocessorConfig config = new PreprocessorConfig();
			config.getServiceBusConfig().setConnectionString("connection string");
			config.getServiceBusConfig().setSubscriptionName("subs name");
			
			// when
			Executable executable = () -> {
				new StepBuilder().preprocessor().config(config).build();
			};
			
			// then
			assertThrows(NullPointerException.class, executable );
		}	
		
		@Test
		@DisplayName("Null subscription name")
		void should_Fail_When_Building_Step_With_Null_SubscriptionName() {
			
			// given
			PreprocessorConfig config = new PreprocessorConfig();
			config.getServiceBusConfig().setConnectionString("connection string");
			config.getServiceBusConfig().setTopicName("Topic name");
			
			// when
			Executable executable = () -> {
				new StepBuilder().preprocessor().config(config).build();
			};
			
			// then
			assertThrows(NullPointerException.class, executable );
		}	
	}
	
	@Nested
	@DisplayName("Processor builder")
	class ProcessorStepBuilderTest {
		
		@Test
		@DisplayName("Build processor step")
		void should_Build_ProcessorStep() {
			
			// given
			
			// when
			Step step = new StepBuilder().processor().build();
			
			// then
			assertNotNull(step);
			assertEquals(ProcessorStep.class, step.getClass());
		}
	}
	
	@Nested
	@DisplayName("Postprocessor builder")
	class PostProcessorStepBuilderTest {
		
		@Test
		@DisplayName("Build postprocessor step")
		void should_Build_PostProcessorStep() {
			
			// given
			
			// when
			Step step = new StepBuilder().postprocessor().build();
			
			// then
			assertNotNull(step);
			assertEquals(PostProcessorStep.class, step.getClass());
		}
	}


}
