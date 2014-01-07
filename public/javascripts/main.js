angular.module("vinology", []);

angular.module("vinology").controller("queryCtrl", ['$scope', '$http', '$timeout', function($scope, $http, $timeout) {

    $scope.query = "";

    $scope.error = null;

    $scope.result = null;

    $scope.predicate = null;

    $scope.reverse = false;

    $scope.loading = false;

    $scope.examples = [{title: "Gamay", query: "prefix vin: <http://www.vin.com/ontologies/vin.owl#>\nselect ?vin\nwhere { ?vin vin:hasCepage vin:GamayVin }"},
        {title: "I love white wine <3", query: "prefix vin: <http://www.vin.com/ontologies/vin.owl#>\nprefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
            + "select ?type\nwhere { ?type rdfs:subClassOf vin:BlancVin }"},
        {title: "Get me everything !!!", query: "prefix vin: <http://www.vin.com/ontologies/vin.owl#>\nselect ?x ?y ?z\nwhere { ?x ?y ?z }"}];

    $scope.showExample = function(example) {
        $scope.query = example.query;
    };

    $scope.changeOrder = function(predicate) {
        if($scope.predicate === predicate) {
            $scope.reverse = !$scope.reverse;
        }
        else {
            $scope.predicate = predicate;
            $scope.reverse = false;
        }
    };

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
