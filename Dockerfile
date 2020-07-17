#Build stage
#FROM openjdk:8-jdk-alpine AS build
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /app
COPY . . 
RUN mvn -f pom.xml clean package -DskipTests


#Package stage
FROM openjdk:11-jre-slim AS package-stage
COPY --from=build /app/target/*.jar /usr/local/lib/app.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]