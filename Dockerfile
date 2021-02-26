FROM adoptopenjdk/openjdk15:alpine-jre
ARG JAR_FILE=lib/service.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]