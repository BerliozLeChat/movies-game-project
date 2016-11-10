(function() {

    var app = angular.module('movies-game', []);

    app.controller('madonnee_dynamique', ['$scope','$window', '$http', '$interval',
        function($scope, $window,$http,$interval) {
            $scope.ajout_directors_attente=false;
            $scope.ajout_directors=false;
            $scope.ajout_directors_erreur=false;

            $scope.nb_directors_disponible_attente=false;
            $scope.nb_directors_disponible=false;
            $scope.nb_directors_disponible_erreur=false;

            $scope.nb_movies_disponible_attente=false;
            $scope.nb_movies_disponible=false;
            $scope.nb_movies_disponible_erreur=false;

            $scope.gereration_attente=false;
            $scope.gereration=false;
            $scope.gereration_erreur=false;

            $scope.nbdirectors=-1;
            $scope.nbdirectordispo=-1;
            $scope.nbmovies=-1;
            $scope.generationjson="";
            $scope.directorsadd=-1;

            $scope.function_ajoutdirectors =function() {
                $scope.ajout_directors_attente=true;
                var promise = $http.get('/adddirectors', {
                    params: { nbdirectors: $scope.nbdirectors}
                });
                promise.success(function(data) {
                    $scope.directorsadd = data["0"]["count"];
                    if(data["0"]["count"]==$scope.nbdirectors){
                        $scope.ajout_directors_attente = false;
                        $scope.ajout_directors = true;
                        $scope.ajout_directors_erreur = false;
                    }else {
                        $scope.ajout_directors_attente = false;
                        $scope.ajout_directors = false;
                        $scope.ajout_directors_erreur = true;
                    }
                }).error(function (data, status){
                    $scope.ajout_directors_attente=false;
                    $scope.ajout_directors=false;
                    $scope.ajout_directors_erreur=true;
                });
            }

            $scope.function_nbdirectors =function() {
                $scope.nb_directors_disponible_attente=true;
                var promise = $http.get('/nbdirectors');

                promise.success(function(data) {
                    $scope.nbdirectordispo = data["0"]["count"];
                    $scope.nb_directors_disponible_attente=false;
                    $scope.nb_directors_disponible=true;
                    $scope.nb_directors_disponible_erreur=false;
                }).error(function (data, status){
                    $scope.nb_directors_disponible_attente=false;
                    $scope.nb_directors_disponible=false;
                    $scope.nb_directors_disponible_erreur=true;
                });
            }
            $scope.fonction_nbmovies =function() {
                $scope.nb_movies_disponible_attente=true;
                var promise = $http.get('/nbmovies');

                promise.success(function(data) {
                    $scope.nbmovies = data["0"]["count"];
                    $scope.nb_movies_disponible_attente=false;
                    $scope.nb_movies_disponible=true;
                    $scope.nb_movies_disponible_erreur=false;
                }).error(function (data, status){
                    $scope.nb_movies_disponible_attente=false;
                    $scope.nb_movies_disponible=false;
                    $scope.nb_movies_disponible_erreur=true;
                });
            }
            $scope.fonction_generation =function() {
                $scope.gereration_attente=true;
                var promise = $http.get('/quizzquestions');

                promise.success(function(data) {
                    $scope.gereration_attente=false;
                    $scope.gereration=true;
                    $scope.gereration_erreur=false;
                    $scope.generationjson = angular.fromJson(data);
                }).error(function (data, status){
                    $scope.gereration_attente=false;
                    $scope.gereration=false;
                    $scope.gereration_erreur=true;
                });
            }
            $scope.clear =function() {
                $scope.ajout_directors_attente=false;
                $scope.ajout_directors=false;
                $scope.ajout_directors_erreur=false;

                $scope.nb_directors_disponible_attente=false;
                $scope.nb_directors_disponible=false;
                $scope.nb_directors_disponible_erreur=false;

                $scope.nb_movies_disponible_attente=false;
                $scope.nb_movies_disponible=false;
                $scope.nb_movies_disponible_erreur=false;

                $scope.gereration_attente=false;
                $scope.gereration=false;
                $scope.gereration_erreur=false;
                $scope.nbdirectors=-1;
                $scope.nbdirectordispo=-1;
                $scope.nbmovies=-1;
                $scope.generationjson="";
                $scope.directorsadd=-1;

            }

        }
    ]);
})();