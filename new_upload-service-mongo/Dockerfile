FROM anapsix/alpine-java
LABEL maintainer="yuvraj.g@softwaremathematics.com"
COPY ./target/spring-boot-file-upload-mongo-0.0.1-SNAPSHOT.jar /home/spring-boot-file-upload-mongo-0.0.1-SNAPSHOT.jar
RUN printf 'rules:\n- pattern: ".*"' > config.yaml
RUN apk update && apk add wget
RUN mkdir /opt/recording
RUN wget https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.17.2/jmx_prometheus_javaagent-0.17.2.jar
CMD ["java", "-javaagent:./jmx_prometheus_javaagent-0.17.2.jar=5000:config.yaml", "-jar","/home/spring-boot-file-upload-mongo-0.0.1-SNAPSHOT.jar"]
