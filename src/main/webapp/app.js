(function() {

    var app = angular.module('movies-game', []);

    app.controller('monController', ['$scope', '$http',
        function($scope,$http) {
            $scope.topscores = [];

            var promise = $http.get('/_ah/api/scoresendpoint/v1/top10listscores');
            promise.success(function(data) {
                $scope.topscores = data;
            });

        }

    ]);


})();