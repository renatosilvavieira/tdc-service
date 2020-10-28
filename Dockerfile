FROM openjdk:8-alpine
VOLUME /tmp
ADD target/chamado-service-0.0.1-SNAPSHOT.jar app.jar
ENV K8S "true"
ENV JAVA_OPTS="-Dname=chamado-service -Dk8s=$K8S"
EXPOSE 8085
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar " ]