@BooksFeature @Regression @DemoQA
Feature: (Regression) Books functionality

  @Positive
  Scenario: Search for a book
    Given user using chrome in desktop
    When user open "books" page
    Then user is in "books" page
    And user do "search for book Git Pocket Guide"
    Then user wait until "books table" is visible
    And user do these validations
      | actual            | validation         | expectation |
      | sizeof(book rows) | GREATER_THAN_EQUAL | 1           |