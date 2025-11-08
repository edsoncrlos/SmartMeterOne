FROM FROM eclipse-temurin:17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=build /app/target/SmartMeterOne-0.0.1-SNAPSHOT.jar /app

EXPOSE 5000

CMD ["java", "-jar", "SmartMeterOne-0.0.1-SNAPSHOT.jar"]
