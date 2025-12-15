package com.gdn.qa.module.api.automation.test.steps;

import com.gdn.qa.automation.core.CucumberStepsDefinition;
import com.gdn.qa.automation.core.context.StepDefinition;
import com.gdn.qa.automation.core.properties.WebProperties;
import com.gdn.qa.module.api.automation.test.data.AccountData;
import com.gdn.qa.module.api.automation.test.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@StepDefinition
@CucumberStepsDefinition
@Slf4j
public class LoginSteps extends ScenarioSteps {
    @Autowired
    AccountData accountData;
    @Autowired
    LoginPage loginPage;
    @Autowired
    WebProperties webProperties;

    @And("^\\[new-automation-project-UI\\] User type (.*) at (.*) input field at demoqa website")
    public void newAutomationProjectUIUserTypeInputParamAtFieldNameInputFieldAtDemoqaWebsite(String inputParam, String inputField) {
        String input = "";
        if (inputParam.equalsIgnoreCase("createdUsername")) {
            input = accountData.getLoginRequest().getUserName();
        } else {
            input = accountData.getLoginRequest().getPassword();
        }
        loginPage.typeAtInputField(input, inputField);
    }

    @Then("[new-automation-project-UI] User land at login page demoqa website")
    public void newAutomationProjectUIUserLandAtLoginPageDemoqaWebsite() {
        assertThat("User cannot see the login form",
                loginPage.elementExistChecker("Form UI"),
                equalTo(true));
        assertThat("User is not landed at login page",
                loginPage.getDriver().getCurrentUrl().equals(webProperties.endpoint("demoQaLogin")),
                equalTo(true));
    }
}
