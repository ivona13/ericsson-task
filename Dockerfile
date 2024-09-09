FROM openjdk:21-jdk-slim
WORKDIR /ericsson-task
COPY build/libs/*.jar ericsson-task.jar
EXPOSE 8081
CMD ["java", "-jar", "ericsson-task.jar"]