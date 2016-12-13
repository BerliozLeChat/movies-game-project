(function() {

    var app = angular.module('movies-game', []);

    app.controller('monController', ['$scope', '$http',
        function($scope,$http) {
            $scope.monscore = -1;
            $scope.scorewait=true;
            var promise = $http.get('/account/GetScore');
            promise.success(function(data) {
                $scope.monscore = data[0]['score'];
                $scope.scorewait=false;
            });

        }

    ]);


})();