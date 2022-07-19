# ONG Project - Spring Hexagonal Architecture

## Build and Run Locally

Step 1: Build the project.

```sh
mvn clean install -DskipTests
```

Step 2: run on docker mysql (optional)

```sh
docker container run -d --name mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=True -p 3306:3306 mysql:8.0
```

Step 3: run the application.

```sh
mvn spring-boot:run 
```

## Build and Run with Compose

```sh
docker-compose up -d
```

## Swagger api docs

http://localhost:8080/api/docs
