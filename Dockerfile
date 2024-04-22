FROM openjdk:17-alpine
VOLUME /tmp
COPY target/file-storage-service-0.0.1-SNAPSHOT.jar file-storage-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/file-storage-service-0.0.1-SNAPSHOT.jar"]