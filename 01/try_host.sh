#!/bin/bash
#
# A script that checks if a host is up or not.
#
# Jakob Ledig, Florian Nehmer, 5.4.2016
# Praktikum Betriebssysteme SS2017, Gruppe 4
#
#--------------------------------------main------------------------------------#
#

if [ $1 == "-h" ]; then
    echo "
    This cli-tool is an interface to the ping program.
    Parameter list:
    -h: Display this help message
    -s \$number \$host: ping host at a certain interval

    Example usage:
    try_host.sh -s 5 www.google.de"

  elif [ $1 == "-s" ]; then
      echo "Pinging in intervals..."
      interval=$2
      host=$3
      while [ true ]; do
        result=$(ping -c 1 "$host")
        if [ $? == 0 ]; then
          echo "$host OK"
        else
          echo "$host FAILED"
        fi
        echo "Sleeping for $interval seconds..."
        sleep $interval
        echo ""
      done
fi
