# Task Organizer

## Guidelines
- Solution should be Java platform based
- Code should be self-contained and maintained in this repository (during the on-site interview you will be asked to load and compile your project onto a laptop we provide)
- Usage of docker containers to launch application and database is highly recommended to easily switch over to other laptops
- Design should follow a simple pattern including UI app code, and a local database
- UI can be any technology of your convenience; we are not testing you on UI skills - the UI is a means to an end so we can review and test the application server, API design, data design, and database integration
- The application server should supply a REST-based API (we will review this during the interview)
- Database can be SQL or NoSQL
- Hosted services such as app engines or database as service cannot be used

## Problem Definition

Design and develop a web-based taskname organizer for managing day to day tasks. Following high level component diagram and user stories captures the gist of the problem.

![Components](images/comp.png)

### User Story 1 – Sign Up

A user should be able to create an account by providing a username and password. 

![Sign Up](images/sign-up.png)

### User Story 2 – Sign In

A http basic authentication should give user access to their tasks.

![Sign In](images/sign-in.png)

### User Story 3 – Home Screen

Home Screen should show the previous pending tasks in the screen. All tasks should be persisted across sign ins.

![Home](images/home.png)

### User Story 4 – Create a Task
User should be able to create a taskname by clicking on the “+” sign. Task can be created inline or using an input dialog.

### User Story 5 – Update Task
Double clicking a taskname (or some other user action) should allow the user to modify the taskname. Modification can either be inline or using an input dialog.

### User Story 6 – Complete
A taskname can be marked complete by clicking on the check box next to the taskname.


### Siva Murugan Response
I could not integrate the service with the UI layer. I couldn't get time to do the same, though I have implemented all the required API design and can behave exactly as how an web layer would work. This can be tested using curl calls.

### Framework and Technology used
- Spring boot : Rest and Web layer endpoints.
- JPA : for integrating with the DB.
- Spring security : for login and session security control.
- Docker/Docker-Compose : container
- Mysql : DB(Simple and clear structure, hence went with SQL. Have limited informtion on future scopes to decide on NoSQL vs SQL, hence choose later)
- Actuators : for monitoring perspective.
- Java 8

### High-level implementation detail
Two docker images will be generated, one for the service and the other for database. The schema file(schema.sql) would be copied into the container mysql docker init directory for the tables to be created at the startup. I did not mention any data store location for mysql container.

Basically the service exposes two kinds of API, one that requires authentication and other unauthenticated. All authentication(login) and session maintenance will be handled by spring security. The credentials are persisted in the database. We can trim down the unauthenticated API to just the sign-up, but I just for debugging purpose have few GET ALL api's exposed in the unauthenticated endpoint.

Once the user log's in, his session is established and he does not have to login back to access a different API. For security purpose, all the mapping api's are taking the implicit logged in user. This data is retrieved from the security context.

Tasks can be created at using Task API or using Mapping API. It works both ways.

Both Task and Task mapping will not be duplicated, which means if a user creates a task it can be referenced by a different user.

CRUD operation except delete is implemented for all the tables, but we can trim down them if required.

### callouts
- Test cases are not implemented - Time constraint.
- More cleaner code would be preferred - Time constraint.(Though not a bad design/implementation)
- UI integration - Pure time constraint
- Passwords in the rest response are not redacted - can do it if required. 

### What can be done next  
- UI implementation
    - Would create Web controllers(MVC) pattern and use Angular JS to render the content. MVC controller will render the model and the UI can probably call the rest endpoint directly.
- Testing
    - Unit test cases has to be written
    - Integration test cases has to be written
- Redesign 
    - Introduce role based mechanism.
    - If the system becomes more complicated and for separation of concern perspective we can create the following microservice.
    - Userservice : Move the authentication logic over there.
    - Data access Service : We can hide the DB completely with a Data access service layer and make the Rest service call the Data access service
    - Web UI : This will be the final piece, Web UI -> Rest Service -> UserService -> Data Access service.
    
### API

All authorization and API calls are tested using Postman, session can be established in postman.

Only API with Auth prefix required credentials

Signup  

    -
    POST http://localhost:8081/api/user
    {
     	"username" : "sivamurugan",
     	"password" : "password"
     }              
Login

    -
    POST http://localhost:8081/login
    login with your created credentials              
Logout

    -
    POST http://localhost:8081/logout              

Get All users

    -
    GET localhost:8081/auth/api/user
    If logged in already you dont need to pass any authorization header, if not pass Basic Auth authorization header.           
Create a new task

    -
    POST http://localhost:8081/auth/api/task
    {
    	"taskname" : "Office"
    }
    If logged in already you dont need to pass any authorization header, if not pass Basic Auth authorization header.           
Get all task

    -
    GET http://localhost:8081/api/task
Create a new Task mapping for a user/Create a task for a user

    -
    POST http://localhost:8081/auth/api/usertaskmapping/
    {
    	"completed" : false,	
    	"task" : {
    		"taskname" : "Office"
    	}	
    }
    If logged in already you dont need to pass any authorization header, if not pass Basic Auth authorization header.
    
Edit a new Task mapping for a user/Edit a task for a user

    -
    PUT http://localhost:8081/auth/api/usertaskmapping/
    {
    	"completed" : true,	
    	"task" : {
    		"taskname" : "Office"
    	}	
    }
    If logged in already you dont need to pass any authorization header, if not pass Basic Auth authorization header.
Get all users task mapping

    -
    Get http://localhost:8081/api/usertaskmapping/all
    
Get a users tasks

    -
    Get http://localhost:8081/auth/api/usertaskmapping/  
    If logged in already you dont need to pass any authorization header, if not pass Basic Auth authorization header.  
