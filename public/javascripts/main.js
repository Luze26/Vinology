angular.module("vinology", []);

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

angular.module("vinology").controller("producteursCtrl", ['$scope', '$http', function($scope, $http) {

    $scope.producteurs = [];

    function init() {
        $scope.$parent.predicate = "name";

        $http.get("/api/producteurs")
            .success(function(data) {
                $scope.producteurs = data;
            });
    }

    init();
}]);

angular.module("vinology").controller("queryCtrl", ['$scope', '$http', function($scope, $http) {

    $scope.query = "";

    $scope.error = null;

    $scope.result = null;

    $scope.loading = false;

    $scope.display = "parsed";

    $scope.examples = [{title: "Mon altesse", query: "prefix vin: <http://www.vin.com/ontologies/vin.owl#>\nselect ?vin\nwhere { ?vin vin:hasCepage vin:CepAltesse }"},
        {title: "J'aime le vin blanc <3", query: "prefix vin: <http://www.vin.com/ontologies/vin.owl#>\nprefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
            + "select ?type\nwhere { ?type rdfs:subClassOf vin:BlancVin }"},
        {title: "Les producteurs de l'altesse", query: "prefix vin: <http://www.vin.com/ontologies/vin.owl#>\nselect ?producteur ?nom\nwhere {\n"
            + "?vin vin:hasCepage vin:CepAltesse .\n?vin vin:hasProducteur ?producteur .\n?producteur vin:hasName ?nom .}"},
        {title: "Montre moi tout !!!", query: "select distinct ?x \nwhere { ?x ?y ?z }"}];

    $scope.showExample = function(example) {
        $scope.query = example.query;
    };

    $scope.getLinkForBinding = function(binding) {
        if(binding.type === "uri" && binding.value.indexOf("http://www.vin.com/ontologies/vin.owl#") != -1) {
            return "/resource?uri=" + encodeURIComponent(binding.value);
        }
        return null;
    };

    $scope.stripUri = function(uri) {
        return uri.slice(38);
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
                            $scope.error = "Une erreur est survenue en traitant votre requête !\n Vérifiez votre syntaxe, vous pouvez faire mieux ;)."
                            break;
                        default:
                            $scope.error = "Une erreur est survenue !";
                    }
                });
        }
    };
}]);