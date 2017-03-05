(function(angular) {

    function routingBackend($http) {

        const server = window.location.origin.split(":")[0] === "file" ? "http://rrp2.mkhackathon.org" : "";

        function search(start, end) {
            return $http.post(server + "/routing", {
                start: start,
                end: end
            });
        }

        function getPlaces() {
            return $http.get(server + "/places");
        }

        return {
            search,
            getPlaces
        };
    }

    routingBackend.$inject = ["$http"];

    angular.module("routing-backend", [])
        .factory("routingBackend", routingBackend);

})(window.angular);