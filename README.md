# blackfriday-api

## Purpose 

This application is made just for learning purposes.

## How to set up the project ?

First of all you need to download and set up your Eclipse IDE for Java EE developers and configure Tomcat as a server(configuration link below).To read and write data you need to create the database which can be done by executing sap_black_friday.sql script. (keep in mind that the implementation of the app is not finished so the database is not complete yet)

[Configure Tomcat for Eclipse](https://www.youtube.com/watch?v=kLgquZ2FiuQ)

### Connecting to the database

This application uses JPA with Hibernate for managing relational databases. For configuration you can use this [guide](https://docs.jboss.org/hibernate/orm/5.4/quickstart/html_single/).

In persistence.xml file you need to change the properties for user and password to your username and password in MySql. You might also need to change the connection string if your MySql Server is running on a different port:

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
	<persistence-unit name="api">
		<!-- ...classed added... -->
	
		 <properties>
           	<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />

            <!--Connection string-->
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:PORT/sap_black_friday" />

            <!--Username here-->
            <property name="javax.persistence.jdbc.user" value="YOUR_USERNAME" />

            <!--Password here-->
            <property name="javax.persistence.jdbc.password" value="YOUR_PASSWORD" />  
            <property name="hibernate.show_sql" value="true" />
            
        </properties>
		
	</persistence-unit>
</persistence>
~~~


## Routes 

| Route| Verb| Access|
| --- | --- | --- |
|http://localhost:YOUR_PORT/blackfriday/api/products| GET - returns all products| None|
|http://localhost:YOUR_PORT/blackfriday/api/products/{id}| GET - returns one product with provided id| None |
|http://localhost:YOUR_PORT/blackfriday/api/webapi/products| POST - adds a new product | signed in as Employee |
|http://localhost:YOUR_PORT/blackfriday/api/products/{id}|DELETE - deletes a products|signed in as Employee|
|http://localhost:YOUR_PORT/blackfriday/api/users|POST - register user|None|
|http://localhost:YOUR_PORT/blackfriday/api/user/auth|POST - log in user|None|
|http://localhost:YOUR_PORT/blackfriday/api/campaigns | GET - returns all |None|
|http://localhost:YOUR_PORT/blackfriday/api/campaigns/{id} | GET - returns one campaign |None|
|http://localhost:YOUR_PORT/blackfriday/api/campaigns | POST - adds campaign | Employee


##### NOTE: Use the postman collection to make requests. If you have trouble requesting see the description of the given request.

## Client
    You can access the client with this path http://localhost:YOUR_PORT/blackfriday/client/index.html. Please keep in mind that the client is really buggy better use postman or if you have problems with the server restart it *sometimes that helps XD*.


#### Example product that can be added
~~~json
    {
        "description": "no desciprtion",
        "image": "no image",
        "isDeleted": false,
        "manufacturer": "Nike",
        "name": "Super cool shoes",
        "minPrice": 20,
        "promotion": 0,
        "quantity": 20,
        "regularPrice": 25
    }
~~~

#### Example user info that can be registered

~~~json
   {
	 "username": "anastas",
	 "password": "123456",
     "role": "employee",
     "image": "no image",
     "email": "myemail@gmail.com"
   }
~~~

#### Example user info that can be logged in

~~~json
   {
	 "username": "anastas",
	 "password": "123456",
     "role": "employee"
   }
~~~

#### Example campaign that can be added

~~~json
   {
	"isActive": true,
    "description": "description",
    "name": "Black friday 2017"
   }
~~~


