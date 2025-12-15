@AccountFeature
Feature: Account API

  @Test
  Scenario: User Generate Token
    Given [new-automation-project] Prepare data for request generate token
      | username | string           |
      | password | stringSTRING123! |
    When  [new-automation-project] Hit request generate token
    Then  [new-automation-project] Verify request generate token response code 200