FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD 
RUN mkdir -p /usr/src/code
COPY .m2 /usr/src/code/.m2
COPY src /usr/src/code/src
COPY pom.xml /usr/src/code
ENV MAVEN_CLI_OPTS='-s /usr/src/code/.m2/settings.xml --batch-mode'
RUN mvn $MAVEN_CLI_OPTS -f /usr/src/code/pom.xml clean verify -DskipTests
FROM adoptopenjdk/openjdk11:jdk-11.0.7_10-alpine-slim
ENV TZ=Asia/Almaty
WORKDIR /apps
EXPOSE 8080
COPY --from=MAVEN_BUILD /usr/src/code/target/task-tracker-0.0.1.jar /apps/
RUN addgroup -S akvalon && adduser -S akvalon -G akvalon
USER akvalon
CMD ["java","-jar","-Dspring.jmx.enabled=false","task-tracker-0.0.1.jar"]
