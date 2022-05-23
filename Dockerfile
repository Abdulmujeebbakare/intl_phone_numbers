FROM adoptopenjdk/openjdk11
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY target/*.jar app.jar
COPY sample.db sample.db
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]