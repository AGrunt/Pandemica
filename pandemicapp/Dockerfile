FROM openjdk:17-jdk-alpine

RUN addgroup --system spring ; \
    adduser --system spring --ingroup spring

USER spring:spring

EXPOSE 8082

ADD target/pandemicapp-0.0.1-SNAPSHOT.jar pandemicapp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "pandemicapp-0.0.1-SNAPSHOT.jar"]