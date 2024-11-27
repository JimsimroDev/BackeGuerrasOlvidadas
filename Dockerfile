FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/guerrasOlvidadas-0.0.1.jar
COPY ${JAR_FILE} app_guerrasOlvidadas.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_guerrasOlvidadas.jar"]
