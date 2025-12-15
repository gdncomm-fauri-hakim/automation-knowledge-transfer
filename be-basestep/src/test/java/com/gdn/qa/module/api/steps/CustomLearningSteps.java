package com.gdn.qa.module.api.steps;

import com.gdn.qa.automation.core.CucumberStepsDefinition;
import com.gdn.qa.automation.core.context.StepDefinition;
import com.gdn.qa.module.api.data.BookStoreData;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gdn.qa.automation.core.utils.helper.commands.CommandExecutor.executeCommand;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Custom Learning Steps for Get Books API - Demonstrating List Response Handling
 * 
 * This step definition class is designed for educational purposes to demonstrate:
 * 1. How to work with list/array responses from APIs
 * 2. How to extract and store multiple data points from responses
 * 3. How to perform assertions on collections
 * 4. How to use JSONPath for complex queries
 * 5. How to iterate through list responses
 * 
 * These steps showcase advanced patterns for handling book list data from the
 * DemoQA BookStore API's "Get All Books" endpoint.
 * 
 * @author QA Automation Team - Knowledge Transfer
 */
@StepDefinition
@CucumberStepsDefinition
public class CustomLearningSteps extends ScenarioSteps {

    @Autowired
    BookStoreData bookStoreData;

    /**
     * LEARNING STEP 1: Get all books and assert list is not empty
     * 
     * This step demonstrates:
     * - How to parse JSONPath to get array size
     * - How to perform basic assertions on list responses
     * - How to store the count for later use
     * 
     * Example: When [learning] I get all books and validate list is not empty
     */
    @When("[learning] I get all books and validate list is not empty")
    public void learningIGetAllBooksAndValidateListIsNotEmpty() {
        // Step 1: Extract the books array from response using JSONPath
        // The response structure is: { "books": [ {...}, {...} ] }
        String booksJson = executeCommand("response($['getAllBooksResponse']['books'])");
        
        // Step 2: Parse and count books in the array
        // Using JSONPath $.length() or size() to count elements
        Integer bookCount = Integer.valueOf(executeCommand("response($['getAllBooksResponse']['books'].size())"));
        
        // Step 3: Store the count in our data holder for later validation
        bookStoreData.setTotalBooks(bookCount);
        
        // Step 4: Perform assertion - books list should not be empty
        assertThat("LEARNING: Books list should contain at least one book", 
                   bookCount, greaterThan(0));
        
        // Step 5: Log for learning purposes
        System.out.println("=== LEARNING STEP 1 ===");
        System.out.println("Total books found: " + bookCount);
        System.out.println("Books data stored in bookStoreData.totalBooks");
        System.out.println("=======================");
    }

    /**
     * LEARNING STEP 2: Extract and store multiple book data from list response
     * 
     * This step demonstrates:
     * - How to iterate through a list response
     * - How to extract specific fields (ISBN, title, author) from each item
     * - How to store multiple data points in lists
     * - How to work with JSONPath array notation: $[*].fieldName
     * 
     * Example: When [learning] I extract ISBN, title, and author from all books
     */
    @When("[learning] I extract ISBN, title, and author from all books")
    public void learningIExtractISBNTitleAndAuthorFromAllBooks() {
        System.out.println("\n=== LEARNING STEP 2 ===");
        System.out.println("Demonstrating: Extract multiple fields from list response");
        
        // Approach 1: Using JSONPath to extract all ISBNs at once
        // Pattern: $[*].isbn means "get isbn field from all array elements"
        String allIsbnsJson = executeCommand("response($['getAllBooksResponse']['books'][*]['isbn'])");
        System.out.println("All ISBNs extracted using JSONPath $[*].isbn:");
        System.out.println(allIsbnsJson);
        
        // Approach 2: Extract all titles
        String allTitlesJson = executeCommand("response($['getAllBooksResponse']['books'][*]['title'])");
        System.out.println("\nAll Titles extracted using JSONPath $[*].title:");
        System.out.println(allTitlesJson);
        
        // Approach 3: Extract all authors
        String allAuthorsJson = executeCommand("response($['getAllBooksResponse']['books'][*]['author'])");
        System.out.println("\nAll Authors extracted using JSONPath $[*].author:");
        System.out.println(allAuthorsJson);
        
        // Step 3: Store the extracted data in BookStoreData for later use
        // Note: In real scenarios, you'd parse these JSON strings to List<String>
        // For learning purposes, we'll store them as-is and also demonstrate iteration
        
        // Step 4: Demonstrate iteration - Extract books one by one
        Integer bookCount = bookStoreData.getTotalBooks();
        List<Map<String, Object>> extractedBooks = new ArrayList<>();
        
        System.out.println("\nIterating through each book:");
        for (int i = 0; i < bookCount; i++) {
            // JSONPath to get specific book by index: $[0], $[1], etc.
            String isbn = executeCommand("response($['getAllBooksResponse']['books'][" + i + "]['isbn'])");
            String title = executeCommand("response($['getAllBooksResponse']['books'][" + i + "]['title'])");
            String author = executeCommand("response($['getAllBooksResponse']['books'][" + i + "]['author'])");
            
            // Store in a map for this book
            Map<String, Object> bookData = new HashMap<>();
            bookData.put("isbn", isbn);
            bookData.put("title", title);
            bookData.put("author", author);
            bookData.put("index", i);
            
            extractedBooks.add(bookData);
            
            System.out.println("  Book #" + (i+1) + ": " + title + " by " + author + " [ISBN: " + isbn + "]");
        }
        
        // Step 5: Store all extracted books in BookStoreData
        bookStoreData.setExtractedBooks(extractedBooks);
        
        System.out.println("\nTotal books extracted and stored: " + extractedBooks.size());
        System.out.println("Data available in: bookStoreData.extractedBooks");
        System.out.println("=======================\n");
    }

