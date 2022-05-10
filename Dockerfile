FROM openjdk:latest
ADD ./target/spring-hello-world-v1.jar spring-hello-world-v1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "spring-hello-world-v1.jar"]
