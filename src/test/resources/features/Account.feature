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