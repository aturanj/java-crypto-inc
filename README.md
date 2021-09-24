# Crypto Inc. Live Order Board API

* Tech Stack
  * Java 11
  * Spring Boot 2.5
  * Spring Data JPA 
  * H2 In-Memory Database 
  * MockMVC
  * Swagger API Documentation
  
- H2 Database Console

http://localhost:8080/h2-console/

User/Password: ```sa/sa``` <br/>
URL: ```jdbc:h2:mem:db;DB_CLOSE_DELAY=-1```

- Swagger UI

http://localhost:8080/swagger-ui/

- API endpoints are as follows. All Order operations implemented as ```REST endpoints```. 
However, the ```LiveOrderBoard``` operation could also be implemented as a ```WebSocket endpoint``` in case of a realtime update requirement.

<img src="https://github.com/aturanj/java-crypto-inc/blob/main/crypto-inc-api-swagger.png" width="700" height="550">
