(function(angular) {

    function ctrl($scope, leafletData) {
        let map = {
            center: {},
            defaults: {
                scrollWheelZoom: false
            },
            paths: {},
            bounds: {},
            markers: {}
        };
        angular.extend($scope, {
            search,
            map,
        });


        function search(start, end, options) {
            console.log(start);
        }

        leafletData.getMap('map').then(function(map) {
            map.locate({
                setView: true,
                maxZoom: 16,
                watch: true,
                enableHighAccuracy: true
            });
            map.on('locationfound', function (e) {
                angular.extend($scope, {
                    markers: {
                        me: {
                            lat: e.latlng.lat,
                            lng: e.latlng.lng
                        }
                    }
                });
            });
        });
    }

    ctrl.$inject = ["$scope", "leafletData"];

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
        "leaflet-directive"
    ])
    .directive("routing", routing);

})(window.angular);