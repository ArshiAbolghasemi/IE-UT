FROM tomcat:latest

ENV WAR_FILE=ROOT.war
ENV TOMCAT_PATH=/usr/local/tomcat/
ENV WEBAPSS_PATH=${TOMCAT_PATH}/webapps/

ADD ./target/${WAR_FILE} ${WEBAPSS_PATH}

RUN chmod -R  755 ${WEBAPSS_PATH}${WAR_FILE}
RUN mv ${TOMCAT_PATH}webapps.dist/* ${WEBAPSS_PATH}
RUN rm -rf ${WEBAPSS_PATH}ROOT

EXPOSE 8080

CMD ["catalina.sh", "run"]
