@ECHO OFF

SET APP_HOME=%~dp0..

SET BOOTSTRAP_JAR=lib/cmdb-1.0-SNAPSHOT.jar
SET JVM_OPTIONS=-Xms256m -Xmx1024m -XX:MaxNewSize=64m
SET DEBUG_OPTIONS=-agentlib:jdwp=transport=dt_socket,server=y,address=9999,suspend=n
SET LOG_OPTIONS=-Dlogging.config=config/logback.xml

@ECHO ON
cd %APP_HOME%
java -Dspring.profiles.active=product ^
%LOG_OPTIONS% ^
%DEBUG_OPTIONS% ^
%JVM_OPTIONS% ^
-jar %BOOTSTRAP_JAR%