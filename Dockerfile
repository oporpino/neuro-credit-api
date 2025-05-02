# Fase de build
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

COPY target/*.jar app.jar

EXPOSE 4000

CMD ["java", "-jar", "app.jar"]
