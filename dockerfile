FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/scrapadjar-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_scrapad.jar
COPY src/main/resources/data/ad.csv src/main/resources/data/ad.csv
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_scrapad.jar"]