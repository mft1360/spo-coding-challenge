# SPO Coding Challenge
Build a workforce optimization tool for one of our cleaning partners! Our partner has
contracts with several structures all around Berlin. These structures are all of varying
size (measured in rooms). The workforce of our partner is made up of Senior
Cleaners and Junior Cleaners. Senior Cleaners have a higher work capacity than
Junior Cleaners. Our partner is free to decide how many Senior and Junior Cleaners
are to be sent to clean a structure but there always needs to be at least one Senior
cleaner at every structure to lead the juniors. The goal is to minimize overcapacity at
every structure.

# Design
this project is implemented by spring and spring boot.
You are able to test API on swagger. 
After running this project you can see them with this URL http://localhost:8080/swagger-ui.html  

## the pre-requisites
* You have to install Java 8.
* You have to install Maven 3.

## Technologies: 
* Spring Web
* Mapstruct
* swagger
* Spring REST(use to write test method)
* Lombok (I use Lombok framework in this project. you do not need to write getter and setter method for each class.The project can be run in IDE but is better to install plugin of Lombok)  

### Run test methods:
```
Use "mvn clean package" to run the tests.
```

### Run in development envirenment:
To run the project with **spring-boot:run** in development environment.


### Run in production environment:
Use java -jar file with the below command:  
**java -jar -Dspring.profiles.active=prod jarName.jar**

## Task lists
You can use these following stack technology to make the application more enterprise:
- [ ] Spring Data
- [ ] Spring Security
- [ ] Spring Cloud
- [ ] Spring Session
- [ ] Spring Oauth2
- [ ] Circuit breaker
- [ ] Spring Cache
- [ ] Spring for apache kafka
- [ ] Spring Integration
- [ ] Spring Batch
- [ ] Swagger