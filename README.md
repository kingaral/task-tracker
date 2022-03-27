# Task-tracker
**Libraries used**
    
    Spring Boot 2.5.4
    Custom: Exceptions, Rest Basic Authentication, RestApiExceptionHandler, Converters
    Swagger2 API
    Data JPA
    Flyway-core for migrations
    Spring Specification for filters
    Postgresql
    Actuator
    Lombok
    
**Features**
These services can perform:
    
    Profile CRUD operations.
    Task CRUD operations.

**Installing:**
for running application you need a pre-installed docker and docker-compose 
    
    git clone https://github.com/kingaral/task-tracker
    cd docker 
    docker build  -t akvelon .
    cd ..
    cd docker-compose/postgresql
    docker-compose up
    
    Now you can check it in: 
                            localhost:8080/actuator/health

**Deployment**

    I deploy this application into my vps server:
**URL:** 
    
    HealthCheck:
    http://165.22.17.38:8080/actuator/health
    Swagger:
    http://165.22.17.38:8080/swagger-ui.html
    
**Versions**

    1.0-SNAPSHOT: Contains Add, View, Update, and Delete Projets and tasks


**Developers:** 
    
    Tokmurzin Mukagali (mukagali.2002.05@gmail.com)

**Authentication:**
I used basic authentication which provided by Spring Security:

    username: qwerty
    password: qwerty