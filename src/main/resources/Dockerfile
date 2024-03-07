FROM openjdk:17

WORKDIR /app

COPY gRPC_client-1.0-SNAPSHOT.jar.jar /app/gRPC_client-1.0-SNAPSHOT.jar.jar

EXPOSE 8082 6565

ENTRYPOINT ["java", "-jar", "/app/gRPC_client-1.0-SNAPSHOT.jar.jar"]