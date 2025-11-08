FROM maven:3.9.11-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/SmartMeterOne-0.0.1-SNAPSHOT.jar /app

EXPOSE 5000

CMD ["java", "-jar", "SmartMeterOne-0.0.1-SNAPSHOT.jar"]
