FROM openjdk:17-alpine@sha256:a996cdcc040704ec6badaf5fecf1e144c096e00231a29188596c784bcf858d05
MAINTAINER bosonit.com
RUN addgroup -S bosonit && adduser -S bosonit -G bosonit
USER bosonit:bosonit
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring-boot-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-1.0.0.jar"]
