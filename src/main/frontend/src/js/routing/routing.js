(function(angular) {

    function ctrl($scope, routingBackend, leafletData) {
        let mk = {
            lat: 52.0330629,
            lng: -0.75908660,
            zoom: 11
        }, map = {
            center: mk,
            defaults: {
                scrollWheelZoom: false
            },
            paths: {},
            bounds: {},
            markers: {},
            tiles: {
                url: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
                options: {
                    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                }
            }
        },
        route = {},
        leafletMap,
        places = [],
        showPlaces = true;

        angular.extend($scope, {
            search,
            locate,
            map,
            route,
            togglePlaces
        });

        function searchSuccess(response) {
            angular.extend(route, response.data.route);
            map.paths.path = {
                type: "polyline",
                latlngs: route.points,
                color: "red",
                weight: 3
            };
            map.bounds = {
                southWest: {
                    lat: route.boundingBox.minLat,
                    lng: route.boundingBox.minLng
                },
                northEast: {
                    lat: route.boundingBox.maxLat,
                    lng: route.boundingBox.maxLng
                }
            };
        }

        function search(start, end) {
            return routingBackend.search(start, end)
                .then(searchSuccess);
        }

        function togglePlaces() {
            showPlaces = !showPlaces;
            if (showPlaces) {
                addPlacesOfInterest();
            } else {
                removePlacesOfInterest();
            }
        }

        function getPlaces() {
            return routingBackend.getPlaces();
        }

        function savePlaces(response) {
            places = response.data;
        }

        function addPlacesOfInterest() {
            const markers = map.markers;
            places.forEach((place, i) => {
                markers['place' + i] = {
                    lat: place.point.lat,
                    lng: place.point.lng,
                    message: place.name,
                    focus: false,
                    icon: {
                        iconUrl: "images/arts_icon.png"
                    }
                }
            });
            angular.extend(map.markers, markers);
        }

        function removePlacesOfInterest() {
            const markers = map.markers;
            places.forEach((place, i) => {
                delete markers['place' + i];
            });
            angular.extend(map.markers, markers);

        }

        function assignMap(map) {
            leafletMap = map;
            return map;
        }

        function locate() {
            leafletMap.stopLocate();
            leafletMap.locate({
                setView: true,
                maxZoom: 16,
                enableHighAccuracy: true,
                watch: true
            });
        }

        function setupLocationFoundEvent() {
            leafletMap.on('locationfound', function(e) {
                angular.extend($scope.map.markers, {
                    me: {
                        lat: e.latlng.lat,
                        lng: e.latlng.lng
                    }
                });
            });
        }

        function setupMapDragEvent() {
            leafletMap.on("drag", function(e) {
                leafletMap.stopLocate();
                leafletMap.locate({
                    setView: false,
                    maxZoom: 16,
                    enableHighAccuracy: true,
                    watch: false
                });
            });
        }

        leafletData.getMap('map')
            .then(assignMap)
            .then(locate)
            .then(setupLocationFoundEvent)
            .then(setupMapDragEvent);

        getPlaces()
            .then(savePlaces)
            .then(addPlacesOfInterest);
    }

    ctrl.$inject = ["$scope", "routingBackend", "leafletData"];

    function routing() {
        return {
            restrict: "E",
            templateUrl: "js/routing/routing.html",
            replace: true,
            scope: {},
            controller: ctrl
        }
    }


    angular.module("routing", [
        "routing-backend",
        "routing.destination-picker",
        "routing.steps",
        "leaflet-directive"
    ])
    .directive("routing", routing);

})(window.angular);