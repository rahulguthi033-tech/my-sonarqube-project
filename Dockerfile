FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/spotify-app-1.0.0.jar app.jar

EXPOSE 5555

ENTRYPOINT ["java","-jar","app.jar"]
