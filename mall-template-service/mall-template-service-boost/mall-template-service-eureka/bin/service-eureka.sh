#!/usr/bin/env bash
# exit if meet any error
set -e
bin=`dirname "$0"`
APP_HOME=`cd "$bin"/..; pwd`
PG_NAME=`basename "$0"`
cd $APP_HOME

#print out env properties
echo \$SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

JAVA_OPTS="${JAVA_OPTS} -Xms64m -Xmx64m"
if [ -z "${SPRING_PROFILES_ACTIVE}" ];then
  echo "active is not set"
  exit 1
fi

#source env
#CONF_ENV_FILE="$APP_HOME/bin/config-${SPRING_PROFILES_ACTIVE}.env"
#source "$CONF_ENV_FILE"

for jar in "$APP_HOME"/*.jar
do
   PRG_NAME=$jar
   echo \$PRG_NAME=$jar
done

function start(){
   RUNNING=`ps -ef|grep $PRG_NAME|grep -v grep|awk '{print $2}'`
   if [ -n "$RUNNING" ]; then
      echo "jar running! $RUNNING"
   else
   	  echo "nohup $JAVA_HOME/bin/java $JAVA_OPTS -Dfile.encoding=utf-8 -jar $PRG_NAME --spring.config.location=$APP_HOME/config/ --logging.config=$APP_HOME/config/log4j2-$SPRING_PROFILES_ACTIVE.xml 2>&1 &"
      exec nohup $JAVA_HOME/bin/java $JAVA_OPTS -Dfile.encoding=utf-8 -jar $PRG_NAME --spring.config.location=$APP_HOME/config/ --logging.config=$APP_HOME/config/log4j2-$SPRING_PROFILES_ACTIVE.xml 2>&1 &
   	if [ $? -eq 0 ]; then
     		echo "$PRG_NAME start success"
   	else
     		echo "$PRG_NAME start fail"
     		exit 1
   	fi
   fi
}

function stop(){
  echo " stop $PRG_NAME..."
  pkill -f "$PRG_NAME"
  echo "$PRG_NAME is stopped!"
}

function status(){
  processid=`pgrep -f "$PRG_NAME"`
  if [ $processid ]; then
    echo "$PRG_NAME is running as process $process"
  else
    echo "$PRG_NAME is not running. "
  fi
}

case $1 in 
   --help|-help|-h)
   print_usage
   exit
   ;;
   start)
      start
   ;;
   stop)
      stop
      ;;
   status)
      status
      ;;
   restart)
      stop
      start
      ;;
   *)
esac
exit $?;	
