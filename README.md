# Spring Boot Application Demo

This is a simple Spring Boot application that demonstrates how to create a RESTful web service using Spring Boot. 
The application uses an PostgreSQL database to store the data
and uses the Spring Data JPA to interact with the database.

This demo application is useful to test some infrastructure configurations like Docker, Kubernetes, and CI/CD pipelines.

## Running the Application

First, you need to have a PostgreSQL database running. 
You can use the following Docker command to start the docker-compose file that will start a PostgreSQL database:

```bash
docker-compose up -d
```

Then, to run the application locally, you can use the `gradlew` command:

```bash
./gradlew bootRun
```

## Creating a Docker Image

To create a Docker image of the application, you can use the following command:

```bash
docker build --build-arg SKIP_TESTS=true -t image-name .
```

View a summary of image vulnerabilities and recommendations:
```bash
docker scout quickview 
```
