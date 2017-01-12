(function(angular, $) {

    function ctrl($scope) {
        angular.extend($scope, {
            showSteps,
            viewSteps
        });

        function showSteps() {
            return $scope.route.steps && $scope.route.steps.length;
        }

        function viewSteps() {
            $( ".steps-box" ).slideToggle( "slow", function() {
                // Animation complete.
            });
            $( ".steps-box-icon" ).toggleClass( "rotated", 3000, "easeOutSine" );
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