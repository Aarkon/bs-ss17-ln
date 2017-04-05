#!/bin/bash

function pinghost() {
  result=$(ping -c 1 "$1")
  if [[ -n $result ]]; then
    echo "$1 OK"
  else
    echo "$1 FAILED"
  fi
  echo "$result"
}

#--------------------------------------main------------------------------------#
#

if [ $1 == "-h" ]; then
    echo "Parameter list:"
    echo "-h: Display this help message"
    echo "-s \$number \$host: ping a host with a given name at a certain interval"
  elif [ $1 == "-s" ]; then
      echo "Pinging in intervals..."
      host=$2
      interval=$3
      while [[ true ]]; do
        pinghost $host
        sleep $interval
      done
fi
