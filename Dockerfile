FROM amazoncorretto:21-alpine
WORKDIR /app

# Ensure this matches the <finalName>app</finalName> in your pom.xml
COPY app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]