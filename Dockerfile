FROM openjdk:17
EXPOSE 8080
ADD build/libs/Poc2-0.0.1-SNAPSHOT.jar /spring-boot-application-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-application-docker.jar"]
