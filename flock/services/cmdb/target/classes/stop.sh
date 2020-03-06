#! /bin/sh

cmdb_pid=`ps -ef|grep java|grep cmdb|awk '{print $2}'`
[ -n "$cmdb_pid" ] && kill -9 $cmdb_pid