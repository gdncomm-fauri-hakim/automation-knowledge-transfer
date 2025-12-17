@BookStoreOperationsFeature @Regression
Feature: (Regression) BooksStore Feature

  Background:
    Given [demoqa] using service with alias demoqa

  @Regression @Positive @GetSpecificBook
  Scenario: Get specific book by ISBN successfully
    # Get all books first
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"