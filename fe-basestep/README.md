# DemoQA Test Automation Project

## Overview

This is a test automation framework for **DemoQA** (https://demoqa.com/login) built using:
- **Serenity BDD 4.2.14** - BDD reporting and orchestration
- **Cucumber** - Gherkin feature files for BDD scenarios
- **Screenplay Pattern Variant** - Custom implementation with `@Action` annotations
- **Badak Core Framework 3.9.27** - GDN's internal automation framework
- **Spring Framework** - Dependency injection for test components

## Project Structure

```
fe-basestep/
├── src/
│   ├── main/java/
│   │   └── com/gdn/qa/ui/module/
│   │       ├── actions/         # Business logic (LoginActions)
│   │       ├── pages/           # Page objects (LoginPage, BooksPage)
│   │       └── data/            # Data holders (DemoQAResponseData)
│   │
│   └── test/
│       ├── java/
│       │   └── com/gdn/qa/ui/module/
│       │       ├── CucumberRunner.java  # Test runner
│       │       ├── hooks/              # Test hooks
│       │       └── steps/              # Step definitions
│       │
│       └── resources/
│           ├── features/               # Cucumber feature files
│           │   ├── 1_sanity_test/     # Smoke tests
│           │   └── 2_regression_test/  # Regression tests
│           │
│           ├── serenity.properties    # Serenity config
│           └── application.properties # Application config
│
└── pom.xml                  # Maven configuration
```

## Quick Start

### Prerequisites
- Java 21
- Maven 3.6+
- Chrome/Firefox browser

### Running Tests

```bash
# Run all tests
mvn clean verify

# Run specific tag
mvn clean verify -Dcucumber.filter.tags="@Sanity"

# Run specific feature
mvn clean verify -Dcucumber.features="src/test/resources/features/1_sanity_test/"
```

### View Reports

After test execution:
```bash
target/site/serenity/index.html
```

## Test Data Configuration

Update `src/test/resources/application.properties`:

```properties
# Default test credentials
default.username=your_username
default.password=your_password
```

## Key Components

### Page Objects
- **LoginPage** - Login page elements and locators
- **BooksPage** - Books/Profile page elements

### Actions
- **LoginActions** - Login, logout, and book search business logic

### Features
- **1_login_to_demoqa.feature** - Login/logout scenarios
- **2_books_functionality.feature** - Books functionality tests

## Adding New Tests

1. **Create Page Object** in `src/main/java/.../pages/`
2. **Create Actions** in `src/main/java/.../actions/`
3. **Create Feature File** in `src/test/resources/features/`
4. **Run Tests** with `mvn clean verify`

## Framework Documentation

For detailed framework explanation, design patterns, and conventions, refer to the project analysis document.

## Tags

- `@Regression` - All regression tests
- `@Sanity` - Critical smoke tests
- `@Positive` - Happy path scenarios
- `@Negative` - Error/validation scenarios

## Contact

For questions or issues, please contact the QA automation team.
