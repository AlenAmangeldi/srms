FROM openjdk:12
ADD target/Srms-0.0.1.jar Srms-0.0.1.jar
ENTRYPOINT ["java", "-jar", "Srms-0.0.1.jar"]
EXPOSE 8082