@AccountCRUDFeature @Regression
Feature: Account CRUD Operations - Regression Tests

  Background:
    Given [demoqa] using service with alias demoqa
    And [demoqa] prepare header "Content-Type" with value "application/json"

  @Regression @Positive @FullCRUD
  Scenario: Complete account lifecycle - Create, Get, and Delete user
    # Create user
    Given [demoqa] I store user credentials with username "random(10,ALPHANUMERIC)" and password "Test@123456" 
    And [demoqa] prepare request data createUserRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 201 and success should be true
    And [demoqa] I store the user ID from response "response($['createUserResponse']['userID'])"
    And [demoqa] user ID should not be empty

    # Generate token for authorization
    Given [demoqa] prepare request data generateTokenRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['generateTokenRequest'])"
    When [demoqa] try POST request to "/Account/v1/GenerateToken"
    Then [demoqa] assign previous response data to generateTokenResponse
    And [demoqa] response statusCode should be 200
    And [demoqa] I store the token from response "response($['generateTokenResponse']['token'])"

    # Get user details
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare pathParam for UUID with value "data($['userData']['userId'])"
    When [demoqa] try GET request to "/Account/v1/User/{UUID}"
    Then [demoqa] assign previous response data to getUserResponse
    And [demoqa] response statusCode should be 200
    And [demoqa] username in response should match stored username

    # Delete user
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare pathParam for UUID with value "data($['userData']['userId'])"
    When [demoqa] try DELETE request to "/Account/v1/User/{UUID}"
    Then [demoqa] response statusCode should be 204

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
      | actual                                     | validation | expectation |
      | response($['createUserResponse']['code'])  | equal      | 1200        |

  @Regression @Negative @MissingPassword
  Scenario: Create user with missing password fails
    # Attempt to create user without password
    Given [demoqa] I store user credentials with username "random(10,ALPHANUMERIC)" and password ""
    And [demoqa] prepare request data createUserRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) |                           |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 400
    And [demoqa] do these validations
      | actual                                     | validation | expectation |
      | response($['createUserResponse']['code'])  | equal      | 1200        |

  @Regression @Negative @NonExistentUser
  Scenario: Get non-existent user returns not found
    # Generate a random UUID that doesn't exist
    Given [demoqa] prepare pathParam for UUID with value "random(36,UUID)"
    When [demoqa] try GET request to "/Account/v1/User/{UUID}"
    Then [demoqa] assign previous response data to getUserResponse
    And [demoqa] response statusCode should be 401
    And [demoqa] do these validations
      | actual                                    | validation | expectation |
      | response($['getUserResponse']['code'])    | equal      | 1200        |

  @Regression @Negative @DeleteNonExistent
  Scenario: Delete non-existent user returns error
    # Setup: Create user and get token first
    Given [demoqa] I store user credentials with username "random(10,ALPHANUMERIC)" and password "Test@123456"
    And [demoqa] prepare request data createUserRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 201
    And [demoqa] I store the token from response "response($['createUserResponse']['token'])"

    # Try to delete with random UUID
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare pathParam for UUID with value "random(36,UUID)"
    When [demoqa] try DELETE request to "/Account/v1/User/{UUID}"
    Then [demoqa] response statusCode should be 200
    And [demoqa] do these validations
      | actual                                  | validation | expectation |
      | response($['code'])                     | equal      | 1207        |
