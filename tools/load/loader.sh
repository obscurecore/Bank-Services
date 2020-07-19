#!/bin/bash

cd `dirname $0`

SCRIPTPATH=`dirname $0`
cd $SCRIPTPATH

threads=8
light_connections=10
light_duration=5s
heavy_connections=1000
heavy_duration=19s

COMMAND="${1: }"

case "$COMMAND" in
  pre_load)
  #L - True if FILE exists and is a symbolic link.
  #Curl command transfers data to or from a network server
  #o optionname(symbolic link)
    curl -L 'https://github.com/obscurecore/Bank-Services/releases/download/v0.0.2/benchmark-0.0.2.jar' -o ./loader.jar
    java -jar ./loader.jar
    ;;
  light_load)
  echo $2
    java -Dio.netty.leakDetection.level=disabled -jar ./loader.jar --url=$2 \
     -t$threads -c$light_connections --duration=$light_duration -H userId:$3 -H longitude:$4 -H latitude:$5
    ;;
  heavy_load)
    java -Dio.netty.leakDetection.level=disabled -jar ./loader.jar --url=$2 \
     -t$threads -c$heavy_connections --duration=$heavy_duration -H userId:$3 -H longitude:$4 -H latitude:$5
    ;;
  load)
  echo $7, $2
    java -Dio.netty.leakDetection.level=disabled -jar ./loader.jar --url=$2 \
     -t$threads -c$6 --duration=$7 -H userId:$3 -H longitude:$4 -H latitude:$5 ${@:8}
    ;;
  *)
    echo "use command [pre_load | load | light_load | heavy_load]"
  ;;
esac