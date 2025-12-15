package com.gdn.qa.module.api.steps;

import com.gdn.qa.automation.core.CucumberStepsDefinition;
import com.gdn.qa.automation.core.context.StepDefinition;
import io.cucumber.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;

@StepDefinition
@CucumberStepsDefinition
public class CommonSteps extends ScenarioSteps {

    @When("^wait for (.*) seconds")
    public void waitForSeconds(int seconds) {
        waitABit(seconds* 1000L);
    }
}
