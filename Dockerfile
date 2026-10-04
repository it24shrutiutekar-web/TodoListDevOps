FROM tomcat:10.1-jdk17-temurin

COPY target/todo-app.war /usr/local/tomcat/webapps/todo-app.war

EXPOSE 8080

CMD ["catalina.sh", "run"]