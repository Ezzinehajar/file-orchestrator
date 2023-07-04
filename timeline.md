# Keywords to be read

Spring

	- Injection dependencies and reflection
	- @RestController, @Service (@Component), @Repositpry

Microservice : is an architecture for applications that separates an application into several small autonomous web services.

	- RESTful API : is an interface that two computer systems use to exchange information securely over the internet.
		- methods
			- GET :Retrieve a list of items 
			- POST : Create an item 
			- PUT : Update an item
			- etc
		
		- response code
			- 20X
			- 30X
			- 40X
			- 50X 
	
	- JSON	

	- DTO vs BEAN vs POJO vs Entity

# Timeline :: What I did 

## 12/06
- configuration of liquibase and h2 data (dependecies)
- Create two JPA entity classes: Process and ShortSellingEligibleSecurity. These entities will represent the tables in the database.
- started to create a endpoint 
- started to complete the file changelog 

## 13/06
		- List the Process challenge 			
			- List the Process table with an endpoint
				- create an endpoint to list the Processes (GET)
					- Path: '/api/v1/process'
				- create a method in order to list the existing Processes (method to fetch the data from the repository)
		
	12/06
	-git: 
	- staging
	- commit (message)
	- push to remote 
     -started to convert the file csv
     
     13/06:
     -convert lines to object and save data 
     
     15/06
     	- skip/jump the first 5 lines
     	- read the array to fill each field on the ShortSellingEligibleSecurityEntity
     	
    	16/06	
    -finished the read file csv 
    -started the part of integration test 
    19/06  
    -doing the integration test 
    
    20-22
    -finished integration test 
    
    26/06
    -started Import file - "UK list of exempted shares"
    -created a second endpoint post
    
    29/06
    - we need to validate the UK Entity persistence using the UkListOfExemptedSharesService (hint: follow the ShortSellingEligibleSecurityService)

   03/03
   	- Java features
   		- record (https://www.baeldung.com/java-record-keyword)
   		- stream (https://www.baeldung.com/java-8-streams)
   		- @Slf4j to replace "System.out"
   		- Avoid magic numbers adding constants
