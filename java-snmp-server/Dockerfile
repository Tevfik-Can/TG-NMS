# Use a lightweight OpenJDK 17 runtime image
FROM eclipse-temurin:17-jre-jammy

# Set working directory inside the container
WORKDIR /app

# Copy your built JAR file into the container
COPY target/snmp-server-1.0.0.jar ./snmp-server.jar

# Expose the port your app listens on (e.g., 8080 for REST API)
EXPOSE 8080

# Command to run your Java application
CMD ["java", "-jar", "snmp-server.jar"]
