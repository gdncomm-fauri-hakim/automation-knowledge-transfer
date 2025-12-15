package com.gdn.qa.module.api.automation.test.steps;

import com.gdn.qa.automation.core.CucumberStepsDefinition;
import com.gdn.qa.automation.core.context.StepDefinition;
import com.gdn.qa.automation.core.properties.WebProperties;
import com.gdn.qa.module.api.automation.test.data.AccountData;
import com.gdn.qa.module.api.automation.test.pages.BooksPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@StepDefinition
@CucumberStepsDefinition
@Slf4j
public class BooksSteps extends ScenarioSteps {
    @Autowired
    BooksPage booksPage;
    @Autowired
    WebProperties webProperties;
    @Autowired
    AccountData accountData;

    @Given("[new-automation-project-UI] User land at book page from demoqa website")
    public void newAutomationProjectUIUserLandAtBookPageFromDemoqaWebsite() {
        booksPage.openAt(webProperties.endpoint("demoQaBooks"));
    }

    @When("^\\[new-automation-project-UI\\] User click (.*) button at demoqa website")
    public void newAutomationProjectUIUserClickLoginButtonAtDemoqaWebsite(String idIdentifier) {
        booksPage.clickWithId(idIdentifier);
    }

    @Then("[new-automation-project-UI] User can see logged in account is createdUsername")
    public void newAutomationProjectUIUserCanSeeLoggedInAccountIsCreatedUsername() {
        assertThat("Account displayed is not same",
                booksPage.validateUserLoggedIn(accountData.getLoginRequest().getUserName()),
                equalTo(true));
    }
}
