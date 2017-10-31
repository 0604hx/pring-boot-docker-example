FROM openjdk:8-jdk-alpine

# We added a VOLUME pointing to "/tmp" because that is where a Spring Boot application
#	creates working directories for Tomcat by default.
# The effect is to create a temporary file on your host under "/var/lib/docker"
#	and link it to the container under "/tmp"
VOLUME /tmp

# set default work directory, e.g.: -v /you/path:/mnt
WORKDIR /mnt

# e.g.: -e "JAVA_OPTS=-Xms2g -Xmx2g -Xmn1g"
ENV JAVA_OPTS=""

# arguments for running jar, e.g.: java -jar app.jar --spring.application.name=TEST
ENV ARGS=""

# default app name is app.jar , you should custom name specially
# Note it without .jar ext.
ENV JAR="app"

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./$JAR.jar $ARGS