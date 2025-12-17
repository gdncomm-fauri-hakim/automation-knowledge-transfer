@AccountFeature @Regression
Feature: (Regression) Account Feature

  Background:
    Given [demoqa] using service with alias demoqa

  @Regression @Negative @InvalidCredentials
  Scenario: Create user with invalid password format fails
    # Attempt to create user with weak password
    Given [demoqa] prepare request data createUserRequest with value
      | userName           | password |
      | (Test_,random(10)) | Test     |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 400
    And [demoqa] do these validations
      | actual                                    | validation | expectation |
      | response($['createUserResponse']['code']) | equal      | 1300        |

  @Regression @Negative @MissingUsername
  Scenario: Create user with missing username fails
    # Attempt to create user without username
    Given [demoqa] prepare request data createUserRequest with value
      | userName | password    |
      |          | Test@123456 |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 400
    And [demoqa] do these validations
      | actual                                    | validation | expectation |
      | response($['createUserResponse']['code']) | equal      | 1200        |

  @Regression @Negative @MissingPassword
  Scenario: Create user with missing password fails
    # Attempt to create user without password
    And [demoqa] prepare request data createUserRequest with value
      | userName           | password |
      | (Test_,random(10)) |          |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 400
    And [demoqa] do these validations
      | actual                                    | validation | expectation |
      | response($['createUserResponse']['code']) | equal      | 1200        |

  @Regression @Negative @ExistanceUser
  Scenario: Create user with exist user fails
    Given [demoqa] prepare request data createUserRequest with value
      | userName                          | password                          |
      | properties(default.data.username) | properties(default.data.password) |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 406
    And [demoqa] do these validations
      | actual                                    | validation | expectation |
      | response($['createUserResponse']['code']) | equal      | 1204        |