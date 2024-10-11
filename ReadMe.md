# HCS

### A monitoring tool in Java that reports health checks of TCP and HTTP layers.

---

# Table of Contents
1. [Introduction](#introduction)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [API Documentation](#api-documentation)
5. [Authentication and access](#authentication-and-access)
6. [Purpose of the project](#purpose-of-the-project)
7. [How it works internally](#how-it-works-internally)
8. [Gathering the health check reports for notification](#gathering-the-health-check-reports-for-notification)
9. [Monitoring on the TCP layer](#monitoring-on-the-tcp-layer)
10. [Monitoring on the HTTP layer](#monitoring-on-the-http-layer)

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

## Purpose of the project

The purpose of this project is to provide a monitoring tool that can be used to check the health of TCP and HTTP destinations. It is designed to be used in a microservices architecture where each service can be monitored and checked for health. **HCS** supports two types of health checks:

- **TCP Health Check:** This type of health check is used to check the health of a TCP destination by connecting to it and sending a TCP request. If the destination is not reachable or the response time is longer than the specified threshold, the destination is considered unhealthy.
- **HTTP Health Check:** This type of health check is used to check the health of an HTTP destination by sending an HTTP request to it. If the destination returns a non-200 HTTP status code, the destination is considered unhealthy.

---

## How it works internally

**HCS** uses a dynamic scheduler to schedule the health checks based on the interval time specified in the resource configuration. Each resource has its own scheduler and it is responsible for checking the health of the resource periodically.

The moment you create a resource(no matter if it is TCP or HTTP), a Runnable task is created and added to the scheduler. This task is responsible for checking the health of the resource periodically using a recursive approach. It means that the task will keep running until the resource is deleted or updated.

When the task is executed, it will check the health of the resource based on the type of the resource. If the resource is a TCP resource, it will connect to the resource and check if the resource is healthy by sending a TCP request and checking the response. If the resource is an HTTP resource, it will send an HTTP request to the resource and check if the resource is healthy based on the HTTP status code.

---

## Gathering the health check reports for notification

**HCS** uses RabbitMQ as a message broker to send the health check reports to the different internal or external services that need to be notified in case of a health check failure. There are two types of queues in the application:

- **TCP Queue:** This queue is used to send the health check reports of TCP resources. Name of the queue is `tcp-queue`.
- **HTTP Queue:** This queue is used to send the health check reports of HTTP resources. Name of the queue is `http-queue`.

There is a default console logger consumer that logs the health check reports to the console.

This design allows **HCS** to be highly flexible and scalable. You can add more consumers to the queues and notify on more services depending on your needs.

---


## Monitoring on the TCP layer

1. Creating a TCP health check resource

You can create a TCP health check resource by making a POST request to the `/heal/v1/tcp-resource` endpoint.

```bash
curl --location 'localhost:8000/heal/v1/tcp-resource' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {ACCESS_TOKEN}' \
--data '{
    "name":"postgres",
    "host":"127.0.0.1",
    "port":5432,
    "intervalInMs":10000,
    "timeout":10000
}'
```

Parameters:

- `name`: The name of the resource.
- `host`: The host of the destination.
- `port`: The port of the destination.
- `intervalInMs`: The interval time in milliseconds between each health check.
- `timeout`: The timeout in milliseconds for the health check.

2. Updating a TCP health check resource

You can update a TCP health check resource by making a PUT request to the `/heal/v1/tcp-resource` endpoint.

```bash
curl --location --request PUT 'localhost:8000/heal/v1/tcp-resource' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {ACCESS_TOKEN}' \
--data '{
    "resourceId": "{RESOURCE_ID}",
    "name": "postgres",
    "intervalInMs": 1000,
    "host": "127.0.0.1",
    "port": 5432,
    "timeout": 1000
}'
```

Parameters:

- `resourceId`: The id of the resource to be updated.
- `name`: The name of the resource.
- `host`: The host of the destination.
- `port`: The port of the destination.
- `intervalInMs`: The interval time in milliseconds between each health check.
- `timeout`: The timeout in milliseconds for the health check.

3. Deleting a TCP health check resource

You can delete a TCP health check resource by making a DELETE request to the `/heal/v1/tcp-resource` endpoint.

```bash
curl --location --request DELETE 'localhost:8000/heal/v1/tcp-resource' \
--header 'Authorization: Bearer {ACCESS_TOKEN}' \
--data '{
    "resourceId": "{RESOURCE_ID}"
}'
```

Parameters:

- `resourceId`: The id of the resource to be deleted.

---

## Monitoring on the HTTP layer

1. Creating a HTTP health check resource

You can create a HTTP health check resource by making a POST request to the `/heal/v1/http-resource` endpoint.

```bash
curl --location 'localhost:8000/heal/v1/http-resource' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb290IiwiaWF0IjoxNzI4NjI4ODE3LCJleHAiOjE3Mjg2MzI0MTd9.uuKXLCMwbnJ4xe2cpYjvqWhs3nK3TjpHpQOdUVnO2lU' \
--data '{
    "name":"name-of-the-service",
    "intervalInMs":1000,
    "url":"https://example.com/",
    "httpMethod":"POST",
    "headers":{
        "content-type":"application/json"
    },
    "body":{
       "name":"sample" 
    }
}'
```

Parameters:

- `name`: The name of the resource.
- `url`: The url of the destination.
- `httpMethod`: The HTTP method to be used.
- `headers`: The headers of the request.
- `body`: The body of the request.
- `intervalInMs`: The interval time in milliseconds between each health check.
- `timeout`: The timeout in milliseconds for the health check.

2. Updating a HTTP health check resource

You can update a HTTP health check resource by making a PUT request to the `/heal/v1/http-resource` endpoint.

```bash
curl --location --request PUT 'localhost:8000/heal/v1/http-resource' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer {ACCESS_TOKEN}' \
--data '{
    "resourceId": "{RESOURCE_ID}",
    "name": "sample-service",
    "intervalInMs": 1000,
    "url": "https://example.com/",
    "httpMethod": "POST",
    "headers": {
        "content-type":"application/json"
    },
    "body":{
       "name":"sample" 
    }
}'
```

Parameters:

- `resourceId`: The id of the resource to be updated.
- `name`: The name of the resource.
- `url`: The url of the destination.
- `httpMethod`: The HTTP method to be used.
- `headers`: The headers of the request.
- `body`: The body of the request.
- `intervalInMs`: The interval time in milliseconds between each health check.
- `timeout`: The timeout in milliseconds for the health check.

3. Deleting a HTTP health check resource

You can delete a HTTP health check resource by making a DELETE request to the `/heal/v1/http-resource` endpoint.

```bash
curl --location --request DELETE 'localhost:8000/heal/v1/http-resource' \
--header 'Authorization: Bearer {ACCESS_TOKEN}' \
--data '{
    "resourceId": "{RESOURCE_ID}"
}'
```

Parameters:

- `resourceId`: The id of the resource to be deleted.