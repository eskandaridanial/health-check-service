# HCS

### A monitoring tool in Java that reports health checks of TCP and HTTP layers.

---

# Table of Contents
1. [Introduction](#introduction)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [API Documentation](#api-documentation)
5. [Authentication and access](#authentication-and-access)

---

## Introduction

**HCS** is a monitoring tool written in Java that provides health checks for both TCP layer(network layer) and HTTP layer(application layer). It features a dynamic scheduler and a non-blocking mechanism to continuously check the health of specified destinations over TCP and HTTP.

---

## Technologies

- Java 21
- Spring Boot 3.3.4
- Maven as build tool
- Postgres
- RabbitMQ

---

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/eskandaridanial/health-check-service
    ```

2. Navigate into the project directory:

    ```bash
    cd health-check-service
    ```
3. Build the project:

   Using Maven:
    ```bash
    mvn clean package
    ```
4. Build the docker image:

    ```bash
    docker build -t health-check-service:latest .
    ```

5. Run the docker-compose.yml file:
    
    ```bash
    docker compose up -d
    ```

6. Test if HCS is up and running:
    
   ```bash
    telnet localhost 8000
    ```

**NOTE:** if you are using **MacOs** or **Windows**, you must comment the `health-check-service` section from the `docker-compose.yml` because containerised HCS is using `network-mode: host` and this network feature of docker only works on Linux environment.

**You must run the jar file manually in the MacOs and Windows environment using the command below or Just run the `health-check-service` on the IDE of your choice. You can run Postgres and RabbitMQ services using the available docker-compose.yml file on the project's root directory.**

```bash
java -jar health-check-service-{version}.jar
```

---

## API Documentation

Once the application is up and running, you can access the API documentation on: 

```thymeleafurlexpressions
http://localhost:8000/swagger-ui/index.html
```

---

## Authentication && Access

1. Creating a user

Before using the health check APIs, you need to create a user first by making a POST request to the `/heal/v1/users` endpoint.

```bash
curl --location 'localhost:8000/heal/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"root",
    "password":"root@root"
}'
```


2. Getting an access token

Now, you need to get an access token by making a POST request to the `/heal/v1/auth` endpoint with your username and password.

```bash
curl --location 'localhost:8000/heal/v1/auth' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"root",
    "password":"root@root"
}'
```

**In the response of the `/heal/v1/auth` a token is generated and assigned to your user. You can now experiment the health check APIs by adding this token as a Bearer token inside the header of you requests.**

---