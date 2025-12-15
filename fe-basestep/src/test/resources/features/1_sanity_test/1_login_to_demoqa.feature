@LoginFeature @Regression @Sanity
Feature: (Sanity) Login to DemoQA

  @Positive
  Scenario: Successful login with valid credentials
    Given user using chrome in desktop and logged out "login" page
    When user do "log in to demoqa" with parameter
      | username                   | password                   |
      | properties(default.username) | properties(default.password) |
    Then user do "logged in demoqa"

  @Positive
  Scenario: Logout from DemoQA
    Given user using chrome in desktop and logged in "books" page
    When user do "log out from demoqa"
    Then user do "logged out demoqa"

  @Negative
  Scenario: Login with invalid credentials
    Given user using chrome in desktop and logged out "login" page
    When user do "log in to demoqa" with parameter
      | username      | password  |
      | invaliduser   | wrongpass |
    Then user wait until "invalid credentials message" is visible
    And user do these validations
      | actual                           | validation | expectation                 |
      | text_of(invalid credentials message) | EQUAL      | Invalid username or password! |
