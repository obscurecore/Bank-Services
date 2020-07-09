# Bank-Services

Microservice project with reactive approach for the banking application.

Below you will find some information on how to perform common tasks.<br>

## Table of Contents
- [Updating to New Releases](#updating-to-new-releases)
- [Sending Feedback](#sending-feedback)
- [Briefing](#briefing)
- [Project Structure](#project-structure)
  - [Eureka Server](#eureka-server)
  - [Zuul Service](#zuul-service)
  - [Security Service](#security-service)
  - [Stonks Service](#stonks-service)
  - [User service](#user-service)
  - [Fine Card Service](#fine-card-service)
- [Supported Language Features and Tools](#supported-language-features-and-tools)
- [Integrating with an API Backend](#integrating-with-an-api-backend)
- [Deployment](#deployment)
  - [AWS](#azure)
- [Description](#description)
- [Build](#build)

## Updating to New Releases

When you run `application`, it always creates the project with the latest version of `scripts` so you’ll get all the new features and improvements in newly created apps automatically.
To update an existing project to a new version, [open the changelog](https://github.com/obscurecore/Bank-Services/master/CHANGELOG.md), find the version you’re currently on, and apply the migration for the newer versions.
We commit to keeping the breaking changes minimal, so you can upgrade painlessly.

## Sending Feedback
We are always open to [your feedback](https://github.com/obscurecore/Bank-Services/issues).

## Briefing


## Project Structure

After creation, your project should look like this:

```
Bank-Services/
  README.md
  CHANGELOG.md
  eureka-server/
    ...
  zuul-serice/
    ...
  security-service/
    ...
  stonks-service/
    ...
  user-service/
    ...
  fine-card-service/
    ...
  .gitignore
```
You can delete or rename files.
### `Eureka Server`

Eureka Server (the service registry) - application, that contain meta about all client service applications.
<br> Each service `register` on Eureka and Eureka knows all services, running on each port and IP address.


Service interacts with Server in this way:

1. Client register information about running instance on Eureka. 
2. Every 30 seconds Client send a request to the server to inform that it still alive, if server doesn't see update within 90 seconds, it just removes instance.
3. Eureka Client get information from the server and store in itself as cache and update every 30 seconds. This meta automatically update.
4. Getting the updates, Client verify information with the server, comparing count of instances and if it has error, the server will send again information.
5. At the end of the running Client send cancellation request to the server. In this way instance will be deleted from registry.

Client that was used 3 unsuccessful attempts with interval in 30 seconds will be deleted. 

### `Zuul Service`
### `Security Service`
### `Stonks Service`
### `User Service`
### `Fine Card Service`


## Supported Language Features and Tools

This project supports a superset of the latest Java standard.<br>
In addition to [Java SE 11](https://docs.oracle.com/en/java/javase/11/) syntax features, it also includes:

* [Spring Boot](https://spring.io/projects/spring-boot) 
* [Spring Security](https://spring.io/projects/spring-security)
* [Spring WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html) 
* [Spring Cloud Netflix](https://spring.io/projects/spring-cloud-netflix) 
* [Spring Data](https://spring.io/projects/spring-data) 
* [Spring AOP](https://docs.spring.io/spring/docs/2.5.x/reference/aop.html) 
* [Project Lombok](https://projectlombok.org/)
* [Docker](https://www.docker.com/)
* [Bash](https://www.gnu.org/savannah-checkouts/gnu/bash/manual/bash.html)


Learn more about: [Microservices](https://martinfowler.com/articles/microservices.html),
 [RxJava](https://github.com/ReactiveX/RxJava),
 [Message Sevice](https://microservices.io/patterns/communication-style/messaging.html).

Note that **the project only includes a few Java SE 11**:

* [Local-Variable Type Inference](https://docs.oracle.com/javase/10/language/toc.htm#JSLAN-GUID-7D5FDD65-ACE4-4B3C-80F4-CC01CBD211A4)


## Integrating with an API Backend

## Build

### How to build
`./mvnw clean install`

### How to run
`./mvnw spring-boot:run`

