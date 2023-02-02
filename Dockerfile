FROM maven:3.8.5-openjdk-17-slim
WORKDIR /
COPY pom.xml .
COPY src src
RUN mvn package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /
COPY --from=0 /target/*.jar /app.jar
CMD ["java", "-jar", "app.jar"]