FROM openjdk

WORKDIR /app

COPY . .

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar"]