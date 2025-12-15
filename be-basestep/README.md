# DemoQA BookStore API Test Automation Project

## Overview

This is a test automation framework for **DemoQA BookStore API** (https://demoqa.com/swagger/) built using:
- **Serenity BDD 4.2.14** - BDD reporting and orchestration
- **Cucumber** - Gherkin feature files for BDD scenarios
- **Badak Core Framework 3.9.27** - GDN's internal automation framework
- **Spring Framework** - Dependency injection for test components

## Project Structure

```
be-basestep/
├── src/
│   ├── main/java/
│   │   └── com/gdn/qa/module/api/
│   │       ├── data/             # Data holders (UserData, BookStoreData)
│   │       ├── properties/       # Configuration properties
│   │       └── utils/            # Utility classes
│   │
│   └── test/
│       ├── java/
│       │   └── com/gdn/qa/module/api/
│       │       ├── CucumberRunner.java    # Test runner
│       │       ├── hooks/                 # Test hooks
│       │       └── steps/                 # Step definitions
│       │           ├── AccountSteps.java  # Account API steps
│       │           ├── BookStoreSteps.java # BookStore API steps
│       │           ├── CustomLearningSteps.java # Learning examples
│       │           └── CommonSteps.java   # Common utility steps
│       │
│       └── resources/
│           ├── features/                   # Cucumber feature files
│           │   ├── Sanity/                # Smoke tests
│           │   │   ├── Account.feature    # Account sanity scenarios
│           │   │   └── BookStore.feature  # BookStore sanity scenarios
│           │   ├── Regression/            # Regression tests
│           │   │   ├── AccountCRUD.feature # Account CRUD scenarios
│           │   │   └── BookStoreOperations.feature # BookStore scenarios
│           │   └── Integration/           # Integration tests
│           │       └── CustomLearningScenarios.feature # Learning examples
│           │
│           ├── serenity.properties        # Serenity config
│           └── application.properties     # Application config
│
└── pom.xml                                # Maven configuration
```

## DemoQA API Endpoints

### Account APIs
- **POST /Account/v1/User** - Create new user
- **POST /Account/v1/GenerateToken** - Generate authentication token
- **POST /Account/v1/Authorized** - Check if user is authorized
- **GET /Account/v1/User/{UUID}** - Get user details
- **DELETE /Account/v1/User/{UUID}** - Delete user

### BookStore APIs
- **GET /BookStore/v1/Books** - Get all books
- **POST /BookStore/v1/Books** - Add book(s) to user collection
- **DELETE /BookStore/v1/Books** - Delete all user books
- **GET /BookStore/v1/Book** - Get specific book by ISBN
- **DELETE /BookStore/v1/Book** - Delete specific book from collection
- **PUT /BookStore/v1/Books/{ISBN}** - Update book ISBN

## Quick Start

### Prerequisites
- Java 21
- Maven 3.6+
- Internet connection (to access https://demoqa.com APIs)

### Running Tests

```bash
# Run all tests
mvn clean verify

# Run Sanity tests only
mvn clean verify -Dcucumber.filter.tags="@Sanity"

# Run Regression tests only
mvn clean verify -Dcucumber.filter.tags="@Regression"

# Run Integration/Learning tests
mvn clean verify -Dcucumber.filter.tags="@Integration"

# Run specific feature
mvn clean verify -Dcucumber.features="src/test/resources/features/Sanity"
```

### View Reports

After test execution:
```bash
target/site/serenity/index.html
```

## Test Data Configuration

Update `src/test/resources/application.properties`:

```properties
# DemoQA default test credentials
default.username=testuser
default.password=Test@123456
```

## Key Components

### Data Models
- **UserData** - Stores user account data (username, password, userId, token)
- **BookStoreData** - Stores book data (ISBN, titles, authors, collection data)

### Step Definitions
- **AccountSteps** - User account management steps
- **BookStoreSteps** - BookStore operations steps
- **CustomLearningSteps** - Educational steps demonstrating list handling, JSONPath queries, data extraction

### Features organized by Test Level

#### Sanity Tests
- **Account.feature** - Critical account flows (create user, generate token, authorization)
- **BookStore.feature** - Critical bookstore operations (get all books, add book)

#### Regression Tests
- **AccountCRUD.feature** - Complete account CRUD operations (5 scenarios: positive and negative cases)
- **BookStoreOperations.feature** - Comprehensive bookstore operations (9 scenarios: add, get, update, delete with positive/negative cases)

#### Integration Tests
- **CustomLearningScenarios.feature** - Learning scenarios (8 examples demonstrating):
  - List response validation
  - Multiple data extraction from arrays
  - Collection count validation
  - Filtering books by criteria
  - Complex JSONPath queries
  - Collection ordering validation

## Custom Learning Steps

The `CustomLearningSteps` class provides educational examples for working with list responses:

### Key Learning Scenarios:
1. **Get All Books and Validate** - Basic list validation
2. **Extract Multiple Data** - Extract ISBN, title, author from all books
3. **Count Validation** - Validate collection sizes
4. **Filter Books** - Filter books by title keyword
5. **Complex JSONPath** - Demonstrate advanced JSONPath patterns
6. **Sort Validation** - Validate collection ordering

### Example JSONPath Patterns:
```javascript
$[*].isbn                    // Get all ISBNs
$[0]                         // First element
$[-1]                        // Last element
$['books'][0]['title']       // Nested field access
$['books'].size()            // Count elements
$[?(@.title =~ /.*Git.*/)]   // Filter by pattern
```

## Tags

- `@Sanity` - Critical smoke tests
- `@Regression` - Full regression suite
- `@Integration` - Integration and learning tests
- `@Learning` - Educational learning scenarios
- `@Positive` - Happy path scenarios
- `@Negative` - Error/validation scenarios

## Adding New Tests

1. **Create Data Model** (if needed) in `src/main/java/.../data/`
2. **Create Custom Steps** (if needed) in `src/test/java/.../steps/`
3. **Create Feature File** in appropriate `src/test/resources/features/` folder
   - `Sanity/` - Quick smoke tests
   - `Regression/` - Comprehensive test scenarios
   - `Integration/` - Integration and learning tests
4. **Run Tests** with `mvn clean verify`

## Framework Documentation

### Base Steps from Badak Framework

The framework uses base steps from `badak-base-steps` for common operations:

**API Steps:**
- `[alias] using service with alias {servicealias}` - Initialize service
- `[alias] prepare header {key} with value {value}` - Set request header
- `[alias] prepare body request with value {body}` - Set request body
- `[alias] try {method} request to {endpoint}` - Execute HTTP request
- `[alias] response statusCode should be {code}` - Validate status code
- `[alias] do these validations` - Perform multiple validations
- `[alias] assign previous response data to {variableName}` - Store response
- `[alias] prepare request data {variable} with value` - Prepare request data
- `[alias] prepare pathParam for {param} with value {value}` - Set path parameter
- `[alias] prepare queryParam for {param} with value {value}` - Set query parameter

**Data Access:**
- `request($['variableName'])` - Access stored request data
- `response($['variableName'])` - Access stored response data
- `data($['dataHolderName']['field'])` - Access data from data holders
- `properties(property.name)` - Access application properties
- `random(length,type)` - Generate random data (ALPHANUMERIC, NUMERIC, UUID)
- `concat(str1,str2)` - Concatenate strings

### Custom Steps

**Account Steps:**
- `[demoqa] I store user credentials with username {string} and password {string}`
- `[demoqa] I store the user ID from response {string}`
- `[demoqa] I store the token from response {string}`
- `[demoqa] user ID should not be empty`
- `[demoqa] authentication token should be generated`
- `[demoqa] user should be authorized`

**BookStore Steps:**
- `[bookstore] I store ISBN {string}`
- `[bookstore] I add ISBN {string} to collection`
- `[bookstore] books list should not be empty`
- `[bookstore] book with ISBN {string} should exist`
- `[bookstore] total book count should be {int}`

**Learning Steps:** (See CustomLearningSteps.java for detailed comments)
- `[learning] I get all books and validate list is not empty`
- `[learning] I extract ISBN, title, and author from all books`
- `[learning] the total book count should match {int}`
- `[learning] I filter books with title containing {string}`
- `[learning] I demonstrate complex JSONPath queries for books`

## Contact

For questions or issues, please contact the QA automation team.
