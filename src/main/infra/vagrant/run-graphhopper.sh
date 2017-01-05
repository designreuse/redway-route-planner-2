#!/usr/bin/env bash

cd ~/graphhopper
nohup java -jar *.jar jetty.resourcebase=webapp config=osm/gh-config.properties datareader.file=osm/buckinghamshire-latest.osm.pbf &
