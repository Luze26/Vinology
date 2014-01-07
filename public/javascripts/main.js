angular.module("vinology", []);

angular.module("vinology").controller("queryCtrl", ['$scope', '$http', function($scope, $http) {

    $scope.query = "";

    $scope.error = null;

    $scope.result = null;

    $scope.loading = false;

    $scope.executeQuery = function() {
        if(!$scope.loading) {
            $scope.result = null;
            $scope.error = null;
            $scope.loading = true;

            $http.post("/query/execute", $scope.query)
                .success(function(data) {
                    $scope.result = data;
                    $scope.loading = false;
                })
                .error(function(data) {
                    $scope.loading = false;
                    switch(data.error) {
                        case "Parse Error":
                            $scope.error = "An error occurred while parsing your query ! Check your syntax, you can do better ;)."
                            break;
                        default:
                            $scope.error = "An error occurred !";
                    }
                });
        }
    };
}]);
