FROM maven:3.6.3-openjdk-11-slim AS MAVEN_BUILD

COPY collections-libs /build/collections-libs/
COPY collections-ms /build/collections-ms/

WORKDIR /build/collections-libs/
RUN mvn clean install

WORKDIR /build/collections-ms/
RUN mvn clean install

FROM openjdk:11-jre-slim
WORKDIR /app
ARG JAR_FILE=collections-ms-1.0.0-SNAPSHOT.jar
COPY --from=MAVEN_BUILD /build/collections-ms/target/collections-ms-1.0.0-SNAPSHOT.jar /app/
ENTRYPOINT ["java","-jar", "collections-ms-1.0.0-SNAPSHOT.jar"]


