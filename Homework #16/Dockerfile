FROM maven:3.8.1-openjdk-11 AS MAVEN_STEP

ENV MVN=/usr/bin/mvn

ENV PROJECT_DIR=/tmp/library

RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

ADD ./pom.xml $PROJECT_DIR
RUN $MVN dependency:resolve

ADD ./src/ $PROJECT_DIR/src
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim

ENV APP_DIR=/opt/library

RUN mkdir -p $APP_DIR
WORKDIR $APP_DIR

COPY --from=MAVEN_STEP /tmp/library/target/Library-1.0-SNAPSHOT.jar $APP_DIR/

EXPOSE 8080

CMD ["java", "-jar", "/opt/library/Library-1.0-SNAPSHOT.jar"]