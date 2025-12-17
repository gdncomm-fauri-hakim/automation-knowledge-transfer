@AccountFeature @Integration
Feature: (Integration) Account Feature

  Background:
    Given [demoqa] using service with alias demoqa

  @Sanity @Positive @CreateUser
  Scenario: Create user, generate token and get all books successfully
    # Create new user
    Given [demoqa] prepare request data createUserRequest with value
      | userName           | password    |
      | (Test_,random(10)) | Test@123456 |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 201 and success should be true
    And [demoqa] do these validations
      | actual                                        | validation | expectation |
      | response($['createUserResponse']['userID'])   | not empty  | true        |
      | response($['createUserResponse']['username']) | not empty  | true        |
    And [demoqa] I store the user ID from response "response($['createUserResponse']['userID'])"

    # Generate authentication token
    Given [demoqa] prepare request data generateTokenRequest with value
      | userName                                    | password                                    |
      | request($['createUserRequest']['userName']) | request($['createUserRequest']['password']) |
    And [demoqa] prepare body request with value "request($['generateTokenRequest'])"
    When [demoqa] try POST request to "/Account/v1/GenerateToken"
    Then [demoqa] assign previous response data to generateTokenResponse
    And [demoqa] response statusCode should be 200 and success should be true
    And [demoqa] do these validations
      | actual                                          | validation | expectation                   |
      | response($['generateTokenResponse']['token'])   | not empty  | true                          |
      | response($['generateTokenResponse']['expires']) | not empty  | true                          |
      | response($['generateTokenResponse']['status'])  | equal      | Success                       |
      | response($['generateTokenResponse']['result'])  | equal      | User authorized successfully. |
    And [demoqa] I store the token from response "response($['generateTokenResponse']['token'])"
    And [demoqa] authentication token should be generated

    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"