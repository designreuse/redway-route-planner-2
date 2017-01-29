(function(angular, $) {

    function ctrl($scope) {
        angular.extend($scope, {
            showSteps,
            toggleSteps,
            stepsHidden: true
        });

        function showSteps() {
            return $scope.route.steps && $scope.route.steps.length;
        }

        function toggleSteps() {
            $scope.stepsHidden = !$scope.stepsHidden;
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

})(window.angular, window.$);