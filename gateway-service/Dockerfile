FROM openjdk:17-jdk-slim
WORKDIR /app .
COPY target/*.jar gateway-service-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "gateway-service-1.0-SNAPSHOT.jar"]

# docker build --tag=ms-2-nacos/gateway-service:1.0-SNAPSHOT .