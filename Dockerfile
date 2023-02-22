FROM openjdk:17
EXPOSE 8080
COPY target/meetek-*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]