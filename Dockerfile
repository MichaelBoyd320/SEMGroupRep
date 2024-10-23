FROM openjdk:latest
COPY "./target/semAssessment-0.1.0.1(0.1-alpha-1)-jar-with-dependencies.jar" /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semAssessment-0.1.0.1(0.1-alpha-1)-jar-with-dependencies.jar"]