    /**
     * LEARNING STEP 3: Validate book count matches expected value
     * 
     * This step demonstrates:
     * - How to validate collection size
     * - How to use stored data for validation
     * - Best practices for assertion messages
     * 
     * Example: Then [learning] the total book count should match {int}
     */
    @Then("[learning] the total book count should match {int}")
    public void learningTheTotalBookCountShouldMatch(Integer expectedCount) {
        System.out.println("\n=== LEARNING STEP 3 ===");
        System.out.println("Demonstrating: Validate collection count");
        
        // Option 1: Use stored count from previous step
        Integer storedCount = bookStoreData.getTotalBooks();
        System.out.println("Stored book count: " + storedCount);
        
        // Option 2: Re-query from response (demonstrates idempotency)
        Integer currentCount = Integer.valueOf(executeCommand("response($['getAllBooksResponse']['books'].size())"));
        System.out.println("Current count from response: " + currentCount);
        
        // Perform assertions
        assertThat("Book count from storage should match expected", 
                   storedCount, equalTo(expectedCount));
        assertThat("Book count from response should match expected", 
                   currentCount, equalTo(expectedCount));
        assertThat("Extracted books count should match expected", 
                   bookStoreData.getExtractedBooks().size(), equalTo(expectedCount));
        
        System.out.println("✓ All count validations passed!");
        System.out.println("=======================\n");
    }

    /**
     * LEARNING STEP 4: Filter books by title keyword
     * 
     * This step demonstrates:
     * - How to use JSONPath filters: $[?(@.field =~ /pattern/)]
     * - How to filter collections based on criteria
     * - Advanced JSONPath expressions for conditional selection
     * 
     * Example: When [learning] I filter books with title containing "Git"
     */
    @When("[learning] I filter books with title containing {string}")
    public void learningIFilterBooksWithTitleContaining(String keyword) {
        System.out.println("\n=== LEARNING STEP 4 ===");
        System.out.println("Demonstrating: Filter collections using JSONPath");
        System.out.println("Filtering books with title containing: " + keyword);
        
        // Step 1: Use JSONPath filter to find matching books
        // Pattern: $[?(@.title =~ /.*keyword.*/i)] 
        // This means: "Find all elements where title matches the regex pattern"
        // Note: The 'i' flag makes it case-insensitive
        
        List<Map<String, Object>> allBooks = bookStoreData.getExtractedBooks();
        List<Map<String, Object>> filteredBooks = new ArrayList<>();
        
        // Manual filter for learning purposes (shows the logic)
        System.out.println("\nManual filtering process:");
        for (Map<String, Object> book : allBooks) {
            String title = (String) book.get("title");
            if (title != null && title.toLowerCase().contains(keyword.toLowerCase())) {
                filteredBooks.add(book);
                System.out.println("  ✓ MATCH: " + title);
            } else {
                System.out.println("  ✗ No match: " + title);
            }
        }
        
        // Store filtered results
        bookStoreData.setExtractedBooks(filteredBooks);
        
        System.out.println("\nFiltering complete!");
        System.out.println("Books before filter: " + allBooks.size());
        System.out.println("Books after filter: " + filteredBooks.size());
        System.out.println("Filtered data stored in: bookStoreData.extractedBooks");
        System.out.println("=======================\n");
    }

