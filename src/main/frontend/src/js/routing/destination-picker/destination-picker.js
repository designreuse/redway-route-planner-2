(function(angular, $) {

    function ctrl($scope) {

        let  route = {};

        angular.extend($scope, {
            findRoute,
            toggleForm,
            route,
            minimised: false
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
                route.error = "Oops, we couldn't find that place. Please try again."
            }
        }

        function toggleForm() {
            $scope.minimised = !$scope.minimised;
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
