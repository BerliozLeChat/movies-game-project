(function() {

    var app = angular.module('movies-game', ['ngMap']);

    app.run(function($rootScope) {
        $rootScope.$on('mapInitialized', function(evt,map) {
            $rootScope.map = map;
            $rootScope.$apply();
        });
    })


    app.directive('myCurrentTime', ['$interval', 'dateFilter',
        function($interval, dateFilter) {
            // return the directive link function. (compile function not needed)
            return function(scope, element, attrs) {
                var format,  // date format
                    stopTime; // so that we can cancel the time updates

                // used to update the UI
                function updateTime() {
                    element.text(dateFilter(new Date(), format));
                }

                // watch the expression, and update the UI on change.
                scope.$watch(attrs.myCurrentTime, function(value) {
                    format = value;
                    updateTime();
                });

                stopTime = $interval(updateTime, 1000);

                // listen on DOM destroy (removal) event, and cancel the next UI update
                // to prevent updating time after the DOM element was removed.
                element.on('$destroy', function() {
                    $interval.cancel(stopTime);
                });
            }}]);

    app.controller('madonnee_dynamique', ['$scope','$window', '$http', '$interval',
        function($scope, $window,$http,$interval) {

            $scope.sname = 'sullivan';
            $scope.sid = 111;
            /*        $scope.data =[];*/


            $scope.ready =false;
            $scope.movie = [];

            var promise = $http.get('/quizzquestions');

            promise.success(function(data) {
                $scope.data = data;
                $scope.ready = true;
                $scope.mapshow = false;
            });
            $scope.score=0;
            $scope.i = 0;
            $scope.affiche_form = false;
            $scope.affiche_connection = true;
            $scope.affiche_res =false;
            $scope.ini = false;
            $scope.questionnaires =false;
            $scope.inputgo =true;
            $scope.questionsqui=false;
            $scope.questionsquand=false;
            $scope.questionsou=false;
            $scope.questionsresult=false;
            $scope.go =function() {
                if($scope.ready&&$scope.timerchoix != 10000){
                    $scope.mapshow = false;
                    $scope.score=0;
                    $scope.movie = $scope.data[0]["0"];
                    $scope.inputgo =false;
                    $scope.i = 0;
                    $scope.timer = $scope.timerchoix;
                    if($scope.timer!=1000)
                        $scope.gotimer();
                    $scope.questionsqui=true;
                    $scope.questionnaires =true;
                }
            }


            $scope.goquand =function() {
                if($scope.questionsqui){
                    $interval.cancel(stop);
                    stop = undefined;
                    $scope.timer = $scope.timerchoix;
                    if($scope.timer!=1000)
                        $scope.gotimer();
                    $scope.questionsqui=false;
                    $scope.questionsquand=true;
                    $scope.questionsou=false;
                }
            }
            $scope.goou =function() {
                if($scope.questionsquand){
                    $interval.cancel(stop);
                    stop = undefined;
                    $scope.timer = $scope.timerchoix;
                    if($scope.timer!=1000)
                        $scope.gotimer();
                    $scope.questionsqui=false;
                    $scope.questionsquand=false;
                    $scope.questionsou=true;
                    $scope.mapshow = true;

                }
            }
            $scope.goresult =function() {
                if($scope.questionsou){
                    $interval.cancel(stop);
                    stop = undefined;
                    $scope.timer = $scope.timerchoix;
                    $scope.quitrue = $scope.movie.qui_vrai;
                    $scope.quandtrue = $scope.movie.quand_vrai;
                    $scope.latitudetrue = $scope.movie.latitude;
                    $scope.longitudetrue = $scope.movie.longitude;
                    if($scope.quitrue==$scope.resultqui)
                        $scope.resultquitrue = true;
                    else
                        $scope.resultquitrue = false;

                    if($scope.quandtrue==$scope.resultquand)
                        $scope.resultquandtrue = true;
                    else
                        $scope.resultquandtrue = false;

                    if(($scope.latitudetrue>$scope.resultlatitude-20) && ($scope.latitudetrue<$scope.resultlatitude+20) && ($scope.longitudetrue>$scope.resultlongitude-20) && ($scope.longitudetrue<$scope.resultlongitude+20) )
                        $scope.resultoutrue = true;
                    else
                        $scope.resultoutrue = false;

                    if($scope.timerchoix==50)
                        $scope.bonus=2;
                    else if($scope.timerchoix==100)
                        $scope.bonus=1.5;

                    if($scope.resultquitrue&&$scope.resultquandtrue&&$scope.resultoutrue)
                        $scope.score = $scope.score + (100*$scope.bonus);
                    else if($scope.resultquitrue&&$scope.resultquandtrue||$scope.resultquandtrue&&$scope.resultoutrue||$scope.resultquitrue&&$scope.resultoutrue)
                        $scope.score = $scope.score + (60*$scope.bonus);
                    else if($scope.resultquitrue||$scope.resultquandtrue||$scope.resultoutrue)
                        $scope.score = $scope.score + (25*$scope.bonus);
                    $scope.mapshow = false;
                    $scope.questionsou=false;

                    $scope.questionsresult=true;
                    if($scope.i<9){
                        $scope.endquestionnaire =false;
                    }else{
                        $scope.endquestionnaire =true;
                    }
                }
            }
            $scope.score = 0;
            $scope.resultqui = "";
            $scope.resultquand = "";
            $scope.resultlatitude = null;
            $scope.resultlongitude = null;
            $scope.mapshow = true;

            $scope.resultquitrue = false;
            $scope.resultquandtrue = false;
            $scope.resultoutrue = false;
            $scope.endgame = false;
            $scope.quitrue ="";
            $scope.quandtrue ="";
            $scope.latitudetrue =0;
            $scope.longitudetrue =0;
            $scope.endquestionnaire=false;
            $scope.bonus=1;
            $scope.goendgame =function() {
                if($scope.questionsresult){
                    $scope.questionsresult=false;
                    $scope.endgame = true;
                }
            }

            $scope.suivant =function() {
                if($scope.i<9 && !$scope.endquestionnaire){
                    $scope.resultqui = "";
                    $scope.resultquand = "";
                    $scope.resultlatitude = null;
                    $scope.resultlongitude = null;
                    $scope.latlng = [null,null];
                    $scope.i++;
                    $scope.movie = $scope.data[0][$scope.i];
                    $interval.cancel(stop);
                    stop = undefined;
                    $scope.timer = $scope.timerchoix;
                    if($scope.timer!=1000)
                        $scope.gotimer();
                    $scope.mapshow = false;
                    $scope.questionsqui=true;
                    $scope.questionsquand=false;
                    $scope.questionsou=false;
                    $scope.questionsresult=false;
                }else{
                    $scope.endquestionnaire =false;
                    $scope.endgame =false;
                    $scope.questionsresult=false;
                    $scope.questionnaires =false;
                    $scope.i=0;
                    $scope.ready = false;
                    promise = $http.get('/quizzquestions');
                    promise.success(function(data) {
                        $scope.data = data;
                        $scope.ready = true;
                    });
                    $scope.inputgo =true;
                }
            }

            $scope.timerchoix = 10000;
            $scope.timer = 25;
            var stop;
            $scope.timeout_qui = false;
            $scope.timeout_quand = false;
            $scope.timeout_ou = false;
            $scope.gotimer = function() {
                stop = $interval(function()
                {
                    if ($scope.timer > 0) {
                        $scope.timer = $scope.timer - 1;
                    } else {
                        $interval.cancel(stop);
                        stop = undefined;
                        $scope.timer = $scope.timerchoix;
                        if($scope.questionsqui)
                            $scope.goquand();
                        else if($scope.questionsquand)
                            $scope.goou();
                        else if($scope.questionsou)
                            $scope.goresult();
                    }
                }, 100);
            };

            $scope.latlng = [null,null];

            $scope.getpos = function(event) {
                $scope.latlng = [event.latLng.lat(), event.latLng.lng()];
                $scope.resultlatitude = event.latLng.lat();
                $scope.resultlongitude = event.latLng.lng();
            }
        }

    ]);
    app.run(function($rootScope) {
        $rootScope.$on('mapInitialized', function(evt,map) {
            $rootScope.map = map;
            $rootScope.$apply();
        });
    })

})();