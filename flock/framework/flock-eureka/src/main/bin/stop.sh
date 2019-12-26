#! /bin/sh

eureka_pid=`ps -ef|grep java|grep eureka|awk '{print $2}'`
[ -n "$eureka_pid" ] && kill -9 $eureka_pid