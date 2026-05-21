# Spring Boot Guide Labs

This repository is a set of small Spring Boot lab projects based on Spring guides and related Spring documentation. Each top-level folder is an independent Maven project with its own `pom.xml` and Maven wrapper. There is no root multi-module Maven build, so run Maven commands from inside the lab you want to work on.

## Prerequisites

- A JDK that matches the lab `pom.xml`.
  - Java 17: `restful-webservice`, `spring-rest-service`, `msg-rabbitmq`, `msg-redis`, `upload-file`
  - Java 21: `soap-spring`
  - Java 25: `caching-data`, `dependency-injection`, `gateway`, `graphql-spring`, `ldap-spring`, `neo4j`
- Docker Desktop or another Docker environment for the Redis and RabbitMQ messaging labs.
- A local Neo4j instance for the Neo4j lab. The app expects `bolt://localhost:7687`, username `neo4j`, and password `password`.

## Labs

| Folder | Topic | Source | Java | Notes |
| --- | --- | --- | --- | --- |
| `restful-webservice` | Simple REST endpoint | [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/) | 17 | `GET /greeting` returns a greeting JSON document. |
| `spring-rest-service` | REST API with JPA, H2, and HATEOAS | [Building REST services with Spring](https://spring.io/guides/tutorials/rest/) | 17 | Manages sample employees through `/employees`. |
| `upload-file` | Web file upload | [Uploading Files](https://spring.io/guides/gs/uploading-files/) | 17 | Thymeleaf form at `/`; uploads are limited to 10 MB. |
| `graphql-spring` | GraphQL API | [Building a GraphQL service](https://spring.io/guides/gs/graphql-server/) | 25 | GraphiQL is enabled at `/graphiql`; GraphQL endpoint is `/graphql`. |
| `soap-spring` | SOAP web service | [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/) | 21 | WSDL is available at `/services/countries.wsdl`. |
| `ldap-spring` | LDAP authentication | [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/) | 25 | Uses embedded LDAP data from `schema.ldif`; login user `ben`, password `benspassword`. |
| `caching-data` | Spring cache abstraction | [Caching Data with Spring](https://spring.io/guides/gs/caching/) | 25 | Command-line demo showing repeated ISBN lookups. |
| `msg-redis` | Redis publish/subscribe messaging | [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/) | 17 | Uses `compose.yml` and Spring Boot Docker Compose support for Redis. |
| `msg-rabbitmq` | RabbitMQ messaging | [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/) | 17 | Uses `compose.yml` and Spring Boot Docker Compose support for RabbitMQ. |
| `gateway` | Spring Cloud Gateway | [Building a Gateway](https://spring.io/guides/gs/gateway/) | 25 | Routes `/get` to `http://httpbin.org` and includes a `/fallback` route. |
| `neo4j` | Spring Data Neo4j | [Accessing Data with Neo4j](https://spring.io/guides/gs/accessing-data-neo4j/) | 25 | Requires a running Neo4j database with the credentials above. |
| `dependency-injection` | Dependency injection patterns | [Spring Beans and Dependency Injection](https://docs.spring.io/spring-boot/reference/using/spring-beans-and-dependency-injection.html) | 25 | Console demo for constructor, setter, and interface-style injection. |
| `azure` | Generated Azure project output | n/a | n/a | This folder currently contains `HELP.md`, IDE files, and build output only. It has no `pom.xml` or source tree checked in. |

## Running a Lab

On Windows:

```powershell
cd restful-webservice
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```sh
cd restful-webservice
./mvnw spring-boot:run
```

Most web labs start on `http://localhost:8080`. Run one web lab at a time, or pass another server port when starting the app.

```powershell
.\mvnw.cmd "-Dspring-boot.run.arguments=--server.port=8081" spring-boot:run
```

## Testing and Building

Run tests from inside a lab:

```powershell
.\mvnw.cmd test
```

Build an executable jar:

```powershell
.\mvnw.cmd clean package
java -jar target\<artifact-id>-0.0.1-SNAPSHOT.jar
```

Use the artifact ID from the lab table or from that lab's `pom.xml`.

## Useful Try-It Commands

REST greeting:

```powershell
curl.exe "http://localhost:8080/greeting?name=Spring"
```

Employee API:

```powershell
curl.exe "http://localhost:8080/employees"
curl.exe "http://localhost:8080/employees/1"
```

GraphQL query in GraphiQL:

```graphql
query {
  bookById(id: "book-1") {
    name
    pageCnt
    author {
      firstName
      lastName
    }
  }
}
```

SOAP request:

```powershell
cd soap-spring
.\mvnw.cmd spring-boot:run
```

Then send the request from another terminal:

```powershell
cd soap-spring
curl.exe --header "content-type: text/xml" --data "@src/main/resources/request.xml" "http://localhost:8080/services"
```

File upload app:

```text
Open http://localhost:8080 and submit a file through the form.
```

## External Services

`msg-redis` and `msg-rabbitmq` include `compose.yml` files and the `spring-boot-docker-compose` dependency. With Docker running, Spring Boot can start those containers automatically when the application starts. You can also start them manually:

```powershell
docker compose -f msg-redis\compose.yml up -d
```

```powershell
docker compose -f msg-rabbitmq\compose.yml up -d
```

`neo4j` does not include a compose file. Start Neo4j separately and make sure the connection settings in `neo4j/src/main/resources/application.properties` match your local database.
