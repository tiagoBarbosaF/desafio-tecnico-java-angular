FROM eclipse-temurin:25-jdk
LABEL authors="tiagobarbosa"

WORKDIR /app
COPY . .
RUN ./gradlew clean bootJar

EXPOSE 8080
CMD ["java", "-jar", "backend/build/libs/backend-0.0.1.jar"]
