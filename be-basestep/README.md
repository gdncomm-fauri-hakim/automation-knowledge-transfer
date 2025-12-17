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