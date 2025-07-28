# Use OpenJDK 17 as the base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY dist/UserService-0.0.1-SNAPSHOT.jar /app/UserService-0.0.1-SNAPSHOT.jar


# Expose the port your Spring Boot app runs on
EXPOSE 8081

# Set the entry point to run the JAR
ENTRYPOINT ["java", "-jar", "UserService-0.0.1-SNAPSHOT.jar"]
