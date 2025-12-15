@CustomLearningFeature @Integration @Learning
Feature: Custom Learning Scenarios - Demonstrating List Response Handling

  Background:
    Given [demoqa] using service with alias demoqa
    And [demoqa] prepare header "Content-Type" with value "application/json"

  @Integration @Learning @GetAllBooksValidation
  Scenario: Get all books and validate list is not empty
    # This scenario demonstrates how to work with list responses
    # and perform basic assertions on collections
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [demoqa] response statusCode should be 200
    
    # LEARNING: Custom step that validates list is not empty
    # Check CustomLearningSteps.java to see how this is implemented
    When [learning] I get all books and validate list is not empty
    
    # Validate using standard base steps
    And [demoqa] do these validations
      | actual                                         | validation | expectation |
      | response($['getAllBooksResponse']['books'])    | is array   | true        |
      | response($['getAllBooksResponse']['books'].size()) | greater than | 0    |

  @Integration @Learning @ExtractMultipleData
  Scenario: Extract and store multiple book data from list response
    # This scenario demonstrates how to extract multiple fields from each
    # item in a list response and store them for later use
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [demoqa] response statusCode should be 200
    And [learning] I get all books and validate list is not empty
    
    # LEARNING: Custom step that extracts ISBN, title, and author from all books
    # This demonstrates iteration through list responses
    # Check CustomLearningSteps.java learningIExtractISBNTitleAndAuthorFromAllBooks()
    When [learning] I extract ISBN, title, and author from all books
    
    # Verify extracted data is available
    Then [demoqa] do these validations
      | actual                                                         | validation | expectation |
      | response($['getAllBooksResponse']['books'][0]['isbn'])         | not empty  | true        |
      | response($['getAllBooksResponse']['books'][0]['title'])        | not empty  | true        |
      | response($['getAllBooksResponse']['books'][0]['author'])       | not empty  | true        |

  @Integration @Learning @CountValidation
  Scenario: Validate book count matches expected value
    # This scenario demonstrates how to validate collection sizes
    # and use stored data for assertions
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [demoqa] response statusCode should be 200
    And [learning] I get all books and validate list is not empty
    
    # LEARNING: Custom step that validates the total count
    # This shows how to work with stored counts from previous steps
    # Check CustomLearningSteps.java learningTheTotalBookCountShouldMatch()
    Then [learning] the total book count should match 8
    
    # Alternative validation using base steps
    And [demoqa] do these validations
      | actual                                         | validation | expectation |
      | response($['getAllBooksResponse']['books'].size()) | equal   | 8           |

  @Integration @Learning @FilterBooks
  Scenario: Filter books by title keyword
    # This scenario demonstrates how to filter collections based on criteria
    # using JSONPath filters and manual filtering logic
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [demoqa] response statusCode should be 200
    And [learning] I get all books and validate list is not empty
    And [learning] I extract ISBN, title, and author from all books
    
    # LEARNING: Custom step that filters books by title containing keyword
    # This demonstrates collection filtering and JSONPath usage
    # Check CustomLearningSteps.java learningIFilterBooksWithTitleContaining()
    When [learning] I filter books with title containing "Git"
    
    # Validate at least one book contains "Git" in title
    Then [demoqa] do these validations
      | actual                                              | validation | expectation |
      | response($['getAllBooksResponse']['books'][0]['title']) | contains  | Git      |

  @Integration @Learning @FilterValidation
  Scenario: Validate filtered book count
    # This scenario demonstrates end-to-end filtering workflow
    # including validation of filtered results
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [demoqa] response statusCode should be 200
    And [learning] I get all books and validate list is not empty
    And [learning] I extract ISBN, title, and author from all books
    When [learning] I filter books with title containing "JavaScript"
    
    # LEARNING: Custom step to validate filtered count
    # Shows how to assert on filtered collection sizes
    # Check CustomLearningSteps.java learningTheFilteredBooksCountShouldBe()
    Then [learning] the filtered books count should be 2

  @Integration @Learning @ComplexJSONPath
  Scenario: Demonstrate complex JSONPath queries on list
    # This scenario showcases advanced JSONPath patterns
    # for working with list/array responses
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [demoqa] response statusCode should be 200
    And [learning] I get all books and validate list is not empty
    
    # LEARNING: Custom step demonstrating multiple advanced JSONPath queries
    # This is the most comprehensive learning step showing various patterns
    # Check CustomLearningSteps.java learningIDemonstrateComplexJSONPathQueriesForBooks()
    When [learning] I demonstrate complex JSONPath queries for books
    
    # Examples of JSONPath patterns demonstrated:
    # - $[*].isbn : Get all ISBNs
    # - $[0] : Get first element
    # - $[-1] : Get last element
    # - $[0,2,4] : Get specific indices
    # - .size() : Count elements

  @Integration @Learning @SortValidation
  Scenario: Validate book list ordering
    # This scenario demonstrates how to work with sorted collections
    # and validate ordering of list responses
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [demoqa] response statusCode should be 200
    And [learning] I get all books and validate list is not empty
    And [learning] I extract ISBN, title, and author from all books
    
    # LEARNING: Custom step to validate if books are sorted
    # Shows comparison operations on lists
    # Check CustomLearningSteps.java learningIValidateBooksAreSortedByTitle()
    Then [learning] I validate books are sorted by title

  @Integration @Learning @FullWorkflow
  Scenario: Complete learning workflow with all custom steps
    # This scenario chains all learning steps together to demonstrate
    # a complete end-to-end workflow for list response handling
    
    # Step 1: Get books and validate
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [demoqa] response statusCode should be 200
    And [learning] I get all books and validate list is not empty
    
    # Step 2: Extract data from all books
    And [learning] I extract ISBN, title, and author from all books
    
    # Step 3: Validate total count
    Then [learning] the total book count should match 8
    
    # Step 4: Filter by keyword
    When [learning] I filter books with title containing "Git"
    Then [learning] the filtered books count should be 1
    
    # Step 5: Demonstrate JSONPath
    When [learning] I demonstrate complex JSONPath queries for books
    
    # Step 6: Check ordering
    Then [learning] I validate books are sorted by title
    
    # This completes the full learning workflow!
