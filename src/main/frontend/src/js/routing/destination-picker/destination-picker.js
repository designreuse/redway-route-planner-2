(function(angular) {

    function ctrl() {

    }

    function destinationPicker() {
        return {
            restrict: "E",
            templateUrl: "js/routing/destination-picker/destination-picker.html",
            replace: true,
            scope: {},
            controller: ctrl
        }
    }

    angular.module("routing.destination-picker",[

    ])
    .directive("destinationPicker", destinationPicker);

})(window.angular);