# Fase de build
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM maven:3.9-eclipse-temurin-21

WORKDIR /app
COPY --from=build /app/target/neuro-credit-api-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 4000

CMD ["mvn", "spring-boot:run" ]
