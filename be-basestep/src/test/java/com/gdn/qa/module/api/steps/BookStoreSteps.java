package com.gdn.qa.module.api.steps;

import com.gdn.qa.automation.core.CucumberStepsDefinition;
import com.gdn.qa.automation.core.context.StepDefinition;
import com.gdn.qa.module.api.data.BookStoreData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.beans.factory.annotation.Autowired;

import static com.gdn.qa.automation.core.utils.helper.commands.CommandExecutor.executeCommand;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Step definitions for BookStore API operations in DemoQA
 * Handles book management, search, and collection operations
 * 
 * @author QA Automation Team
 */
@StepDefinition
@CucumberStepsDefinition
public class BookStoreSteps extends ScenarioSteps {

    @Autowired
    BookStoreData bookStoreData;

    @Given("[bookstore] I store ISBN from response {string}")
    public void bookstoreIStoreISBNFromResponse(String isbnPath) {
        String isbn = executeCommand(isbnPath);
        bookStoreData.setIsbn(isbn);
    }
}
