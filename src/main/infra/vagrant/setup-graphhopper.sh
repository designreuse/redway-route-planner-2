#!/usr/bin/env bash

sudo apt-get install unzip
cd ~/
rm -rf graphhopper
mkdir graphhopper
chmod -R 755 graphhopper
cd graphhopper
wget https://oss.sonatype.org/content/groups/public/com/graphhopper/graphhopper-web/0.8.0/graphhopper-web-0.8.0-bin.zip
sudo cp -r /vagrant/osm ./
unzip graphhopper-web-0.8.0-bin.zip
nohup java -jar *.jar jetty.resourcebase=webapp config=osm/gh-config.properties datareader.file=osm/buckinghamshire-latest.osm.pbf &
