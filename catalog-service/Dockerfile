FROM openjdk:17-jdk-slim
WORKDIR app/
#EXPOSE 1113
COPY target/*.jar catalog-service-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "catalog-service-1.0-SNAPSHOT.jar"]

# docker build --tag=ms-2-nacos/catalog-service:1.0-SNAPSHOT .