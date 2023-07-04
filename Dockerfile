# Build stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/cmpt276-project-0.0.1-SNAPSHOT.jar cmpt276-project.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cmpt276-project.jar"]
