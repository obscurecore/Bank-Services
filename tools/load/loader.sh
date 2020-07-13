#!/bin/bash

cd `dirname $0`


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
    curl -L 'https://dl.bintray.com/corecorp/rx-benchamrk/rx-benchmark-0.0.1.jar?expiry=1588238365650&id=D8PRKPNN%2BKi6Ya6SUNRrxGooLradub6RtXxEUUueDs%2FsMKw9C51m1M7G%2BsIw2YCBmCd24LG%2F%2FQ%2B098bmFxm06w%3D%3D&signature=LAilBmsekM84qmVBWAr6Rp%2FTb8%2FjZcc4ho1%2Fp8nkOjXoY8Wi2iCQnq7zVQnNNPn0owPb5%2Bp2gIeBsb5riwiqJg%3D%3D' -o ./rx-loader.jar
    java -jar ./rx-loader.jar
    ;;
  light_load)
  echo $2
    java -Dio.netty.leakDetection.level=disabled -jar ./rx-loader.jar --url=$2 \
     -t$threads -c$light_connections --duration=$light_duration -H userId:$3 -H longitude:$4 -H latitude:$5
    ;;
  heavy_load)
    java -Dio.netty.leakDetection.level=disabled -jar ./rx-loader.jar --url=$2 \
     -t$threads -c$heavy_connections --duration=$heavy_duration -H userId:$3 -H longitude:$4 -H latitude:$5
    ;;
  load)
    java -Dio.netty.leakDetection.level=disabled -jar ./rx-loader.jar --url=$2 \
     -t$threads -c$6 --duration=$7 -H userId:$3 -H longitude:$4 -H latitude:$5 ${@:8}
    ;;
  *)
    echo "use command [pre_load | load | light_load | heavy_load]"
  ;;
esac