package gr.kotsovolos.integration.pim.job;

import java.util.List;

import gr.kotsovolos.integration.pim.step.Step;

public interface Job {
	
	List<Step> getOrderedSteps();

}
