@AccountFeature
Feature: Account API

  @Authentication
  Scenario: User Create Account and Authenticate until complete
    # Create Account - Demo at Session

    # Authorized the User
    Given [new-automation-project] Prepare data for request generate token
      | username | string           |
      | password | stringSTRING123! |
    When  [new-automation-project] Hit request generate token
    Then  [new-automation-project] Verify request generate token response code 200

    # Verify the authentication valid
    When  [new-automation-project] Hit request check authorization
    Then  [new-automation-project] Verify request check authorization response code 200
    And   [new-automation-project] Verify request check authorization response value true

    # Login via UI using created account
    Given [new-automation-project-UI] User land at book page from demoqa website
    When  [new-automation-project-UI] User click Login button at demoqa website
    And   [new-automation-project-UI] User type createdUsername at Username input field at demoqa website
    And   [new-automation-project-UI] User type createdPassword at Password input field at demoqa website
    And   [new-automation-project-UI] User click Login button at demoqa website
    Then  [new-automation-project-UI] User can see logged in account is createdUsername

    # Logged Out
    When  [new-automation-project-UI] User click Logout button at demoqa website
    Then  [new-automation-project-UI] User land at login page demoqa website