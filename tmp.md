# Keywords to be read

Spring

	- Injection dependencies and reflection
	- @RestController, @Service (@Component), @Repositpry

Microservice

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

	- DTO vs BEAN vs POJO vs Entity7



= = = =

- Change the POST endpoint to receive a body parameter
	- create the "parameter" class 
	- add as a parameter in the method using annotation

- reuse the "name builder logic" on both GET and POST endpoints

- how to create Service in Spring 	


	


# What i did 

	12/06
		- configuration of liquibase and h2 data (dependecies)
		- Create two JPA entity classes: Process and ShortSellingEligibleSecurity. These entities will represent the tables in the database.
		- started to create a endpoint 
		-started to complete the file changelog 

	13/06
		- List the Process challenge 			1§
			- List the Process table with an endpoint
				- create an endpoint to list the Processes (GET)
					- Path: '/api/v1/process'
				- create a method in order to list the existing Processes (method to fetch the data from the repository)
		
	12/06
	-git: 
	- staging
	- commit (message)
	- push to remote 
     -started to convert the file
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
    20 a 22
    -finished integration test 
    
    26/06
    -started Import file - "UK list of exempted shares"
    -created a second endpoint post
    
    29/06
    - we need to validate the UK Entity persistence using the UkListOfExemptedSharesService (hint: follow the ShortSellingEligibleSecurityService)
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	