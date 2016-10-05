# mdhs [![Build Status](https://travis-ci.org/portlandwebworks/mdhs-prototype.svg?branch=develop)](https://travis-ci.org/portlandwebworks/mdhs-prototype)

***Requirements:***


* Java 8
* Maven 3.x (must be able to run `mvn` on the command line)
* Node + NPM

***To Run:***

```
npm install
mvn spring-boot:run
```

Point a browser to `http://localhost:8080/`

To keep your SASS files compiling and jslint checking run:

```
gulp watch
```

***Current Endpoints:***

* /api/facilities
  * Supports GET
  * GET returns sample facilities

Refer to model objects in Java source for available properties. 
