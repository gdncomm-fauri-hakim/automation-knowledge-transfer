package com.gdn.qa.module.api.automation.test;

import com.gdn.qa.automation.core.utils.helper.UtilityHelper;
import cucumber.runtime.SerenityObjectFactory;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(stepNotifications = true,
        objectFactory = SerenityObjectFactory.class,
        monochrome = true,
        snippets = CAMELCASE,
        features = "src/test/resources/features",
        glue = {"com.gdn.qa.automation.core.steps", "com.gdn.qa.module.api.automation.test.steps"},
        plugin = {"json:target/destination/cucumber.json"},
        tags = "@AccountFeature")
public class CucumberRunner {
  @BeforeClass
  public static void beforeTests() throws Exception {
  }

  @AfterClass
  public static void afterTests() {
    UtilityHelper.tearDownAllTests();
  }
}
