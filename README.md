# Micro Basics

A simple Java microservices foundation including three core services (facade, messages, logging). Built with Spring Boot, it demonstrates basic patterns for service boundaries, inter-service communication, logging, and facade aggregation—serving as a learning scaffold or bootstrap template.

## Architecture Overview

```
client → facade-service → { messages-service, logging-service }
```

* **facade-service**: Aggregates and orchestrates calls to the other services; exposes API endpoints to clients.
* **messages-service**: Handles message-related domain logic (e.g. create / fetch messages).
* **logging-service**: Centralised logging / audit service accessible by other microservices.

## Key Features & Goals

* Simple, modular microservices structure
* Inter-service HTTP / REST communication
* Centralised logging (via the logging service)
* Clean separation of concerns
* Educational and extensible foundation

## Requirements & Setup

* Java 11+
* Maven
* (Optional) Docker for containerised deployment

### Running Locally

- Build and start each service in Docker
