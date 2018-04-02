FROM openjdk:8
ADD target/docker-idm.jar docker-idm.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-idm.jar"]

