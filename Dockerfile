# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/devops-blog-1.0-SNAPSHOT.jar /app/devops-blog.jar

# Expose the port that the application will run on
EXPOSE 7000

# Specify the command to run on container startup
CMD ["java", "-jar", "devops-blog.jar"]
