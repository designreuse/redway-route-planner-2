(function(angular, $) {

    function ctrl($scope) {

        let  route = {};

        angular.extend($scope, {
            findRoute,
            toggleForm,
            route
        });

        function findRoute() {
            route.error = "";
            $scope.search({text: route.start}, {text: route.end}, {})
                .then(toggleForm)
                .catch(handleError);
        }

        function handleError(response) {
            if (response.status === 404) {
                route.error = response.data.message;
            } else {
                route.error = "Sorry, an error occurred. Please find again."
            }
        }

        function toggleForm() {
            $( ".input-box" ).toggleClass( "minimised", 1000, "easeOutSine" );
            $( ".minimiser" ).toggleClass( "minimised-icon", 1000, "easeOutSine" );
            $( ".route-input" ).toggleClass( "hide", 500, "easeOutSine" );
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

})(window.angular, window.$);