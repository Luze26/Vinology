angular.module("vinology").controller("resourcesListCtrl", ['$scope', function($scope) {

    $scope.predicate = null;

    $scope.reverse = false;

    $scope.changeOrder = function(predicate) {
        if($scope.predicate === predicate) {
            $scope.reverse = !$scope.reverse;
        }
        else {
            $scope.predicate = predicate;
            $scope.reverse = false;
        }
    };
}]);

angular.module("vinology").controller("vinsCtrl", ['$scope', '$http', function($scope, $http) {

    $scope.vins = [];

    function init() {
        $scope.$parent.predicate = "name";

        $http.get("/api/vins")
            .success(function(data) {
                $scope.vins = data;
            });
    }

    init();
}]);

angular.module("vinology").controller("cepagesCtrl", ['$scope', '$http', function($scope, $http) {

    $scope.cepages = [];

    function init() {
        $scope.$parent.predicate = "name";

        $http.get("/api/cepages")
            .success(function(data) {
                $scope.cepages = data;
            });
    }

    init();
}]);