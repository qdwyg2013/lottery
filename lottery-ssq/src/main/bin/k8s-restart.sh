#!/bin/bash
cd `dirname $0`
./stop.sh
sleep 1
./k8s-start.sh
