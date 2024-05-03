# Usar una imagen base con Java 17
FROM amazoncorretto:17

# Información del mantenedor
LABEL maintainer="your_email@example.com"

# Puerto que expone el contenedor
EXPOSE 8081

# Copiar el archivo jar desde el target de Maven al contenedor
ARG JAR_FILE=target/discover-backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]
