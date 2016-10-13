
# Mississippi Vendor Challenge

## PROTOTYPE URL
http://mdhs-prototype.portlandwebworks.com/

## 4.1 DOCUMENTATION

### Description

The application has been developed to meet the submission requirements for the Mississippi Vendor Challenge. The application includes the ability to perform a proximity search for a child care provider and applying a variety of filters to the search and the results.

The application was developed using best practices for user centered design and agile development, which is detailed in further detail within this document. These are all practices that our company employs on a regular basis through our extensive work with public-sector agencies.

### Installation

#### mdhs-frontend

Requirements:

* Java 8
* Maven 3.x (must be able to run mvn on the command line)
* Node + NPM

**To Run:**
```
npm install
mvn spring-boot:run
```
Point a browser to http://localhost:8080/index.jsp

To keep your SASS files compiling and jslint checking run:
```
gulp watch
```

***Current Endpoints:***

* /api/facilities
  * Supports GET
  * GET returns sample facilities

Refer to model objects in Java source for available properties. 

## Project Narrative
A more comprehensive description of our Technical Approach can be found on our Confluence wiki - [Link](https://confluence.portlandwebworks.com/display/MSPOC)

In the event that external artifacts are not considered admissable, we have also provided many of the associated documents and images within this repository - [Link](https://confluence.portlandwebworks.com/display/MSPOC/POC+Requirements)

## 4.2 TEAM STRUCTURE
**Assigned a team leader.**

John Gordon, Director of Software Development

**Team Members (and corresponding ADPQ labor categories)**

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

## 4.3 RESEARCH AND DISCOVERY

User research and testing included the following (see [Approach](https://confluence.portlandwebworks.com/display/MSPOC/Approach) section of project wiki):

* User
* Analogous research
* Team ideation meetings
* Initial wireframes reviewed with user
* Updated wireframe reviewed by external testers
* Prototypes reviewed by external users

**Human-centered design techniques or tools**

Multiple human-centered design techniques, were used in the development of the PoC. These included:

* Creation of wireframes - [Link](http://un6mm3.axshare.com/)
* Creation of "user stories"
* Creating a Product Backlog - [Link](https://confluence.portlandwebworks.com/display/MSPOC/Agile+Artifacts)
* Sharing findings with the team and incorporation of feedback
* Use of a simple and flexible design style guide - [Link](https://confluence.portlandwebworks.com/download/attachments/6291861/mdhs-design-doc1.pdf?version=1&modificationDate=1476379120750&api=v2)
* Usability testing of wireframes
* Usability testing of prototypes

**Performed usability tests with people**
Usability tests were performed at several points in the development process, including:

* Internal testing of initial concepts
* Testing of wireframes
* Testing of working prototypes

## 4.4 DESIGN

**Created or used a design style guide**
A visual style guide was created by the designer to define styles, colors, fonts, etc. [Link](https://confluence.portlandwebworks.com/download/attachments/6291861/mdhs-design-doc1.pdf?version=1&modificationDate=1476379120750&api=v2)

**Responsive Design**
The PoC has been developed as mobile-responsive. Quality Assurance testing assured that the PoC matched business requirements:

* Regression testing of desktop, mobile, and tablet
* One test case for each user story
* If test case passes the story is closed, if it fails a subtask is created and it is retested
* Fixes not addressed were added to the Backlog for future enhancements

## 4.5 PROTOTYPE

**Our iterative approach**

Our iterative approach consisted of the following steps:

* Set up team collaboration site in Confluence – [Link](https://confluence.portlandwebworks.com/display/MSPOC)
* Feedback on the PoC sought and incorporated throughout
* Use of Scrum methodology
* On-going grooming of the product backlog
* Development and code reviews completed within a single Sprint
* Sprint Demo for review by Product Owner

**Use of modern and open-source technologies**

Numerous open-source technologies have been utilized. They include:

* HTML/SASS/CSS - front-end layout and styling
* AngularJS 1.5.5 - client site interaction and application logic
* Node/NPM with Bower+Gulp - Manage JS dependencies and SASS/JS build tasks
* Spring Boot with Hibernate / JPA and Jersey - server side logic
* Liquibase - Database schema migration source control
* TravisCI - continuous integration
* HSQLDB - data storage

**Deployed the prototype on PaaS**
* The PoC has been deployed to Google Cloud Container Engine. 
* The Container Engine is built on the open source Kubernetes system, providing flexibility to take advantage of on-premises, hybrid, or public cloud infrastructure. 
* Many cloud providers are working to integrate Kubernetes into their platforms such as Red Hat, Microsoft, IBM, OpenStack, and VMware. Kubernetes can also be deployed to Amazon GovCloud. 
* Kubernetes also has a number of other benefits such as the ability to automatically scale based on real-time user demand. Please see the kubernetes (https://github.com/portlandwebworks/mdhs-prototype/tree/develop/kubernetes) folder for a functional demo of the code used to provision the prototype environment.

**Developed automated unit tests for their code**

JUnit and EasyMock were utilized to cover unit testing needs while utilizing Spring based design methodologies to help write testable code. First pass integration tests were also established using the following technologies:

* Protractor
* Cucumber
* WebDriver
This setup allows easy build out of an automated test suite that would be used as a regression level tests and automated on the integration server.

For integration tests, testing was conducted using Protractor, Cucumber, and Selenium:
* Protractor is designed to drive AngularJS apps with base steps written in JavaScript
* Cucumber employs base steps using pseudo-human-readable scripts
* Selenium drives automation in browsers

**Used a continuous integration system**
* This project is leveraging Travis CI for it's build environment. 
* All code pushed to GitHub is automatically run in Travis and if there are any test failures the team is notified in the projects Slack channel. 
* If there are no test failures, the most recent code is automatically deployed to the Continuous Integration environment. 
* Travis CI also handles deployment of the Docker images to a public repository and it integrates directly with Kubernetes to release the most recent version of the Docker images.

**Used configuration management**
By utilizing Kubernetes, we are able to deploy and update secrets and application configuration without rebuilding the Docker image and without exposing sensitive data in your project source code.

**Setup or used continuous monitoring**
This project is monitored using Google Stack Driver, the monitoring tools are built into the Google Cloud Platform. Additionally, StackDriver Logging aggregates and analyze all of the logs from the deployed containers. The following tests are in place:

* URL Monitoring - Tracking and alerting on the availability of the front end and backend services
* Disk Throughput - Monitoring the disk usage on the Kubernetes nodes. Alerting if throughput is sustained near the maximum
* Cluster CPU - Monitoring the CPU of kubernetes cluster

**Deployed software in a container**

This project is deployed using Docker container technology. This allows the application to be portable between most major cloud providers, as well as providing a consistent environment between development and production.

**Provided sufficient documentation to install and run their prototype on another machine**
The README.md file located in the repository contains complete instructions for deploying and running the prototype on another machine.

**Prototype and underlying platforms used to create and run the prototype are openly licensed and free of charge**
All of the tools used to create and run the prototype are openly licensed and free of charge and are commonly used by the Portland Webworks development team.

## CONTACT INFO

Phone: 207-773-6600 Website: http://www.portlandwebworks.com Email: info@portlandwebworks.com

## COPYRIGHT

Copyright 2016 Portland Webworks. All rights reserved.
