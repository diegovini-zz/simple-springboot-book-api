#Build Application
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY . .
RUN apk add --no-cache git
RUN git clone https://github.com/diegovini/simple-springboot-book-api
RUN  ./mvnw package
ARG JAR_FILE=target/*.jar
COPY  ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]