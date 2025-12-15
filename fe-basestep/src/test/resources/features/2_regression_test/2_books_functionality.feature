@BooksFeature @Regression
Feature: (Regression) Books functionality

  Background:
    Given user using chrome in desktop and logged in "books" page
    And user is in "books" page

  @Positive
  Scenario: Search for a book
    When user do "search for book Git Pocket Guide"
    Then user wait until "books table" is visible
    And user do these validations
      | actual                | validation         | expectation |
      | sizeof(book rows) | GREATER_THAN_EQUAL | 1           |

  @Positive
  Scenario: View logged in username
    Then user wait until "logged in username label" is visible
    And user do these validations
      | actual                            | validation | expectation                   |
      | text_of(logged in username label) | EQUAL      | properties(default.username) |
