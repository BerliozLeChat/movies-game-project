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


            $scope.ready =false;
            $scope.movie = [];

            var promise = $http.get('/game/quizzquestions');

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
                    $scope.repondu=false;
                    $scope.reponse_user="";
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
                    $scope.repondu=false;
                    $scope.reponse_user="";
                }
            }
            $scope.goou =function() {
                if($scope.questionsquand){
                    $interval.cancel(stop);
                    stop = undefined;
                    $scope.timer = $scope.timerchoix;
                    if($scope.timer!=1000)
                        $scope.gotimer();
                    $scope.paysselect ="";
                    $scope.questionsqui=false;
                    $scope.questionsquand=false;
                    $scope.questionsou=true;
                    $scope.mapshow = true;
                    $scope.repondu=false;
                    $scope.reponse_user="";

                }
            }
            $scope.goresult =function($result) {
                if($scope.questionsqui){
                    $scope.reponse_user=$result;
                    $scope.repondu=true;
                    $scope.quitrue = $scope.movie.qui_vrai;
                    if($scope.quitrue==$result){
                        $scope.resultquitrue = true;
                        $scope.score = $scope.score + 10;
                    }
                    else
                        $scope.resultquitrue = false;
                }
                else if($scope.questionsquand){
                    $scope.reponse_user=$result;
                    $scope.repondu=true;
                    $scope.quandtrue = $scope.movie.quand_vrai;
                    if($scope.quandtrue==$result){
                        $scope.resultquandtrue = true;
                        $scope.score = $scope.score + 10;
                    }
                    else
                        $scope.resultquandtrue = false;
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
            var rootApi = 'https://moviesgameoff.appspot.com/_ah/api/';

            $scope.goendgame =function() {
                if($scope.questionsresult){

                    /*gapi.client.load('scoresendpoint', 'v1', function() {
                        console.log("todos api loaded");

                        gapi.client.scoresendpoint.insertscores({scores:$scope.score}).execute(
                            function(resp) {
                                console.log(resp);
                            });


                        gapi.client.scoresendpoint.listscores().execute(
                            function(resp) {
                                console.log(resp);
                            });
                    }, rootApi);*/
                    $scope.questionsresult=false;
                    $scope.endgame = true;
                    $scope.mapshow = false;
                    $scope.questionsou=false;
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
                    $scope.repondu=false;
                    $scope.reponse_user="";
                }else{
                    $scope.endquestionnaire =false;
                    $scope.endgame =false;
                    $scope.questionsresult=false;
                    $scope.questionnaires =false;
                    $scope.i=0;
                    $scope.ready = false;
                    promise = $http.get('/game/quizzquestions');
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
                        else if($scope.questionsou) {
                            $scope.mapshow = false;
                            $scope.questionsresult =true;
                            $scope.pays = $scope.movie.pays;
                            if(!$scope.repondu)
                                $scope.resultoutrue = false;
                            $scope.repondu = true;
                        }
                    }
                }, 100);
            };

            $scope.latlng = [null,null];
            $scope.paysselect ="";
            $scope.getpos = function(event) {
                $scope.latlng = [event.latLng.lat(), event.latLng.lng()];
                $scope.resultlatitude = event.latLng.lat();
                $scope.resultlongitude = event.latLng.lng();
                $scope.pays = $scope.movie.pays;

                var getgooglemap = $http.get('https://maps.googleapis.com/maps/api/geocode/json?latlng='+event.latLng.lat()+','+event.latLng.lng()+'&language=en');

                getgooglemap.success(function(data) {
                    $scope.mapshow = false;
                    if(data.results[0]) {
                        for(var i = 0; i < data.results[0].address_components.length; i++) {
                            if(data.results[0].address_components[i].types[0] == "country") {
                                $scope.paysselect =data.results[0].address_components[i].long_name;
                            }
                        }
                    }

                    if( $scope.pays.match(".*"+$scope.paysselect+".*") && ($scope.paysselect!="") ){
                        $scope.resultoutrue = true;
                        $scope.score = $scope.score + 10;
                    }
                    else
                        $scope.resultoutrue = false;
                    $scope.repondu = true;
                    $scope.questionsresult=true;
                    if($scope.i<9){
                        $scope.endquestionnaire =false;
                    }else{
                        $scope.endquestionnaire =true;
                    }
                });



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