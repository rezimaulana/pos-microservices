FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:11-jdk-slim
EXPOSE 1237
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} gateway-service.jar
ENTRYPOINT ["java","-jar","/gateway-service.jar"]