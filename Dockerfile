# Use a base image with Java 21
FROM openjdk:21

# Set the working directory
VOLUME /tmp

# Add build argument to find the jar file
ARG JAR_FILE=target/golfclub-api-0.0.1-SNAPSHOT.jar

# Copy jar file into the container
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
