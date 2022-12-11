FROM openjdk:11

WORKDIR /app

COPY target/information-thematic-backend.jar information-thematic-backend.jar

ENTRYPOINT ["java", "-jar", "information-thematic-backend.jar"]

EXPOSE 5000