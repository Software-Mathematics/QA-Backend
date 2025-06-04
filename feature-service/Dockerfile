FROM openjdk:11-jre-slim-buster
LABEL maintainer="rohit.d@softwaremathematics.com"
COPY ./target/feature-service-0.0.1-SNAPSHOT.jar /home/feature-service-0.0.1-SNAPSHOT.jar
# RUN printf 'rules:\n- pattern: ".*"' > config.yaml
# RUN apt-get update
# RUN apt-get -y install wget
# RUN wget https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.17.2/jmx_prometheus_javaagent-0.17.2.jar
CMD ["java", "-jar","/home/feature-service-0.0.1-SNAPSHOT.jar"]
# CMD ["java", "-jar","/home/request-service-0.0.1-SNAPSHOT.jar"]
