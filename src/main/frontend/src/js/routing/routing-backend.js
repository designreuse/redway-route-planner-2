(function(angular) {

    function routingBackend($http) {

        function search(start, end) {
            return $http.post("/routing", {
                start: start,
                end: end
            });
        }

        return {
            search
        };
    }

    routingBackend.$inject = ["$http"];

    angular.module("routing-backend", [])
        .factory("routingBackend", routingBackend);

})(window.angular);