(function(angular) {

    function ctrl($scope) {
        angular.extend($scope, {
            showSteps
        });

        function showSteps() {
            return $scope.route.steps && $scope.route.steps.length;
        }
    }

    ctrl.$inject = ["$scope"];

    function steps() {
        return {
            restrict: "E",
            templateUrl: "js/routing/steps/steps.html",
            replace: true,
            scope: {
                route: "="
            },
            controller: ctrl
        }
    }

    angular.module("routing.steps",[

    ])
    .directive("steps", steps);

})(window.angular);