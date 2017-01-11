(function(angular) {

    function ctrl($scope, $http, leafletData) {
        let map = {
            center: {},
            defaults: {
                scrollWheelZoom: false
            },
            paths: {},
            bounds: {},
            markers: {}
        },
        route = {};

        angular.extend($scope, {
            search,
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

        function search(start, end, options) {
            $http.post("/routing", {
                start: start,
                end: end
            }).then(searchSuccess);
        }

        leafletData.getMap('map').then(function(map) {
            map.locate({
                setView: true,
                maxZoom: 16,
                enableHighAccuracy: true
            });
            map.on('locationfound', function (e) {
                angular.extend($scope.map, {
                    markers: {
                        me: {
                            lat: e.latlng.lat,
                            lng: e.latlng.lng
                        }
                    }
                });
                map.stopLocate();
            });
        });
    }

    ctrl.$inject = ["$scope", "$http", "leafletData"];

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
        "routing.destination-picker",
        "routing.steps",
        "leaflet-directive"
    ])
    .directive("routing", routing);

})(window.angular);