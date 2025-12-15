package com.gdn.qa.module.api.steps;

import com.gdn.qa.automation.core.CucumberStepsDefinition;
import com.gdn.qa.automation.core.context.StepDefinition;
import com.gdn.qa.module.api.data.UserData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.beans.factory.annotation.Autowired;

import static com.gdn.qa.automation.core.utils.helper.commands.CommandExecutor.executeCommand;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Step definitions for Account API operations in DemoQA
 * Handles user creation, authentication, and account management
 * 
 * @author QA Automation Team
 */
@StepDefinition
@CucumberStepsDefinition
public class AccountSteps extends ScenarioSteps {

    @Autowired
    UserData userData;

    /**
     * Store user credentials for later use
     * Example: Given [demoqa] I store user credentials with username "testuser" and password "Test@123"
     */
    @Given("[demoqa] I store user credentials with username {string} and password {string}")
    public void demoqaIStoreUserCredentialsWithUsernameAndPassword(String username, String password) {
        userData.setUsername(executeCommand(username));
        userData.setPassword(executeCommand(password));
    }

    /**
     * Validate user ID is not empty
     * Example: Then [demoqa] user ID should not be empty
     */
    @Then("[demoqa] user ID should not be empty")
    public void demoqaUserIDShouldNotBeEmpty() {
        assertThat("User ID should not be empty", userData.getUserId(), notNullValue());
        assertThat("User ID should not be empty", userData.getUserId(), not(emptyString()));
    }

    /**
     * Validate token is generated
     * Example: Then [demoqa] authentication token should be generated
     */
    @Then("[demoqa] authentication token should be generated")
    public void demoqaAuthenticationTokenShouldBeGenerated() {
        assertThat("Token should not be empty", userData.getToken(), notNullValue());
        assertThat("Token should not be empty", userData.getToken(), not(emptyString()));
    }

    /**
     * Validate user is authorized
     * Example: Then [demoqa] user should be authorized
     */
    @Then("[demoqa] user should be authorized")
    public void demoqaUserShouldBeAuthorized() {
        assertThat("User should be authorized", userData.getIsActive(), equalTo(true));
    }

    /**
     * Store user ID from response
     * Example: And [demoqa] I store the user ID from response "response($['createUserResponse']['userID'])"
     */
    @Given("[demoqa] I store the user ID from response {string}")
    public void demoqaIStoreTheUserIDFromResponse(String userIdPath) {
        String userId = executeCommand(userIdPath);
        userData.setUserId(userId);
    }

    /**
     * Store token from response
     * Example: And [demoqa] I store the token from response "response($['generateTokenResponse']['token'])"
     */
    @Given("[demoqa] I store the token from response {string}")
    public void demoqaIStoreTheTokenFromResponse(String tokenPath) {
        String token = executeCommand(tokenPath);
        userData.setToken(token);
    }

    /**
     * Store authorization status
     * Example: And [demoqa] I store authorization status from response "response($['authorizedResponse'])"
     */
    @Given("[demoqa] I store authorization status from response {string}")
    public void demoqaIStoreAuthorizationStatusFromResponse(String statusPath) {
        Boolean isActive = Boolean.valueOf(executeCommand(statusPath));
        userData.setIsActive(isActive);
    }

    /**
     * Validate username in response matches stored username
     * Example: Then [demoqa] username in response should match stored username
     */
    @Then("[demoqa] username in response should match stored username")
    public void demoqaUsernameInResponseShouldMatchStoredUsername() {
        String responseUsername = executeCommand("response($['getUserResponse']['username'])");
        assertThat("Username should match", responseUsername, equalTo(userData.getUsername()));
    }

    /**
     * Reset user data for cleanup
     * Example: Given [demoqa] I reset user data
     */
    @Given("[demoqa] I reset user data")
    public void demoqaIResetUserData() {
        userData.resetUserData();
    }
}
