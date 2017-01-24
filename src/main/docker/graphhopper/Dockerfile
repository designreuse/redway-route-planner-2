FROM java:8

EXPOSE 8989

WORKDIR /graphhopper

RUN wget https://oss.sonatype.org/content/groups/public/com/graphhopper/graphhopper-web/0.8.0/graphhopper-web-0.8.0-bin.zip
RUN apt-get install unzip
RUN unzip /graphhopper/graphhopper-web-0.8.0-bin.zip
COPY ./osm /graphhopper/osm

CMD java -jar graphhopper-web-0.8.0-with-dep.jar jetty.resourcebase=webapp config=osm/gh-config.properties datareader.file=osm/buckinghamshire-latest.osm.pbf