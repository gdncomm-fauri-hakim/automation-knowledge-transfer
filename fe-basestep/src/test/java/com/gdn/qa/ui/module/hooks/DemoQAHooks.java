package com.gdn.qa.ui.module.hooks;

import com.gdn.qa.automation.core.context.StepDefinition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;

/**
 * Cucumber hooks for test setup and teardown
 */
@Log4j2
@StepDefinition
public class DemoQAHooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        log.info("Starting scenario: {}", scenario.getName());
        log.info("Tags: {}", scenario.getSourceTagNames());
    }

    @After
    public void afterScenario(Scenario scenario) {
        log.info("Scenario {} status: {}", scenario.getName(), scenario.getStatus());
    }
}
