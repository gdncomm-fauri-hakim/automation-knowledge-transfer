@LoginFeature @Sanity @Regression @DemoQA
Feature: (Sanity) Login to DemoQA

  @Positive
  Scenario: Successful login with valid credentials
    Given user using chrome in desktop and logged in "login" page
    And user wait until "loading label" is not visible
    Then user is in "profile" page
    Then user do "logged in demoqa"

  @Negative
  Scenario: Login with invalid credentials
    Given user using chrome in desktop and logged out "login" page
    And user wait for 1 seconds
    And user do "log in to demoqa" with parameter
      | username | password     |
      | invalid  | invalidpass  |
    Then user wait until "invalid credentials message" is visible
    And user do these validations
      | actual                               | validation | expectation                   |
      | textof(invalid credentials message) | EQUAL      | Invalid username or password! |