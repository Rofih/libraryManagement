# ---------------------
# Stage 1: Build Stage
# ---------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (better caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the source code
COPY . .

# Package the app (skip tests for faster build)
RUN mvn clean package -DskipTests

# ------------------------
# Stage 2: Runtime Stage
# ------------------------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Set environment variable for Spring Boot port
ENV PORT=419

# Expose the port
EXPOSE 419

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]
