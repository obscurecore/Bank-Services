# Bank-Services

Below you will find some information on how to perform common tasks.<br>

## Table of Contents


- [Description](#description)
- [Stack](#stack)
- [Build](#build)



## Description

![coverage report](src/main/resources/arch.jpg)



#####manifest of a reactive system
![coverage report](src/main/resources/manifest.jpg)
## Stack

* `Microservices`<br> References :
<br>[Martin Fowler](https://martinfowler.com/articles/microservices.html) - for building applications as suites of services. As well as the fact that services are independently deployable and scalable
<br>[Spring Cloud Netflix](https://cloud.spring.io/spring-cloud-netflix/reference/html/)  [ [1](https://medium.com/swlh/spring-cloud-service-discovery-with-eureka-16f32068e5c7) ] - for Service Discovery (Eureka), Circuit Breaker (Hystrix), [Intelligent Routing](https://microservices.io/patterns/apigateway.html) (Zuul)  and Client Side Load Balancing (Ribbon).
<br>[Client-side service discovery](https://microservices.io/patterns/client-side-discovery.html) - pattern

* `Reactor`<br> References : 
<br> [Reactive](https://spring.io/reactive) [ [1](http://reactivex.io/) ] - for low-latency, high-throughput workloads

* `Messaging`<br> References : 
<br> [Chris Richardson](https://microservices.io/patterns/communication-style/messaging.html) - for communicate

* `Spring`: 
<br> AOP
<br> MVC
<br> Data
<br> Security

* `Tools`: 
<br> Docker compose
<br> Bash

* `Server`:
<br> Reactive Netty 


## Build

### How to build
`./mvnw clean install`

### How to run
`./mvnw spring-boot:run`

