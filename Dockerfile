FROM maven:eclipse-temurin AS builder

WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY src src
COPY pom.xml .

RUN mvn package -Dmaven.test.skip=true

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/assessmentworkshop-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_REDIS_HOST=null SPRING_REDIS_PORT=null
ENV SPRING_REDIS_USERNAME=null SPRING_REDIS_PASSWORD=null
ENV SPRING_REDIS_EXPIRATION=5 PORT=3000

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar

