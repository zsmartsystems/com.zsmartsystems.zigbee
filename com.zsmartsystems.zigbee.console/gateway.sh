#!/bin/sh
java -jar target/zigbee-gateway-server-3.0.1-SNAPSHOT.jar /dev/cu.usbmodem1411 38400 11 4952 00000000000000000000000000000000 false 5000 secret
