FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:11-jdk-slim
EXPOSE 8761
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} registry-service.jar
COPY src/main/resources/application.properties /app/application.properties
ENTRYPOINT ["java","-jar","/registry-service.jar"]