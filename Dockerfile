FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw package -Dmaven.test.skip=true
RUN mkdir -p /uploads/gatos && cp src/main/resources/static/images/gatos/* /uploads/gatos/

CMD ["sh", "-c", "java -jar target/*.jar"]
