(function(angular) {

    function ctrl($scope) {

        let  route = {};

        angular.extend($scope, {
            findRoute,
            route
        });

        function findRoute() {
            $scope.search({text: route.start}, {text: route.end}, {});
        }
    }

    ctrl.$inject = ["$scope"];

    function destinationPicker() {
        return {
            restrict: "E",
            templateUrl: "js/routing/destination-picker/destination-picker.html",
            replace: true,
            scope: {
                search: "="
            },
            controller: ctrl
        }
    }

    angular.module("routing.destination-picker",[

    ])
    .directive("destinationPicker", destinationPicker);

})(window.angular);