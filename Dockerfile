FROM openjdk:17-jdk-slim

WORKDIR /src

COPY target/EDUCONNECT-*.jar /src/EDU.jar

CMD ["java", "-jar", "/src/EDU.jar"]
