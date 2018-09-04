angular.module("demo", [])
    .controller("exchangeRate", function($scope, $http)
    {
        $http.get('http://localhost:8080/sgdToCn/getAll').
        then(function(response) {
            $scope.moneyInfo = response.data;
        },function(err) {
            console.error("Error occured: ", err);
        });
    });
