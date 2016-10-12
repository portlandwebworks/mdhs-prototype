
# Mississippi Vendor Challenge

## PROTOTYPE URL
http://mdhs-prototype.portlandwebworks.com/

## OVERVIEW

The application has been developed to meet the submission requirements for the Mississippi Vendor Challenge. The application includes the ability to perform a proximity search for a child care provider and applying a variety of filters to the search and the results.

The application was developed using best practices for user centered design and agile development, which is detailed in further detail within this document. These are all practices that our company employs on a regular basis through our extensive work with public-sector agencies.

## INSTALLATION

###Requirements:

* Java 8
* Maven 3.x (must be able to run mvn on the command line)
* Node + NPM (Using [nvmrc](https://github.com/creationix/nvm) to manage node installations)

###To Run:

Run the following in the root directory of the repository.

```
npm install
mvn spring-boot:run
```
Point a browser to http://localhost:8080/#/

To keep your SASS files compiling and jslint checking run:
```
gulp watch
```

***Current Endpoints:***

* /api/facilities
  * GET returns facility information
  * Query Parameters:
      * availableOpenings (true|false)
      * licensed (true|false)
      * noConvictions (true|false)
      * city (string)
      * genders (string: MALE|FEMALE|BOTH)
      * ageRanges (string: INFANT|TODDLER|CHILD|PRETEEN|TEEN)
      * capacityMinimum (integer)
      * capacityMaximum (integer)
      * withinDistance (integer)
      * page (integer)
      * sortBy (string)
      * sotryDir (string: ASC|DESC) 	  
* /api/facilities/addresses/cities
  * GET
* /api/facilities/addresses/counties
  * GET
* /api/facilities/addresses/city-zips
  * GET
* /api/facilities/provider-types
  * GET

  
Refer to model objects in Java source for available properties. 

### Database
The database schema is currently controlled by [Liquibase](http://www.liquibase.org/), and it's integration with Spring Boot. On startup the application will automatically run any outstanding migrations.

For development the app can be run completely standalone using the in-memory database [HSQLDB](http://hsqldb.org/). Production would be deployed using something like PostgreSQL.


## TECHNICAL APPROACH

A more comprehensive description of our Technical Approach can be found on our Confluence wiki - Link

In the event that external artifacts are not considered admissible, we have also provided many of the associated documents and images within this repository - Link

**A. Assigned a team leader.**

John Gordon, Director of Software Development

**B. Team Members (and corresponding ADPQ labor categories)**

+ Product Manager: Tom Lovering
+ Technical Architect: Nick Stuart
+ Interaction Designer/User Researcher: Brittany Cornell
+ Visual Designer: Christopher Prinn
+ Front End Developer: Rachel Charow
+ Back End Developer: Joseph Descalzota
+ Dev Ops Engineer: Lyle McKarns
+ Security Engineer: Chris Davis
+ Agile Coach: Alison Schestopol
+ Quality Assurance: Carl Swanson

**C. User Research**

User research and testing included the following:

* User
* Analogous research
* Team ideation meetings
* Initial wireframes reviewed with user - Link
* Updated wireframe reviewed by external testers - Link
* Prototypes reviewed by external users - Link

**D. Used at least three “human-centered design” techniques or tools**

Multiple human-centered design techniques, were used in the development of the PoC. These included:

* Creation of wireframes - Link
* Creation of "user stories" - Link
* Creating a Product Backlog - Link
* Sharing findings with the team and incorporation of feedback - Link
* Use of a simple and flexible design style guide - Link
* Usability testing of wireframes - Link
* Usability testing of prototypes - Link

**E. Created or used a design style guide**
A visual style guide was created by the designer to define styles, colors, fonts, etc. Link

**F. Performed usability tests with people**
Usability tests were performed at several points in the development process, including:

* Internal testing of initial concepts
* Testing of wireframes - Link
* Testing of working prototypes - Link

**G. Used an iterative approach**

Our iterative approach consisted of the following steps:

* Set up team collaboration site in Confluence – Link
* Feedback on the PoC sought and incorporated throughout
* Use of Scrum methodology
* On-going grooming of the product backlog
* Development and code reviews completed within a single Sprint
* Sprint Demo for review by Product Owner

**H. Responsive Design**
The PoC has been developed as mobile-responsive. Quality Assurance testing assured that the PoC matched business requirements:

* Leveraged JIRA plugin test case application called Zephyr - Link
* Regression testing of desktop, mobile, and tablet
* One test case for each user story
* If test case passes the story is closed, if it fails a subtask is created and it is retested
* Fixes not addressed were added to the Backlog for future enhancements

Release Notes

**I. Used at least five modern and open-source technologies**

Numerous open-source technologies have been utilized. They include:

* HTML/SASS/CSS - front-end layout and styling
* AngularJS 1.5.5 - client site interaction and application logic
* Node/NPM with Bower+Gulp - Manage JS dependencies and SASS/JS build tasks
* Spring Boot with Hibernate / JPA and Jersey - server side logic
* Liquibase - Database schema migration source control
* TravisCI - continuous integration
* HSQL/PostgreSQL - data storage

**J. Deployed the prototype on PaaS**
The PoC has been deployed to Google Cloud Container Engine Link. The Container Engine is built on the open source Kubernetes system, providing flexibility to take advantage of on-premises, hybrid, or public cloud infrastructure. Many cloud providers are working to integrate Kubernetes into their platforms such as Red Hat, Microsoft, IBM, OpenStack, and VMware. Kubernetes can also be deployed to Amazon GovCloud. Kubernetes also has a number of other benefits such as the ability to automatically scale based on real-time user demand. Please see the kubernetes (https://github.com/portlandwebworks/mdhs-prototype/tree/develop/kubernetes) folder for a functional demo of the code used to provision the prototype environment.

**K. Developed automated unit tests for their code**

JUnit and EasyMock were utilized to cover unit testing needs while utilizing Spring based design methodologies to help write testable code. First pass integration tests were also established using the following technologies:

* Protractor
* Cucumber
* WebDriver
This setup allows easy build out of an automated test suite that would be used as a regression level tests and automated on the integration server.

For integration tests, happy-path testing was conducted on stories xx, xx, xx, xx using Protractor, Cucumber, and Selenium:
* Protractor is designed to drive AngularJS apps with base steps written in JavaScript
* Cucumber employs base steps using pseudo-human-readable scripts
* Selenium drives automation in browsers

**L. Used a continuous integration system**
This project is leveraging Travis CI for it's build environment. All code pushed to GitHub is automatically run in Travis and if there are any test failures the team is notified in the projects Slack channel. If there are no test failures, the most recent code is automatically deployed to the Continuous Integration environment. Travis CI also handles deployment of the Docker images to a public repository and it integrates directly with Kubernetes to release the most recent version of the Docker images.

**M. Used configuration management**
By utilizing Kubernetes, we are able to deploy and update secrets and application configuration without rebuilding the Docker image and without exposing sensitive data in your project source code.

**N. Setup or used continuous monitoring**
This project is monitored using Google Stack Driver, the monitoring tools are built into the Google Cloud Platform. Additionally, StackDriver Logging aggregates and analyze all of the logs from the deployed containers. The following tests are in place:

* URL Monitoring - Tracking and alerting on the availability of the front end and backend services
* Disk Throughput - Monitoring the disk usage on the Kubernetes nodes. Alerting if throughput is sustained near the maximum
* Cluster CPU - Monitoring the CPU of kubernetes cluster

**O. Deployed their software in a container**

This project is deployed using Docker container technology. This allows the application to be portable between most major cloud providers, as well as providing a consistent environment between development and production.

**P. Provided sufficient documentation to install and run their prototype on another machine**
The README.md file located in the repository contains complete instructions for deploying and running the prototype on another machine.

**Q. Prototype and underlying platforms used to create and run the prototype are openly licensed and free of charge**
All of the tools used to create and run the prototype are openly licensed and free of charge and are commonly used by the Portland Webworks development team.

## CONTACT INFO

Phone: 207-773-6600 Website: http://www.portlandwebworks.com Email: info@portlandwebworks.com

## COPYRIGHT

Copyright 2016 Portland Webworks. All rights reserved.
