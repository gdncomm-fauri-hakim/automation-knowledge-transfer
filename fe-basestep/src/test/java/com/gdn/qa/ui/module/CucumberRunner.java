package com.gdn.qa.ui.module;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.SpringFactory;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(objectFactory = SpringFactory.class, stepNotifications = true, snippets = CAMELCASE, features = {
        "src/test/resources/features/"}, plugin = {"json:target/destination/cucumber.json",
        "rerun:target/rerun/rerun.txt"}, glue = {"com.gdn.qa.automation.core", "com.gdn.qa.ui.module.hooks",
        "com.gdn.qa.ui.module.steps", "com.gdn.qa.ui.module.actions"}, tags = "@Integration or @Regression")
public class CucumberRunner {

}
