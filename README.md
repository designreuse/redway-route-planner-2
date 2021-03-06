[![Build Status](https://travis-ci.org/mkhackathon/redway-route-planner-2.svg?branch=master)](https://travis-ci.org/mkhackathon/redway-route-planner-2)
# MK Hackathon Redway Route Planner

To run the development environment:

* Install [Vagrant](https://www.vagrantup.com) and Virtual Box
* Navigate to the root of the repo
* Run `vagrant up`
* ssh into the VM `vagrant ssh`
* `cd /vagrant`
* `/opt/maven/bin/mvn spring-boot:run`
* Navigate to `https://localhost:8080`

The routing is done using Graphhopper. The Graphhopper server is started automatically when the VM starts.

Starting the app with Maven will run the application using the Spring Boot Maven Plugin which will monitor the classpath for changes and reload the app.

To make changes to the front end code and see them reflected in the running app you'll need to run `npm watch`. This can be done from the host machine if you have Node.js installed. If not, you can ssh into the running Vagrant VM and run the following commands in there as Node.js is installed. Just first cd into `/vagrant`. 

* `cd src/main/frontend`
* `npm run watch`

This will watch all files in the `src/main/frontend/src` directory and copy them to the `src/main/resources/static` directory when changed where they will be served up by the web server (actually it'll copy them into `target` as well so you don't have to rebuild that folder if you're running in IntellJ).

### Geocoding

There are two implementations of the geocoding API to turn text into coordinates. One provided for OSM and one for Google.
To use the Google implementation you'll need an API key. Once you have it set it as the environment variable `GOOGLE_API_KEY`.


