# Selenium WebDriver BiDi Demo

[![Selenium Version](https://img.shields.io/badge/Selenium-4.29.0-green)](https://www.selenium.dev/)
[![Java Version](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-orange)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

This repository showcases WebDriver BiDi (Bidirectional Protocol) functionalities in Selenium 4. The BiDi protocol enables powerful browser automation features like:

- Real-time console log access
- Network traffic monitoring and interception
- JavaScript execution and exception monitoring
- Browser context management
- DevTools Protocol (CDP) integration

## 📖 Detailed Documentation

For a comprehensive guide on Selenium BiDi, check out:
- [Selenium BiDi Official Documentation](https://www.selenium.dev/documentation/webdriver/bidirectional/)
- [Detailed Blog Post on Awesome Testing](https://www.awesome-testing.com/2023/04/exploring-selenium-bidi-functionality.html)

## 🚀 Getting Started

### Prerequisites

- Java 21
- Maven
- Docker and Docker Compose (for running Selenium Grid)

### Project Setup

1. Clone this repository
2. Build the project: `mvn clean install`

## 🏗️ Running Selenium Grid

You need to install [Docker](https://docs.docker.com/get-docker/) and [Docker Compose](https://docs.docker.com/compose/install/) first.

Then run:

```bash
# On Mac/Linux
docker-compose up

# On Windows
docker compose up
```

### Grid Verification

- Grid UI: [http://localhost:4444](http://localhost:4444)
- Grid Status: [http://localhost:4444/status](http://localhost:4444/status)

## 🧪 Running Tests

### Local Tests

```bash
mvn test -Dtest=*local*
```

### Remote Tests (Selenium Grid)

```bash
# Start the grid first using docker-compose
mvn test -Dtest=*remote*
```

## 📊 Test Reports with Allure

[Allure](https://allurereport.org/docs/gettingstarted-installation/) is required to generate test reports.

```bash
# Run tests with Allure
mvn clean test

# Generate the report
allure generate target/allure-results -o target/allure-report

# Generate and serve the report (single HTML report)
allure generate target/allure-results -o target/allure-report --single-file
```

> **Note**: At least Allure version 2.24.1 is required to generate a single HTML report.

## 🧩 Project Structure

- `src/test/java/com/awesome/testing/tests/bidi/` - BiDi test examples
  - `w3c/` - W3C WebDriver BiDi protocol tests
  - `cdp/` - Chrome DevTools Protocol integration tests
  - Each folder contains both local and remote test examples

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

