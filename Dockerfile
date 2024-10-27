FROM openjdk:latest
COPY ./target/semAssessment.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semAssessment.jar", "db:3306", "10000"]