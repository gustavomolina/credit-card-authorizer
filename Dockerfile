# Start with a Maven build stage
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Run the unit tests and build the application
RUN mvn clean package

# Second stage: use a lightweight JDK image for running the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the jar file from the Maven build stage
COPY --from=build /app/target/credit-card-authorizer-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
