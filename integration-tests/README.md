Integration Tests
============

### Global Pre-Reqs

1. webdriver-manager: `npm istall -g webdriver-manager`
1. protractor v2.5.1: `npm install -g protractor@2.5.1`
1. cucumber v0.7.0: `npm install -g cucumber@0.7.0`

### Running the Tests

Protractor will automtically start and stop a selenium instance and use a npm provided chromedriver.

    npm install
    protractor config/protractor.js
