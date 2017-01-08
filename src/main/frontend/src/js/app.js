(function(angular) {

    function Config() {

    }

    function app() {
        return {
            restrict: "E",
            templateUrl: "js/app.html",
            replace: true,
            scope: {}
        }
    }

    angular.module("app", [
    ])
    .config(Config)
    .directive("app", app);

}(window.angular));