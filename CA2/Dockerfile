FROM tomcat:latest

LABEL maintainer="Nidhi Gupta"

ADD ./target/com.ut.mizdooni.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]