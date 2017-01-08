(function(angular) {

    function ctrl() {

    }

    function routing() {
        return {
            restrict: "E",
            templateUrl: "js/routing/routing.html",
            replace: false,
            scope: {},
            controller: ctrl
        }
    }

    angular.module("routing", [
        "routing.destination-picker"
    ])
    .directive("routing", routing);

})(window.angular);