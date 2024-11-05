# Start with a lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the WAR file into the container
COPY target/ELMSPROJECT.war app.war


# Expose the port your app will run on (default Spring Boot port is 8080)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.war"]
