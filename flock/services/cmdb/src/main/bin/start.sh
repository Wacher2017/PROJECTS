#! /bin/sh

APP_HOME=$(cd `dirname $0`; cd ..; pwd)
LOG_OPTIONS=-Dlogging.config=config/logback.xml
DEBUG_OPTIONS=-agentlib:jdwp=transport=dt_socket,server=y,address=${server.debug.port},suspend=n
JVM_OPTIONS="-Xms256m -Xmx1024m -XX:MaxNewSize=64m"
BOOTSTRAP_JAR=lib/${project.build.finalName}.jar

cd $APP_HOME
nohup java -Dspring.profiles.active=${spring.profiles.active} \
$LOG_OPTIONS \
$DEBUG_OPTIONS \
$JVM_OPTIONS \
-jar $BOOTSTRAP_JAR > /dev/null 2>&1 &
