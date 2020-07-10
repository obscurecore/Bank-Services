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
    - [Stonks service](#stonks-service)
        - [Get All Data](#ss-get-all-data-from-bd)
        - [Insert Data into DB](#ss-insert-data-into-bd)
- [Deployment](#deployment)
  - [AWS](#azure)
- [Build](#build)
    - [Mongo](#mongo)
    - [Maven](#maven)
    - [Additional Instructions](#additional-instructions)

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

 
Interaction of servers with each other is the same as with Client and Server.
If has problem it try to check all peer nodes or protect already available.
### `Zuul Service`
### `Security Service`
### `Stonks Service`

Represent of a simple crud application with Mongo - DB, WebFlux - reactive, Lombok - convenience.



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

### `Stonks Service`
#####  SS, Get all data from BD. 
* **URL** &ensp; /getAll
* **Method:** &ensp; `GET`
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[
        {
            id : 12,
            title : "Java",
            description : "OOP",
            personalNumber : 200,
            imageLink : "https://...."
        },
        {
            id : 2,
            title : "Java",
            description : "Stream API",
            personalNumber : 437,
            imageLink : "https://...."        
        }
    ]`
 
* **Sample Call:**

  ```javascript
  $.ajax({
     url: "/getAll",
     dataType: "json",
     type : "GET",
     success : function(r) {
       console.log(r);
     }
  });
  ```
  ```http request
  GET http://localhost:8081/getAll
  Accept: application/json
  ```
---
#####  SS, Insert data into BD. 
* **URL** &ensp; /create 
* **Method:** &ensp; `POST`
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** empty
    
* **Error Response:**  
   * **Code:** 400 BAD REQUEST <br />
     **Content:** `{ title : "Max length is 10" }`
* **Sample Call:**

  ```javascript
  $.ajax({
     url: "/getAll",
     contentType: "application/json",
     type : "POST",
     data : JSON.stringify({ "id": 123, "title": "Spring Data", "description": "Cassandra", "personalNumber": 1973, "imageLink": "https://..."}),
     success : function() {
       console.log("GJ");
     }
  });
  ```
  ```http request
  POST http://localhost:8081/create
  Content-Type: application/json
  
  {
    "id": 123,
    "title": "Spring Data",
    "description": "Cassandra",
    "personalNumber": 1973,
    "imageLink": "https://..."
  }
  ```
## Deployment

###AWS  

## Build

###Mongo
 * `docker pull mongo` &ensp; - &ensp; merge with docker-hub last version.
 * `docker images` &ensp; - &ensp;check image.
 * `docker run mongo` &ensp; - &ensp; launch mongo. default port is 27017 or specify`docker run mongo --port 27017`.
 * `mongo` &ensp; - &ensp;  get into mongo shell.
 *  In case if you start stonks service `use stonksdb` - where stonksdb is the name of a database (see the application.yml) and `show collection` to see stonks object (see the Stonks class).
   
###Maven

* **Build:**<br>
`./mvnw clean install`
* **Run:**<br>
`./mvnw spring-boot:run`

###Additional Instructions
 * `./tools/test_cli env` &ensp; - &ensp; (docker compose up) for start or restart all services, aggregates the output of each container. 
 * `./tools/test_cli env_start`&ensp; - &ensp; (docker compose start) start the previously stopped container.
 * `./tools/test_cli env_stop`&ensp; - &ensp; (docker compose stop) stop containers, but won't remove them. 
 * `./tools/test_cli env_down`&ensp; - &ensp; (docker compose down) stop containers, and it removes. 
 * `./tools/test_cli env_restart`&ensp; - &ensp; (docker compose restart) restart one or more containers 
