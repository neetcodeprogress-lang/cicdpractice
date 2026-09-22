# The deliberately naive build. This is the "before" half of the demo.
#
# Two things are wrong with it on purpose:
#   1. COPY . . puts the source in BEFORE any dependency is resolved, so
#      editing one Java file invalidates this layer and every layer below it.
#      That is the BUILD TIME problem.
#   2. It is a single stage, so the shipped image is this JDK-plus-Maven image,
#      carrying a compiler and a full ~/.m2 cache that a running app never uses.
#      That is the IMAGE SIZE problem.

FROM maven:3.9-eclipse-temurin-17
WORKDIR /app
COPY . .
RUN mvn package
EXPOSE 8080
CMD ["java", "-jar", "target/asdf-1.0-SNAPSHOT.jar"]


# Below is the correct version

## Stage 1: the builder. Has Maven and a JDK. Nothing from this stage reaches
## the final image unless you copy it across explicitly.
#FROM maven:3.9-eclipse-temurin-17 AS build
#WORKDIR /app
#COPY pom.xml .
#RUN mvn -B dependency:go-offline
#COPY src ./src
#RUN mvn -B package
#
## Stage 2: the runtime. A JRE only. No compiler, no ~/.m2 cache.
#FROM eclipse-temurin:17-jre
#WORKDIR /app
## --from=build reaches into the stage above and takes only the finished jar.
#COPY --from=build /app/target/asdf-1.0-SNAPSHOT.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]