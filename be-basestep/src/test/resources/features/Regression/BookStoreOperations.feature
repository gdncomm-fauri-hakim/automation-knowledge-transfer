@BookStoreOperationsFeature @Regression
Feature: BookStore Operations - Regression Tests

  Background:
    Given [demoqa] using service with alias demoqa
    And [demoqa] prepare header "Content-Type" with value "application/json"
    # Setup: Create user and generate token for authenticated operations
    And [demoqa] I store user credentials with username "random(10,ALPHANUMERIC)" and password "Test@123456"
    And [demoqa] prepare request data createUserRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['createUserRequest'])"
    When [demoqa] try POST request to "/Account/v1/User"
    Then [demoqa] assign previous response data to createUserResponse
    And [demoqa] response statusCode should be 201
    And [demoqa] I store the user ID from response "response($['createUserResponse']['userID'])"
    And [demoqa] prepare request data generateTokenRequest with value
      | userName                  | password                  |
      | data($['userData']['username']) | data($['userData']['password']) |
    And [demoqa] prepare body request with value "request($['generateTokenRequest'])"
    When [demoqa] try POST request to "/Account/v1/GenerateToken"
    Then [demoqa] assign previous response data to generateTokenResponse
    And [demoqa] response statusCode should be 200
    And [demoqa] I store the token from response "response($['generateTokenResponse']['token'])"

  @Regression @Positive @AddBook
  Scenario: Add book to user collection successfully
    # Get a book ISBN
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"

    # Add book to collection
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
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

  @Regression @Positive @GetSpecificBook
  Scenario: Get specific book by ISBN successfully
    # Get all books first
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"

    # Get specific book by ISBN
    Given [demoqa] prepare queryParam for ISBN with value "data($['bookStoreData']['isbn'])"
    When [demoqa] try GET request to "/BookStore/v1/Book"
    Then [demoqa] assign previous response data to getBookResponse
    And [demoqa] response statusCode should be 200
    And [bookstore] book with ISBN "data($['bookStoreData']['isbn'])" should exist

  @Regression @Positive @UpdateBookISBN
  Scenario: Update book ISBN for user successfully
    # Add a book first
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare request data collectionOfIsbns with value
      | isbn                              |
      | data($['bookStoreData']['isbn'])  |
    And [demoqa] prepare request data addBooksRequest with value
      | userId                            | collectionOfIsbns                        |
      | data($['userData']['userId'])     | request($['collectionOfIsbns'])          |
    And [demoqa] prepare body request with value "request($['addBooksRequest'])"
    When [demoqa] try POST request to "/BookStore/v1/Books"
    Then [demoqa] response statusCode should be 201

    # Update book ISBN
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][1]['isbn'])"
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare pathParam for ISBN with value "response($['getAllBooksResponse']['books'][0]['isbn'])"
    And [demoqa] prepare request data updateBookRequest with value
      | userId                        | isbn                             |
      | data($['userData']['userId']) | data($['bookStoreData']['isbn']) |
    And [demoqa] prepare body request with value "request($['updateBookRequest'])"
    When [demoqa] try PUT request to "/BookStore/v1/Books/{ISBN}"
    Then [demoqa] assign previous response data to updateBookResponse
    And [demoqa] response statusCode should be 200

  @Regression @Positive @DeleteSingleBook
  Scenario: Delete single book from user collection
    # Add a book first
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare request data collectionOfIsbns with value
      | isbn                              |
      | data($['bookStoreData']['isbn'])  |
    And [demoqa] prepare request data addBooksRequest with value
      | userId                            | collectionOfIsbns                        |
      | data($['userData']['userId'])     | request($['collectionOfIsbns'])          |
    And [demoqa] prepare body request with value "request($['addBooksRequest'])"
    When [demoqa] try POST request to "/BookStore/v1/Books"
    Then [demoqa] response statusCode should be 201

    # Delete the book
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare request data deleteBookRequest with value
      | isbn                             | userId                        |
      | data($['bookStoreData']['isbn']) | data($['userData']['userId']) |
    And [demoqa] prepare body request with value "request($['deleteBookRequest'])"
    When [demoqa] try DELETE request to "/BookStore/v1/Book"
    Then [demoqa] response statusCode should be 204

  @Regression @Positive @DeleteAllBooks
  Scenario: Delete all books from user collection
    # Add books first
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare request data collectionOfIsbns with value
      | isbn                              |
      | data($['bookStoreData']['isbn'])  |
    And [demoqa] prepare request data addBooksRequest with value
      | userId                            | collectionOfIsbns                        |
      | data($['userData']['userId'])     | request($['collectionOfIsbns'])          |
    And [demoqa] prepare body request with value "request($['addBooksRequest'])"
    When [demoqa] try POST request to "/BookStore/v1/Books"
    Then [demoqa] response statusCode should be 201

    # Delete all books
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [demoqa] prepare queryParam for UserId with value "data($['userData']['userId'])"
    When [demoqa] try DELETE request to "/BookStore/v1/Books"
    Then [demoqa] response statusCode should be 204

  @Regression @Negative @InvalidISBN
  Scenario: Add book with invalid ISBN fails
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    And [bookstore] I store ISBN "INVALID-ISBN-123"
    And [demoqa] prepare request data collectionOfIsbns with value
      | isbn                              |
      | data($['bookStoreData']['isbn'])  |
    And [demoqa] prepare request data addBooksRequest with value
      | userId                            | collectionOfIsbns                        |
      | data($['userData']['userId'])     | request($['collectionOfIsbns'])          |
    And [demoqa] prepare body request with value "request($['addBooksRequest'])"
    When [demoqa] try POST request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to addBooksResponse
    And [demoqa] response statusCode should be 400
    And [demoqa] do these validations
      | actual                                    | validation | expectation |
      | response($['addBooksResponse']['code'])   | equal      | 1205        |

  @Regression @Negative @DeleteNonExistentBook
  Scenario: Delete book not in user collection fails
    Given [demoqa] prepare header "Authorization" with value "concat(Bearer ,data($['userData']['token']))"
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"
    And [demoqa] prepare request data deleteBookRequest with value
      | isbn                             | userId                        |
      | data($['bookStoreData']['isbn']) | data($['userData']['userId']) |
    And [demoqa] prepare body request with value "request($['deleteBookRequest'])"
    When [demoqa] try DELETE request to "/BookStore/v1/Book"
    Then [demoqa] response statusCode should be 400
    And [demoqa] do these validations
      | actual                         | validation | expectation |
      | response($['code'])            | equal      | 1206        |

  @Regression @Negative @GetNonExistentBook
  Scenario: Get book with non-existent ISBN fails
    Given [bookstore] I store ISBN "9999999999999"
    And [demoqa] prepare queryParam for ISBN with value "data($['bookStoreData']['isbn'])"
    When [demoqa] try GET request to "/BookStore/v1/Book"
    Then [demoqa] assign previous response data to getBookResponse
    And [demoqa] response statusCode should be 400
    And [demoqa] do these validations
      | actual                                 | validation | expectation |
      | response($['getBookResponse']['code']) | equal      | 1205        |

  @Regression @Negative @UnauthorizedAccess
  Scenario: Add book without authentication fails
    When [demoqa] try GET request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to getAllBooksResponse
    And [bookstore] I store ISBN from response "response($['getAllBooksResponse']['books'][0]['isbn'])"
    
    # Try to add book without Authorization header
    Given [demoqa] prepare request data collectionOfIsbns with value
      | isbn                              |
      | data($['bookStoreData']['isbn'])  |
    And [demoqa] prepare request data addBooksRequest with value
      | userId                            | collectionOfIsbns                        |
      | data($['userData']['userId'])     | request($['collectionOfIsbns'])          |
    And [demoqa] prepare body request with value "request($['addBooksRequest'])"
    When [demoqa] try POST request to "/BookStore/v1/Books"
    Then [demoqa] assign previous response data to addBooksResponse
    And [demoqa] response statusCode should be 401
    And [demoqa] do these validations
      | actual                                    | validation | expectation |
      | response($['addBooksResponse']['code'])   | equal      | 1200        |
