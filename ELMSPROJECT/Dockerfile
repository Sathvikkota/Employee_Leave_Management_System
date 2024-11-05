# Use the official OpenJDK image
FROM openjdk:20-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the WAR file from the target directory
COPY target/ELMSPROJECT-0.0.1-SNAPSHOT.war app.war

# Expose the port the app runs on
EXPOSE 2039

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.war"]
