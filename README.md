# Bank-Services

Microservice project with reactive approach for the bank.

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
  - [Benchamrk](#benchmark)
  - [Bucket Service](#bucket-service)
  - [Config Server](#config-server)
  - [MS Config Properties](#ms-config-properties)
  - [Order Service](#order-service)
  - [Tools](#tools)
- [Supported Language Features and Tools](#supported-language-features-and-tools)
- [Integrating with an API Backend](#integrating-with-an-api-backend)
    - [Stonks service](#stonks-service)
        - [Create](#ss-create)
        - [Read](#ss-read)
        - [Update](#ss-update)
        - [Delete](#ss-delete)
- [Sever side](#server-side)
    - [Change SSH port, add User](#change-port-add-user)
        - [Amazon Linux 2](#amazon-linux-2)
        - [Ubuntu](#ubuntu)
    - [Deployment](#deployment)    
- [Build](#build)
    - [Mongo](#mongo)
    - [Jenkins](#jenkins)
    - [Maven](#maven)
    - [RabbitMQ](#rabbitmq)
    - [Replica](#replica)
    - [Additional Instructions](#additional-instructions)
- [Papers](#papers)
    - [Eureka Server](#eureka-server)
    - [Reactive Programming](#reactive-programming)
        - [Implementation](#implementation)
        - [Reactive Streams](#reactive-streams)
    - [MongoDB](#mongodb)
    - [Feign Client](#feign-client)
    - [RSocket](#rsoket)
    - [WebClient](#webclient)
    - [Ribbon](#ribbon)
    - [Hystrix](#hystrix)
    


## Updating to New Releases

When you run `application`, it always creates the project with the latest version of `scripts` so you’ll get all the new features and improvements in newly created apps automatically.
To update an existing project to a new version, [open the changelog](https://github.com/obscurecore/Bank-Services/master/CHANGELOG.md), find the version you’re currently on, and apply the migration for the newer versions.
We commit to keeping the breaking changes minimal, so you can upgrade painlessly.

## Sending Feedback
We are always open to [your feedback](https://github.com/obscurecore/Bank-Services/issues).

## Briefing
### `Architecture`
![schema_ractor](https://i.ibb.co/MNYvmww/arch.png)

---
### `Functions:`
* Provide responsiveness under load
* The system remains functional even if one of the components fails.
* The system should take up the optimal amount of resources in each time period.
* Communication between services must occur via asynchronous messages.
  Each element of the system requests information from another element,
   but does not expect to get the result immediately.
   Instead, it continues to perform its tasks.

---
### `Plan:`
* Configure Eureka Server, Eureka Client (User-Service, Stonks-Service).
* Use the Postgres, Mongo database, and write reactive services on Spring 5. 
* Make friends with the services and make them communicate via RestTemplate, Feign Client, WebClient for the statistic.
---
* Add the Zuul API Gateway, a Ribbon load balancer.
* Have a Space-service with a spare replica.
* Configure Load Balancer, Configure Config Server And Config Client.
* Add Token Security.
---
* Configure Docker Swarm 
---
* Make several replicas for Eureka Server and other services and make them work together.
* Add a ELK (Elastic Search Kibana) to view logs.
---
* Add Spring Cloud sleutch and Spring Cloud Stream together with Kafka and RabbitMQ.
* Get acquainted with the websocket and Rsocket protocols and build a connection between services.
---
* Use Saga Pattern
* Explore Axon
## Project Structure
After creation, your project should look like this:

```text
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
  benchmark/
    ...
  bucket-service/
    ...
  config-server/
    ...
  ms-config-service/
    ...
  order-service/
    ...
  tools/
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
---
### `Zuul Service`
Gateway, an intermediate level between users and services.

**Filters:**
* `Pre-filters`:
 Performed before routing and can be used for things like authentication, routing and request processing, speed limits, DDoS protection.
* `Endpoint-filters`:
 filters-are responsible for processing the request based on performing pre-filters (health check responses, static error responses, 404).
* `Post-filters`:
They are executed after receiving a response from the source and can be used for metrics, to generate a response for the user, or to add any custom headers.

---
### `Security Service`
![er_security](https://i.ibb.co/Pt6jkqf/bd-security.png)


---
### `Stonks Service`

Represent of a simple crud application with Mongo - DB, WebFlux - reactive, Lombok - convenience.




---
### `User Service`

Will communicate with stonks-service to get data from BD, i.e. not directly.<br>
In case failure of BD or service, it will use Hystrix, that instead of error will return the default value or use replica.
The user won't notice anything.

Also, user service use Ribbon - load balancer between replicas of other services.

---
### `Fine Card Service`

---
### `Benchmark`

---
### `Bucket Service`

---
### `Config Server`
![configserver](https://dzone.com/storage/temp/10546765-config.jpg)



Contains links to the storage with General settings.
To avoid writing the same code in different services, use a common settings store.

When you start your service, it will register with Eureka and access the config-server,
 which will direct it to the remote storage with the settings.
 <br>Then you get the settings among all of them `as follows`:
  It looks at {application} - the name of the service, and then at {profile} -  default profile is default.


---
### `MS Config Properties`

---
### `Order Service`

---
### `Tools`



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

#### SS, Create
* **URL:**&ensp;/create 
* **Method:**&ensp;`POST`
* **Required:**&ensp;`title=[String, NotNull, Length<10]`
* **Optional:**&ensp;`id=[String]`
* **Data Params:**
```
    {
     "title": "Spring",
     "description": "Cassandra",
     "personalNumber": 1973,
     "imageLink": "https://..."
    }
```
* **Success Response:**
  * **Code:** 200 <br />
     **Content:** 
```javascript
    {
        id : 12,
        title : "Java",
        description : "OOP",
        personalNumber : 200,
        imageLink : "https://...."
    }
```
    
* **Error Response:**  
   * **Code:** 400 BAD REQUEST <br />
     **Content:** `{ title : "Max length is 10" }`
     
   * **Code:** 401 UNAUTHORIZED <br />
     **Content:** `{ error : "Log in" }`
* **Sample Call:**

```javascript
  $.ajax({
     url: "/create",
     contentType: "application/json",
     type : "POST",
     data : JSON.stringify({ "id": 123, "title": "Spring", "description": "Cassandra", "personalNumber": 1973, "imageLink": "https://..."}),
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
  
* **Notes:**&ensp;hasRole ("USER","ADMIN")

#### SS, Read
_Meta Information_
* **URL:**&ensp;/
* **Method:**&ensp;`GET`,`POST`, `PUT`, `DELETE`, `PATCH`
* **Required:**&ensp;None
* **Success Response:**
  * **Code:** 200 <br />
    **Content:** `Stonks-Service running at port: 8081`
* **Sample Call:**
  ```javascript
  $.ajax({
     url: "/",
     dataType: "String",
     type : "GET",
     success : function(r) {
       console.log(r);
     }
  });
  ```
  ```http request
  GET http://localhost:8081/
  Accept: application/json
  ```
* **Notes:**&ensp;hasRole ("GUEST","USER","ADMIN")

---
_Read All Data_ 
* **URL:**&ensp;`/getAll`, `/show` - Feign Client (on User-Service side) , `/data` - Rest Template (on User-Service side)
* **Method:**&ensp;`GET`
* **Required:**&ensp;None
* **Success Response:**
  * **Code:** 200 <br />
    **Content:** 
```javascript
     [
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
    ]
```
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
* **Notes:**&ensp;hasRole ("GUEST","USER","ADMIN")

---
_Read By ID_  
* **URL:**&ensp;`read/{id}`
* **Method:**&ensp;`GET`
* **Required:**&ensp;None
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
```javascript
        {
            id : 12,
            title : "Java",
            description : "OOP",
            personalNumber : 200,
            imageLink : "https://...."
        }
```
  * **Error Response:**
  
    * **Code:** 404 NOT FOUND <br />
 
* **Sample Call:**

  ```javascript
  $.ajax({
     url: "/get/{id}",
     dataType: "json",
     type : "GET",
     success : function(r) {
       console.log(r);
     }
  });
  ``` 
  ```http request
  GET http://localhost:8081/get/{id}
  Accept: application/json
  ```
* **Notes:**&ensp; hasRole ("GUEST","USER","ADMIN")

_Read All Data, SSE_  
* **URL:**&ensp;`/stream/stonks`)
* **Method:**&ensp;`GET`
* **Required:**&ensp;None
* **Success Response:**
  * **Code:** 200 <br />
    **Content:** 
```javascript
    [
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
    ]
```
* **Notes:**&ensp; hasRole ("GUEST","USER","ADMIN")

---
_Read Default Data Every 1 Second, SSE_  
* **URL:**&ensp;`/stream/stonks/default`)
* **Method:**&ensp;`GET`
* **Required:**&ensp;None
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
```javascript
        {
            id : "val",
            title : "Python",
            description : "default theme",
            personalNumber : 0,
            imageLink : "https://...."
        }
```
* **Notes:**&ensp; hasRole ("GUEST","USER","ADMIN")

---
_Read All Data Every N Second, SSE_  
* **URL:**&ensp;`/stream/stonks/delay`)
* **Method:**&ensp;`GET`
* **Required:**&ensp;None
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
```javascript
  [
      {
            id : "15",
            title : "PHP",
            description : "MVP",
            personalNumber : 789456,
            imageLink : "https://...."
        },
        {
        }
   ]
    
```
* **Notes:**&ensp;hasRole ("GUEST","USER","ADMIN")

---
#### SS, Update
_Update By ID_  
* **Method:**&ensp;`UPDATE`
* **Required:**&ensp;`title=[String, NotNull, Length<10]`
* **Optional:**&ensp;`id=[String]`
* **Data Params:**
```
    {
     "title": "Spring",
     "description": "Cassandra",
     "personalNumber": 1973,
     "imageLink": "https://..."
    }
```
* **Success Response:**
  * **Code:** 200 <br />
     **Content:** 
```javascript
    {
        id : 12,
        title : "Java",
        description : "OOP",
        personalNumber : 200,
        imageLink : "https://...."
    }
```
    
* **Error Response:**
    * **Code:** 404 NOT FOUND <br />  
    * **Code:** 400 BAD REQUEST <br />
      **Content:** `{ title : "Max length is 10" }`
     
    * **Code:** 401 UNAUTHORIZED <br />
      **Content:** `{ error : "Log in" }`
* **Sample Call:**

```javascript
  $.ajax({
     url: "/update",
     contentType: "application/json",
     type : "UPDATE",
     data : JSON.stringify({ "id": 123, "title": "Spring", "description": "Cassandra", "personalNumber": 1973, "imageLink": "https://..."}),
     success : function() {
       console.log("GJ");
     }
  });
```
```http request
  POST http://localhost:8081/update
  Content-Type: application/json
  
  {
    "id": 123,
    "title": "Spring Data",
    "description": "Cassandra",
    "personalNumber": 1973,
    "imageLink": "https://..."
  }
```
* **Notes:**&ensp;hasRole ("USER","ADMIN")

---
#### SS, Delete
_Delete By ID_  
* **URL:**&ensp;`delete/{id}`
* **Method:**&ensp;`DELETE`
* **Required:**&ensp;None
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
   ```javascript
        {
            id : 12,
            title : "Java",
            description : "OOP",
            personalNumber : 200,
            imageLink : "https://...."
        }
  ```
  * **Error Response:**
  
    * **Code:** 404 NOT FOUND <br />
 
* **Sample Call:**

  ```javascript
  $.ajax({
     url: "/delete/{id}",
     dataType: "json",
     type : "GET",
     success : function(r) {
       console.log(r);
     }
  });
  ``` 
  ```http request
  GET http://localhost:8081/delete/{id}
  Accept: application/json
  ```
* **Notes:**&ensp; hasRole ("USER","ADMIN")

---
_Delete All Data_
* **URL:**&ensp;`/deleteAllStonks`)
* **Method:**&ensp;`DELETE`
* **Required:**&ensp;None
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** NONE
* **Sample Call:**
```javascript
  $.ajax({
     url: "/deleteAllStonks",
     dataType: "json",
     type : "DELETE",
     success : function(r) {
       console.log(r);
     }
  });
``` 
  
```http request
  DELETE http://localhost:8081/deleteAllStonks
  Accept: application/json
```
* **Notes:**&ensp; hasRole ("USER","ADMIN")

---
## Server Side

### Change port, add User 

#### Amazon Linux 2

* Add custom TCP port in the security group.
* Connect via ssh `ssh "<key>" ec2-user@user2-**-**-**-**-**.compute-1.amazonaws.com`.
* Add new user "developer" `sudo useradd -m -d /home/developer developer`.
* Setting password `passwd developer`.
* Add user in group `usermod -a -G wheel developer`.
* Open ssh config file `sudo nano /etc/ssh/sshd_config`.
* Change lines.
```
Port 22 -> Port 12
PaermitRootLogin yes -> PermitRootLogin no
+ AllowUsers developer
``` 
* Open new user's bashrc file.  
```
cd /home/developer
nano .bashrc
```
* Add line `force_color_promt=yes`.
* Reload. 
```
(sudo systemctl enable sshd.service)
(sudo systemctl start sshd.service)
 sudo systemctl stop sshd.service
 sudo systemctl start sshd.service
```
---
#### Ubuntu
* Connect via ssh `ssh root@185.143.173.124`.
* Install sudo `apt-get install sudo`.
* Add new user "developer" `useradd -m -d /home/developer developer`.
* Setting password `passwd developer`.
* Add user in group `usermod -a -G sudo developer`.
* Open ssh config file `nano /etc/ssh/sshd_config`.
* Change lines.
```
Port 22 -> Port 12
PaermitRootLogin yes -> PermitRootLogin no
+ AllowUsers developer
``` 
* Open new user's bashrc file.
```
cd /home/developer
nano .bashrc
```
* Add line `force_color_promt=yes`.
* Reload. `service ssh reload`
### Deployment

## Build

### Mongo
 * `docker pull mongo` &ensp; - &ensp; merge last version with docker-hub.
 * `docker images` &ensp; - &ensp;check image.
 * `docker run mongo` &ensp; - &ensp; launch mongo. default port is 27017 or specify`docker run mongo --port 27017`.
 * `mongo` &ensp; - &ensp;  get into mongo shell.
 *  In case if you start stonks service `use stonksdb` - where stonksdb is the name of a database (see the application.yml) and `show collection` to see stonks object (see the Stonks class).
---   
### Jenkins
**Ubuntu 18**
* `sudo apt-get update` &ensp; - &ensp; update repository 
* `sudo apt-get install openjdk-8-jre` &ensp; - &ensp; install java




---   
### Maven

* **Build:**<br>
`./mvnw clean install`
* **Run:**<br>
`./mvnw spring-boot:run`
---
### RabbitMQ

---
### Replica
**Service**
* `mvn install` &ensp; - &ensp; create jar file
* `java -jar -Dserver.port=8086 stonks-service-0.0.1-SNAPSHOT.jar` &ensp; - &ensp; start the application on different port

**Server**
* `/etc/hosts` add records 
```
127.0.0.1       my-eureka-server.com
127.0.0.1       my-eureka-server-ru.com
127.0.0.1       my-eureka-server-us.com
127.0.0.1       my-eureka-server-fr.com
```
* Start
```
java -jar -Dspring.profiles.active=france eureka-server-0.0.1-SNAPSHOT.jar
java -jar -Dspring.profiles.active=united-states eureka-server-0.0.1-SNAPSHOT.jar
```
* Browser
```
http://my-eureka-server-us.com:9001
http://my-eureka-server-fr.com:9002
```
---
### Additional Instructions
 * `./tools/test_cli env` &ensp; - &ensp; (docker compose up) for start or restart all services, aggregates the output of each container. 
 * `./tools/test_cli env_start`&ensp; - &ensp; (docker compose start) start the previously stopped container.
 * `./tools/test_cli env_stop`&ensp; - &ensp; (docker compose stop) stop containers, but won't remove them. 
 * `./tools/test_cli env_down`&ensp; - &ensp; (docker compose down) stop containers, and it removes. 
 * `./tools/test_cli env_restart`&ensp; - &ensp; (docker compose restart) restart one or more containers 



## Papers
### Eureka Server

`Eureka Server:` It contains a registry of services, and a REST API that can be used to register a service, unregister a service, and determine the location of other services.<br>

`Eureka Service:` Any application that can be found in the Eureka Seven service registry and that can be detected by other services. The service has a specific ID, that can refer to one or more instances of the same application.<br>

`Eureka Instance:` Any application that is registered on the Eureka Server for discovery by others.

`Eureka Client:` Any application that can detect services. It only requests the service registry from Eureka Server to identify running instances of microservices.<br>

#### How it works
By default, the Eureka client starts in the `STARTING` state, which allows the instance to perform initialization for a specific application before it can serve traffic.<br>

#### TIme Lag
All operations with Eureka Client may take some time to be reflected on Eureka Server, and then on other Eureka clients.
This is due to caching of useful data on the server, which is periodically updated to display new information.

#### Status check
After successful registration, Eureka always declares that the app is in the "UP" state.
Change this behavior by enabling Eureka health checks.
  ```yaml
  eureka:
  client:
  healthcheck:
  enabled: true
  ```
### Reactive Programming
Project Reactor is a Java 8 library that implements the reactive programming model.
It is based on the reactive stream specification.

**!!!Reactive types are not intended to process queries or data faster!!!**
Their special feature is their ability to concurrently process more requests and handle delayed operations more efficiently, such as requesting data from a remote server.<br>
#### Implementation 

To find out the drawbacks of imperative programming look at the implementation between the two components
```java
interface ShoppingCardService{                  //(1)
    Output calculate(Input value);          
}
class OrderService {
    private final ShoppingCardService scService;
    
    void process(){                             //(2)
    Input input = ...;
    Output output  = scService.calculate(input);//(2.1)                                                             
    ...                                         //(2.2)
    }
}
```

`How this code works`<br>
In the line 2.1 a synchronous call is made and receiving its result. 
The other code is located in the line 2.2.
In this case the services are tightly coupled. Not possible to perform other actions while the **ShoppingCardService** is busy processing.

`calculate()` blocks the thread that runs the OrderService logic


The problem can be solved using callbacks / Hi, callbackhell
```java
interface ShoppingCardService{                              //(1)
    Output calculate(Input value, Consumer<Output> c);          
}
class OrderService {
    private final ShoppingCardService scService;
    
    void process(){                                         //(2)
    Input input = ...;
    Output output  = scService.calculate(input, output -> { //(2.1)
       ...                                                  //(2.2)
    });                                                          
    ...                                                     //(2.2)
    }
}       
```
`How this code works`<br>
**calculate** take two arguments and nothing return,
 i.e the calling code does not have to wait for a response

Later when the ShoppingCardService sends a response of the callback function, can process (2.2)
```java
class AsyncShoppingCardService implements ShoppingCardService {
    public void calculate(Input value, Consumer<Output> c){
        new Thread(() -> {
            Output result = template.getForObject(...);
            ...
            c.accept(result);
        }).start();
    }
} 
```
#### Reactive Streams 
Reactive streams сonsist of 4-Java interfaces `publisher, subscriber, subscription and processor`.

```java
 public static interface Publisher<T> {
     public void subscribe(Subscriber<? super T> subscriber);
 } public static interface Subscriber<T> {
     public void onSubscribe(Subscription subscription);
     public void onNext(T item);
     public void onError(Throwable throwable);
     public void onComplete();
 }
        
 public static interface Subscription {
     public void request(long n);
     public void cancel();
 }
        
 public static interface Processor<T,R> extends Subscriber<T>,  
   Publisher<R> {
 }
```
They all have the following requirements:

* ASYNC — asynchrony
* NIO - "non-blocking” i / o
* RESPECT BACKPRESSURE - ability to handle cases when data appears faster than it is consumed (this situation does not occur in synchronous, imperative code, but it is common in reactive systems).
A mechanism that allows the recipient to ask how much data they want to get.
In other words, the recipient starts receiving data only when it is ready to process it.
---
![schema_ractor](https://i2.wp.com/springgears.com/wp-content/uploads/2018/11/Blank-Diagram.png?resize=768%2C387&ssl=1?style=centerme)


### MongoDB


### Feign Client

This is a simple and flexible http client that is natively integrated with `Ribbon` and `Hystrix`.<br>
Its feature is that we do not need to know where and on what port a service is located.
We just say Feign to the client, go to `Wonder-Service` and get all the users from him.
Then Feign refers to `Eureka Server` and asks where `Wonder-Service` is located.


If `Wonder-Service` was registered in `Eureka Server`,
 then `Eureka` will know everything about `Wonder-Service` (where it is located, on what port, its URL, etc.).<br>
You only need to describe how to access the remote API service, specifying details such as the URL, request and response body, accepted headers, and so on.


Netflix provides Feign as an abstraction for REST based calls,
### RSocket

### WebClient
For endpoint testing `WebFlux` environment comes with the `WebTestClient` class. 
`WebTestClient` is a thin wrapper around WebClient. 
You can use it to run queries and check responses.


### Ribbon
`Is a load balancer.`
<br>
Out of the box, it is integrated with the Service Discovery mechanism,
 which provides a dynamic list of available instances for balancing between them.
 
 **`Provides:`**
 * Fault tolerance
 * Load balancing
 * Support for multiple protocols (HTTP, TCP, UDP) in asynchronous and reactive models
 * Caching
<br>

By default, Spring Cloud Ribbon uses the `ZoneAwareLoadBalancer` strategy (Servers in the same zone as our service).

---
### Hystrix
`Circuit Breaker pattern.`
<br>
Library that helps control the interaction between services,
 providing fault tolerance and stability to delays,
  which increases the stability of the entire system as a whole  