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