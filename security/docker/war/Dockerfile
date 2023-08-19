# Use RHEL as the base image
FROM registry.access.redhat.com/ubi8/ubi:8.1

MAINTAINER thanasissot1@gmail.com

RUN mkdir /opt/tomcat/

WORKDIR /opt/tomcat/

RUN curl -0 https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.9/bin/apache-tomcat-10.1.9.tar.gz
RUN tar xvfz apache*.tar.gz
RUN mv apache-tomcat-10.1.9/* /opt/tomcat/.
RUN yum -y install -y java-11-openjdk-headless
RUN java -version

RUN mkdir /webapps-javaee/
RUN ls -la

# Copy your Spring Boot WAR file into the container
COPY ../../target/dm-users.war /opt/tomcat/webapps-javaee/

# Expose the port for Tomcat
EXPOSE 8080

CMD ["/opt/tomcat/bin/catalina.sh", "run"]