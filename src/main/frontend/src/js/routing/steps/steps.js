(function(angular) {

    function steps() {
        return {
            restrict: "E",
            templateUrl: "js/routing/steps/steps.html",
            replace: true,
            scope: {
                route: "="
            }
        }
    }

    angular.module("routing.steps",[

    ])
    .directive("steps", steps);

})(window.angular);