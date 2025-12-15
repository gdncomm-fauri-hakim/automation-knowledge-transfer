@AccountFeature @Sanity
@Regression
Feature: Account Management - Sanity Tests

  Background:
    Given [demoqa] using service with alias demoqa

  @Sanity @Positive @CreateUser
  Scenario: Create user and generate token successfully
    # Create new user
    Given [demoqa] prepare header "Content-Type" with value "application/json"
    And [demoqa] I store user credentials with username "random(10,ALPHANUMERIC)" and password "Test@123456"
    And [demoqa] prepare request data createUserRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 201 and success should be true
    And [demoqa] do these validations
      | actual                                          | validation | expectation |
      | response($['createUserResponse']['userID'])     | not empty  | true        |
      | response($['createUserResponse']['username'])   | not empty  | true        |
      | response($['createUserResponse']['books'])      | is array   | true        |
    And [demoqa] I store the user ID from response "response($['createUserResponse']['userID'])"

    # Generate authentication token
    Given [demoqa] prepare header "Content-Type" with value "application/json"
    And [demoqa] prepare request data generateTokenRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['generateTokenRequest'])"
    When [demoqa] try POST request to "/Account/v1/GenerateToken"
    Then [demoqa] assign previous response data to generateTokenResponse
    And [demoqa] response statusCode should be 200 and success should be true
    And [demoqa] do these validations
      | actual                                           | validation | expectation |
      | response($['generateTokenResponse']['token'])    | not empty  | true        |
      | response($['generateTokenResponse']['expires'])  | not empty  | true        |
      | response($['generateTokenResponse']['status'])   | equal      | Success     |
      | response($['generateTokenResponse']['result'])   | equal      | User authorized successfully. |
    And [demoqa] I store the token from response "response($['generateTokenResponse']['token'])"
    And [demoqa] authentication token should be generated

  @Sanity @Positive @Authorized
  Scenario: Verify user is authorized with valid token
    # Setup: Create user first
    Given [demoqa] prepare header "Content-Type" with value "application/json"
    And [demoqa] I store user credentials with username "random(10,ALPHANUMERIC)" and password "Test@123456"
    And [demoqa] prepare request data createUserRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 201

    # Check if user is authorized
    Given [demoqa] prepare header "Content-Type" with value "application/json"
    And [demoqa] prepare request data authorizedRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['authorizedRequest'])"
    When [demoqa] try POST request to "/Account/v1/Authorized"
    Then [demoqa] assign previous response data to authorizedResponse
    And [demoqa] response statusCode should be 200
    And [demoqa] I store authorization status from response "response($['authorizedResponse'])"
    And [demoqa] user should be authorized

  @Sanity @Negative @InvalidCredentials
  Scenario: Create user with invalid password format fails
    # Attempt to create user with weak password
    Given [demoqa] prepare header "Content-Type" with value "application/json"
    And [demoqa] I store user credentials with username "random(10,ALPHANUMERIC)" and password "weak"
    And [demoqa] prepare request data createUserRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 400
    And [demoqa] do these validations
      | actual                                     | validation | expectation |
      | response($['createUserResponse']['code'])  | equal      | 1300        |
