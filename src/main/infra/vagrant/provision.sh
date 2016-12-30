#!/usr/bin/env bash
sudo apt-get install -y software-properties-common python-software-properties
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
sudo add-apt-repository ppa:webupd8team/java -y
sudo apt-get update
sudo apt-get install oracle-java8-installer
echo "Setting environment variables for Java 8.."
sudo apt-get install -y oracle-java8-set-default

sudo apt-get install unzip
sudo apt-get install maven



rm -rf graphhopper
mkdir graphhopper
chmod -R 755 graphhopper
cd graphhopper
wget https://oss.sonatype.org/content/groups/public/com/graphhopper/graphhopper-web/0.8.0/graphhopper-web-0.8.0-bin.zip
sudo cp -r /vagrant/osm ./
unzip graphhopper-web-0.8.0-bin.zip
nohup java -jar *.jar jetty.resourcebase=webapp config=config-example.properties datareader.file=osm/buckinghamshire-latest.osm.pbf &

cd /vagrant
mvn clean install

