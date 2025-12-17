package com.gdn.qa.module.api.steps;

import com.gdn.qa.automation.core.CucumberStepsDefinition;
import com.gdn.qa.automation.core.context.StepDefinition;
import com.gdn.qa.module.api.data.AccountData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.beans.factory.annotation.Autowired;

import static com.gdn.qa.automation.core.utils.helper.commands.CommandExecutor.executeCommand;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@StepDefinition
@CucumberStepsDefinition
public class AccountSteps extends ScenarioSteps {

    @Autowired
    AccountData accountData;

    @Then("[demoqa] authentication token should be generated")
    public void demoqaAuthenticationTokenShouldBeGenerated() {
        assertThat("Token should not be empty", accountData.getToken(), notNullValue());
        assertThat("Token should not be empty", accountData.getToken(), not(emptyString()));
    }

    @Given("[demoqa] I store the user ID from response {string}")
    public void demoqaIStoreTheUserIDFromResponse(String userIdPath) {
        String userId = executeCommand(userIdPath);
        accountData.setUserId(userId);
    }

    @Given("[demoqa] I store the token from response {string}")
    public void demoqaIStoreTheTokenFromResponse(String tokenPath) {
        String token = executeCommand(tokenPath);
        accountData.setToken(token);
    }
}
