@BooksFeature @Integration @DemoQA
Feature: (Integration) Login and Books functionality


  @Positive
  Scenario: User do login go to a book store and then logout
    Given user using chrome in desktop and logged in "login" page
    And user wait until "loading label" is not visible
    Then user is in "profile" page
    Then user do "logged in demoqa"

    When user open "books" page
    Then user is in "books" page
    When user do "search for book Git Pocket Guide"
    Then user wait until "books table" is visible
    And user do these validations
      | actual            | validation         | expectation |
      | sizeof(book rows) | GREATER_THAN_EQUAL | 1           |

    When user do "log out from demoqa"
    Then user do "logged out demoqa"