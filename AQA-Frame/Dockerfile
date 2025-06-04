
FROM openjdk:17-jdk-slim-buster 
LABEL maintainer="rohit.d@softwaremathematics.com"
COPY ./target/aqa-framework-1.0-SNAPSHOT.jar /home/aqa-framework-1.0-SNAPSHOT.jar
# COPY src/main/resources/serviceAccountKey.json /opt/
# RUN printf 'rules:\n- pattern: ".*"' > config.yaml
# RUN apt-get update
# RUN apt-get -y install wget
# RUN wget https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.17.2/jmx_prometheus_javaagent-0.17.2.jar
# RUN apt-get update && apt-get install -y wget && \
#     wget -O jmx_prometheus_javaagent.jar https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.17.2/jmx_prometheus_javaagent-0.17.2.jar && \
#     apt-get remove -y wget && apt-get clean
    
CMD ["java", "-jar","/home/aqa-framework-1.0-SNAPSHOT.jar"]
