angular.module('demo', [])
    .controller('Hello', function($scope, $http)
    {
        $http.get('/sgdToCn/getAll').
        then(function(response) {

            $scope.greeting = response.data;
        });
    });
