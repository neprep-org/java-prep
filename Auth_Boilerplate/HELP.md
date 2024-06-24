# Spring-Boot Authentication Boilerplate
A spring-boot project that has authentication set for you.

### GETTING STARTED
To start using this authentication boilerplate, you need to follow a few steps:
* Clone the project
* Go to src/main/resource/application.properties file to modify the following:
    * Database credentials and database name
    * Swagger app name, description
    * If you modified the package name, please change the swagger base controller property and replace it with your new package name
* Install the dependencies in pom
* Run the project
* Visit localhost:8000/swagger-ui.html and start using the app

### MODEL

In this authentication boilerplate, I used a User model containing the following properties:
* Id (UUID)
* firstName (String)
* lastName (String)
* phoneNumber (String,digits only, chars=10, example="07********")
* nationalId (String, digits only, chars=16)
* gender (String, enum= MALE, FEMALE, OTHERS)
* password(String, 1 uppercase, 1 lowercase, 1 special character, 1 digit)


### AUTH MECHANISM
In this project we used JWT in authentication. When you send a request to login api, sending email and password the api returns you a JWT token that is signed with a secret and also has expiry time. Both the properties can be configured in src/main/resources/application.properties 