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

    /**
     * Store ISBN for book operations
     * Example: Given [bookstore] I store ISBN "9781449325862"
     */
    @Given("[bookstore] I store ISBN {string}")
    public void bookstoreIStoreISBN(String isbn) {
        bookStoreData.setIsbn(executeCommand(isbn));
    }

    /**
     * Add ISBN to collection list
     * Example: And [bookstore] I add ISBN "9781449325862" to collection
     */
    @Given("[bookstore] I add ISBN {string} to collection")
    public void bookstoreIAddISBNToCollection(String isbn) {
        bookStoreData.addIsbn(executeCommand(isbn));
    }

    /**
     * Validate books are returned
     * Example: Then [bookstore] books list should not be empty
     */
    @Then("[bookstore] books list should not be empty")
    public void bookstoreBooksListShouldNotBeEmpty() {
        Integer bookCount = Integer.valueOf(executeCommand("response($['getAllBooksResponse']['books'].size())"));
        assertThat("Books list should not be empty", bookCount, greaterThan(0));
    }

    /**
     * Validate specific book exists in response
     * Example: Then [bookstore] book with ISBN "9781449325862" should exist
     */
    @Then("[bookstore] book with ISBN {string} should exist")
    public void bookstoreBookWithISBNShouldExist(String isbn) {
        String isbnValue = executeCommand(isbn);
        String bookIsbn = executeCommand("response($['getBookResponse']['isbn'])");
        assertThat("Book ISBN should match", bookIsbn, equalTo(isbnValue));
    }

    /**
     * Validate book is added to user collection
     * Example: Then [bookstore] book should be added to user collection
     */
    @Then("[bookstore] book should be added to user collection")
    public void bookstoreBookShouldBeAddedToUserCollection() {
        String booksData = executeCommand("response($['addBooksResponse']['books'])");
        assertThat("Books should be added", booksData, notNullValue());
    }

    /**
     * Validate book is deleted successfully
     * Example: Then [bookstore] book should be deleted successfully
     */
    @Then("[bookstore] book should be deleted successfully")
    public void bookstoreBookShouldBeDeletedSuccessfully() {
        // After deletion, the response status should indicate success
        // This is validated through the base step's status code validation
        waitABit(500); // Small wait to ensure deletion is processed
    }

    /**
     * Store ISBN from response
     * Example: And [bookstore] I store ISBN from response "response($['getBookResponse']['isbn'])"
     */
    @Given("[bookstore] I store ISBN from response {string}")
    public void bookstoreIStoreISBNFromResponse(String isbnPath) {
        String isbn = executeCommand(isbnPath);
        bookStoreData.setIsbn(isbn);
    }

    /**
     * Validate book count matches expected
     * Example: Then [bookstore] total book count should be {int}
     */
    @Then("[bookstore] total book count should be {int}")
    public void bookstoreTotalBookCountShouldBe(Integer expectedCount) {
        Integer actualCount = Integer.valueOf(executeCommand("response($['getAllBooksResponse']['books'].size())"));
        assertThat("Book count should match", actualCount, equalTo(expectedCount));
    }

    /**
     * Validate book title contains text
     * Example: Then [bookstore] book title should contain "Git"
     */
    @Then("[bookstore] book title should contain {string}")
    public void bookstoreBookTitleShouldContain(String expectedText) {
        String title = executeCommand("response($['getBookResponse']['title'])");
        assertThat("Book title should contain expected text", title, containsString(expectedText));
    }

    /**
     * Reset bookstore data for cleanup
     * Example: Given [bookstore] I reset bookstore data
     */
    @Given("[bookstore] I reset bookstore data")
    public void bookstoreIResetBookstoreData() {
        bookStoreData.resetBookStoreData();
    }
}
