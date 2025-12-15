package com.gdn.qa.module.api.automation.test.steps;

import com.gdn.qa.automation.core.CucumberStepsDefinition;
import com.gdn.qa.automation.core.context.StepDefinition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;
import com.gdn.qa.module.api.automation.test.api.controllers.AccountController;
import com.gdn.qa.module.api.automation.test.data.AccountData;
import com.gdn.qa.module.api.automation.test.models.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@StepDefinition
@CucumberStepsDefinition
public class AccountSteps extends ScenarioSteps {
    @Autowired
    AccountData accountData;
    @Autowired
    AccountController accountController;

    @Given("[new-automation-project] Prepare data for request generate token")
    public void newAutomationProjectPrepareDataForRequestGenerateToken(Map<String, String> dataRequestMap) {
        LoginRequest request = new LoginRequest();
        request.setUserName(dataRequestMap.get("username"));
        request.setPassword(dataRequestMap.get("password"));
        accountData.setLoginRequest(request);
    }

    @When("[new-automation-project] Hit request generate token")
    public void newAutomationProjectHitRequestGenerateToken() {
        accountData.setGenerateTokenResponse(accountController.generateToken(accountData.getLoginRequest()));
    }

    @Then("[new-automation-project] Verify request generate token response code {int}")
    public void newAutomationProjectVerifyRequestGenerateTokenResponseCode(int arg0) {
        assertThat("Response status code not 200",
                accountData.getGenerateTokenResponse().getResponse().getStatusCode(),
                equalTo(arg0));

    }

}
