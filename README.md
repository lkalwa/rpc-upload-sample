# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/maven-plugin/reference/html/#build-image)

### Compilation

``./mvnw clean package``

### Running worker
``java -jar rabbitmq.demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=rpc,worker``

### Runing client
``java -jar rabbitmq.demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=rpc,client``

