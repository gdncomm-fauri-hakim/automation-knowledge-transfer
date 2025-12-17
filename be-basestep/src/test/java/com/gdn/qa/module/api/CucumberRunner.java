package com.gdn.qa.module.api;

import cucumber.runtime.SerenityObjectFactory;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(stepNotifications = true,
        objectFactory = SerenityObjectFactory.class,
        monochrome = true,
        snippets = CAMELCASE,
        features = "src/test/resources/features",
        glue = {"com.gdn.qa.automation.core.steps", "com.gdn.qa.module.api.steps", "com.gdn.qa.module.api.hooks"},
        plugin = {"json:target/destination/cucumber.json","rerun:target/rerun/rerun.txt"},
        tags = "@Integration or @Regression")
public class CucumberRunner {
  @BeforeClass
  public static void beforeTests() throws Exception {
  }
}
