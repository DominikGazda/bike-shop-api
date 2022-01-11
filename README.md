# Bike - shop
#### Bike online shop application created by using Spring Boot in microservices architecture and React - API. 
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#Features)
* [Rest documentation](#rest-documentation)

## General info
A web application that allows you to manage or place orders in an online store, depending on the administrator or customer role assigned to the user.

## Technologies
Project is created with:
* Java 11
* Spring Boot 2.4.0
* Spring REST
* Spring Cloud
* Spring/Bean Validation
* Spring Data
* Spring Security
* JPA/Hibernate
* MySQL Database
	
## Setup
To run this project, run it locally

1. Download or Clone project:
```
https://github.com/DominikGazda/bike-shop-api
```
2. Open it in IDE as maven project
3. Change database configuration in admin-service, read-dao-service and update-service (application.properties file)
3. Run all services in project

## Features
* Register new account
* Log in to existing account
* Add, modify or delete products and orders in shop using an administator account
* Create new order using a user account
* Manage your account as user
* Manage all accounts as administrator
* Make an appointment at a bicycle repair shop

## Rest documentation
### React-Api (Swagger url: http://localhost:8111/swagger-ui/index.html)
<img src = "https://github.com/DominikGazda/bike-shop-api/blob/develop/images/react-api.png" />
### Admin-Service (Swagger url: http://localhost:7777/swagger-ui/index.html)
<img src = "https://github.com/DominikGazda/bike-shop-api/blob/develop/images/admin-service.png" />
### Read-Dao-Service (Swagger url: http://localhost:8089/swagger-ui/index.html)
<img src = "https://github.com/DominikGazda/bike-shop-api/blob/develop/images/read-dao-service.png" />
### Update-Service (Swagger url: http://localhost:8093/swagger-ui/index.html)
<img src = "https://github.com/DominikGazda/bike-shop-api/blob/develop/images/update-service.png" />



