# Flight tracker

This is a flight tracking system demo project.

The project was generated using spring initializr with the following settings:
* "Generate a Gradle project with Java and Spring Boot 2.0.0 RC1"
* Packaging: war
* Java version: 8
* Compile dependencies: spring-boot-starter-web, spring-boot-starter-data-jpa

The application currently using
* h2 database, and drop-create it after every run. (The database is packaged in the war, working out of the box)
* Thymeleaf template engine, for navigating, an showing UI in web browser.
* JUnit fot unit and integration testing

Currently only ISO 8601 DateTime formats work (yyyy-MM-dd'T'HH:mm:ss.SSSZ  eg. 2018-02-14T14:30)

The project can run on spring's embedded tomcat application server, but it was tested on Wildfly 11 too.