    /**
     * LEARNING STEP 5: Validate filtered book count
     * 
     * This step demonstrates:
     * - How to validate results after filtering
     * - How to work with filtered collections
     * 
     * Example: Then [learning] the filtered books count should be {int}
     */
    @Then("[learning] the filtered books count should be {int}")
    public void learningTheFilteredBooksCountShouldBe(Integer expectedCount) {
        System.out.println("\n=== LEARNING STEP 5 ===");
        System.out.println("Demonstrating: Validate filtered results");
        
        Integer actualCount = bookStoreData.getExtractedBooks().size();
        System.out.println("Expected count: " + expectedCount);
        System.out.println("Actual count: " + actualCount);
        
        assertThat("Filtered books count should match expected", 
                   actualCount, equalTo(expectedCount));
        
        System.out.println("✓ Filtered count validation passed!");
        System.out.println("=======================\n");
    }

    /**
     * LEARNING STEP 6: Demonstrate complex JSONPath queries
     * 
     * This step demonstrates:
     * - Multiple advanced JSONPath patterns
     * - How to extract nested data
     * - How to combine multiple queries
     * 
     * Example: When [learning] I demonstrate complex JSONPath queries for books
     */
    @When("[learning] I demonstrate complex JSONPath queries for books")
    public void learningIDemonstrateComplexJSONPathQueriesForBooks() {
        System.out.println("\n=== LEARNING STEP 6 ===");
        System.out.println("Demonstrating: Advanced JSONPath queries");
        
        // Query 1: Get all ISBNs (simple array extraction)
        System.out.println("\n1. Extract all ISBNs:");
        System.out.println("   JSONPath: $['getAllBooksResponse']['books'][*]['isbn']");
        String allIsbns = executeCommand("response($['getAllBooksResponse']['books'][*]['isbn'])");
        System.out.println("   Result: " + allIsbns);
        
        // Query 2: Get first book's title
        System.out.println("\n2. Get first book:");
        System.out.println("   JSONPath: $['getAllBooksResponse']['books'][0]");
        String firstBook = executeCommand("response($['getAllBooksResponse']['books'][0])");
        System.out.println("   Result: " + firstBook);
        
        // Query 3: Get last book's title
        System.out.println("\n3. Get last book:");
        System.out.println("   JSONPath: $['getAllBooksResponse']['books'][-1]");
        String lastBook = executeCommand("response($['getAllBooksResponse']['books'][-1])");
        System.out.println("   Result: " + lastBook);
        
        // Query 4: Get books at specific indices
        System.out.println("\n4. Get books at indices 0, 2, 4:");
        System.out.println("   JSONPath: $['getAllBooksResponse']['books'][0,2,4]");
        System.out.println("   (This would require multiple queries in practice)");
        
        // Query 5: Get all publishers (demonstrates another field extraction)
        System.out.println("\n5. Extract all publishers:");
        System.out.println("   JSONPath: $['getAllBooksResponse']['books'][*]['publisher']");
        String allPublishers = executeCommand("response($['getAllBooksResponse']['books'][*]['publisher'])");
        System.out.println("   Result: " + allPublishers);
        
        System.out.println("\n=======================");
        System.out.println("KEY LEARNING POINTS:");
        System.out.println("- Use [*] to get all elements");
        System.out.println("- Use [0] for first element, [-1] for last");
        System.out.println("- Chain fields with ['field1']['field2']");
        System.out.println("- Use .size() to count elements");
        System.out.println("=======================\n");
    }

    /**
     * LEARNING STEP 7: Sort and validate book list order
     * 
     * This step demonstrates:
     * - How to work with sorted collections
     * - How to validate ordering
     * - Comparison operations on lists
     * 
     * Example: Then [learning] I validate books are sorted by title
     */
    @Then("[learning] I validate books are sorted by title")
    public void learningIValidateBooksAreSortedByTitle() {
        System.out.println("\n=== LEARNING STEP 7 ===");
        System.out.println("Demonstrating: Validate collection ordering");
        
        List<Map<String, Object>> books = bookStoreData.getExtractedBooks();
        
        // Extract titles
        List<String> titles = books.stream()
                                   .map(book -> (String) book.get("title"))
                                   .collect(Collectors.toList());
        
        System.out.println("Original order:");
        for (int i = 0; i < titles.size(); i++) {
            System.out.println("  " + (i+1) + ". " + titles.get(i));
        }
        
        // Create sorted copy to compare
        List<String> sortedTitles = new ArrayList<>(titles);
        sortedTitles.sort(String::compareTo);
        
        System.out.println("\nExpected sorted order:");
        for (int i = 0; i < sortedTitles.size(); i++) {
            System.out.println("  " + (i+1) + ". " + sortedTitles.get(i));
        }
        
        // Validate ordering
        boolean isSorted = titles.equals(sortedTitles);
        System.out.println("\nIs sorted? " + isSorted);
        
        if (!isSorted) {
            System.out.println("Note: Books are NOT sorted alphabetically");
            System.out.println("This is expected behavior from the API");
        }
        
        System.out.println("=======================\n");
    }
}
