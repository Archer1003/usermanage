FROM  tomcat:latest
WORKDIR /usr/local/tomcat/webapps/
RUN rm -rf /usr/local/tomcat/webapps/*
copy  target/usermanage.war  /usr/local/tomcat/webapps
RUN unzip /usr/local/tomcat/webapps/usermanage.war -d /usr/local/tomcat/webapps/usermanage
RUN rm /usr/local/tomcat/webapps/usermanage.war
