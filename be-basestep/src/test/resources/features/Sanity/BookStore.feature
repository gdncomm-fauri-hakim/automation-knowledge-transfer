@BookStoreFeature @Sanity
@Regression
Feature: BookStore Management - Sanity Tests

  Background:
    Given [demoqa] using service with alias demoqa

  @Sanity @Positive @GetAllBooks
  Scenario: Get all books successfully
    # Get all available books
    Given [demoqa] prepare header "Content-Type" with value "application/json"
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [demoqa] response statusCode should be 200
    And [bookstore] books list should not be empty
    And [demoqa] do these validations
      | actual                                          | validation | expectation |
      | response($['getAllBooksResponse']['books'])     | is array   | true        |
      | response($['getAllBooksResponse']['books'][0]['isbn'])   | not empty  | true        |
      | response($['getAllBooksResponse']['books'][0]['title'])  | not empty  | true        |
      | response($['getAllBooksResponse']['books'][0]['author']) | not empty  | true        |

  @Sanity @Positive @AddBookToCollection
  Scenario: Add book to user collection
    # Setup: Create user and generate token
    Given [demoqa] prepare header "Content-Type" with value "application/json"
    And [demoqa] I store user credentials with username "random(10,ALPHANUMERIC)" and password "Test@123456"
    And [demoqa] prepare request data createUserRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 201
    And [demoqa] I store the user ID from response "response($['createUserResponse']['userID'])"

    # Generate token
    Given [demoqa] prepare header "Content-Type" with value "application/json"
    And [demoqa] prepare request data generateTokenRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['generateTokenRequest'])"
    When [demoqa] try POST request to "/Account/v1/GenerateToken"
    Then [demoqa] assign previous response data to generateTokenResponse
    And [demoqa] response statusCode should be 200
    And [demoqa] I store the token from response "response($['generateTokenResponse']['token'])"

    # Get a book ISBN to add
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"

    # Add book to user collection
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare header "Content-Type" with value "application/json"
    And [demoqa] prepare request data collectionOfIsbns with value
      | isbn                              |
      | data($['bookStoreData']['isbn'])  |
    And [demoqa] prepare request data addBooksRequest with value
      | userId                            | collectionOfIsbns                        |
      | data($['userData']['userId'])     | request($['collectionOfIsbns'])          |
    And [demoqa] prepare body request with value "request($['addBooksRequest'])"
    When [demoqa] try POST request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to addBooksResponse
    And [demoqa] response statusCode should be 201
    And [bookstore] book should be added to user collection
