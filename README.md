Repository showcasing WebDriver BiDi functionalities
describe [here](https://www.selenium.dev/documentation/webdriver/bidirectional/)

## Blogpost

Detailed blogpost is available here: https://www.awesome-testing.com/2023/04/exploring-selenium-bidi-functionality.html

## Running Selenium grid

You need to install [Docker](https://docs.docker.com/get-docker/)
and [compose](https://docs.docker.com/compose/install/) first.

Next run:

### Mac

```commandline
docker-compose up
```

### Windows

```commandline
docker compose up
```

### Verification

Go to UI [http://localhost:4444](http://localhost:4444)

Query status [http://localhost:4444/status](http://localhost:4444/status)

### Allure

Installed [Allure](https://allurereport.org/docs/gettingstarted-installation/) is required to generate a report.

At least version 2.24.1 is required to generate single HTML report.

