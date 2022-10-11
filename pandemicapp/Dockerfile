FROM openjdk:17-jdk-alpine

RUN addgroup --system spring ; \
    adduser --system spring --ingroup spring

RUN mkdir /usr/local/tomcat ; \
    chown -R spring:spring /usr/local/tomcat ; \
    chmod 744 /usr/local/tomcat

USER spring:spring

EXPOSE 8082

ADD target/pandemicapp-0.0.1-SNAPSHOT.jar pandemicapp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "pandemicapp-0.0.1-SNAPSHOT.jar"]