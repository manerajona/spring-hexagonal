# Build stage
FROM maven:3.8.5-openjdk-17-slim AS build

COPY src /home/app/src

COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Package stage
FROM openjdk:17-jdk-slim

COPY --from=build /home/app/target/ong-0.0.1-SNAPSHOT.jar /usr/local/lib/ong.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Xms512m", "-Xmx2g", "-Dspring.profiles.active=docker", "/usr/local/lib/ong.jar"]
