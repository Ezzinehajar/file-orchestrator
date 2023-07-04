# Keywords to be read

## Spring
- What is?
- Injection dependencies and reflection
- Annotations
  - @RestController
  - @Service or @Component
  - @Repository

## Microservice: is an architecture for applications that separates an application into several small autonomous web services
- RESTful API (is an interface that two computer systems use to exchange information securely over the internet)
  - Methods (verbs)
    - GET: Retrieve data 
    - POST: Create data 
    - PUT: Update data
    - etc
		
  - Response codes
      - 20X
      - 30X
      - 40X
      - 50X 
	
- JSON

## Classes Organization
- DTO vs BEAN vs POJO vs Entity (https://www.baeldung.com/java-pojo-javabeans-dto-vo)

## Lombok
- @Data
- @Builder
- @Getter / @Setter
- @AllArgsConstructor
- @Slf4j

# Timeline :: What I did 

## 12/06
- Configuration of liquibase and h2 data (dependencies)
- Create two JPA entity classes: Process and ShortSellingEligibleSecurity. These entities will represent the tables in the database.
- Started to create a endpoint 
- Started to complete the file changelog 

## 13/06
- List the Process table with an endpoint
  - create an endpoint to list the Processes (GET)
  - Path: '/api/v1/process' 
    - create a method in order to list the existing Processes (method to fetch the data from the repository)
		
## 12/06
- Git 	
  - Create branch
  - Commit (with message)
  - Push to remote
  - Pull
  - Open Pull-request
- Started to convert the file csv
     
## 13/06
- Convert lines to object and save data 
     
## 15/06
- skip/jump header lines (the first 5 lines)
- read the array to fill each field on the ShortSellingEligibleSecurityEntity
     	
## 16/06	
- finished the read file csv 
- started the part of integration test 
  
## 19/06  
- Doing the integration test 
    
## 20/22
- finished integration test 
    
## 26/06
- started Import file - "UK list of exempted shares"
- created a second endpoint post
    
## 29/06
- we need to validate the UK Entity persistence using the UkListOfExemptedSharesService (hint: follow the ShortSellingEligibleSecurityService)

## 03/07
- Java features
	- record (https://www.baeldung.com/java-record-keyword)
    - stream (https://www.baeldung.com/java-8-streams)
- Coding/Refactoring    
  - Avoid magic numbers adding constants
- Lombok
  - Builder 
  - @Slf4j to replace "System.out"

## 04/07
- Collections 
  - List
  - Set

## 05/07
- Collections
	- Map
