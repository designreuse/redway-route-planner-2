(function(angular) {

    function ctrl($scope, routingBackend, leafletData) {
        let map = {
            center: {},
            defaults: {
                scrollWheelZoom: false
            },
            paths: {},
            bounds: {},
            markers: {}
        },
        route = {},
        leafletMap;

        angular.extend($scope, {
            search,
            locate,
            map,
            route
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
            return routingBackend.search(start, end).then(searchSuccess);
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
            leafletMap.on('locationfound', function (e) {
                angular.extend($scope.map, {
                    markers: {
                        me: {
                            lat: e.latlng.lat,
                            lng: e.latlng.lng
                        }
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