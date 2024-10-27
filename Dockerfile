FROM openjdk:latest
COPY ./target/"semAssessment-0.1.0.1(0.1-alpha-1).jar" /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semAssessment-0.1.0.1(0.1-alpha-1).jar", "db:3306", "10000"]