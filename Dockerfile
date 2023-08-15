FROM openjdk:22-jdk-slim

WORKDIR /app

COPY build/libs/todolist-member-bff-0.0.1.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
