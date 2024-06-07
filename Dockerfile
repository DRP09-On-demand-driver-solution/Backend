FROM openjdk:17-jdk-slim

WORKDIR /app

COPY /target/Backend-0.0.1-SNAPSHOT.jar /app/Backend.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/Backend.jar"]