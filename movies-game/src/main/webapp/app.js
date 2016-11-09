(function() {

    var app = angular.module('plunker', []);


    app.controller('madonnee_dynamique', ['$scope','$window', '$http',
        function($scope, $window,$http) {
            $scope.sname = 'sullivan';
            $scope.sid = 111;
    /*        $scope.data =[];*/


            $scope.ready =false;
            $scope.movie = [];

            var promise = $http.get('/quizzquestions');
            promise.success(function(data) {
                $scope.data = data;
                $scope.ready = true;
            });
            this.score=0;
            this.i = 0;
            this.affiche_form = false;
            this.affiche_connection = true;
            this.affiche_res =false;
            this.ini = false;
            this.questionnaires =false;
            this.inputgo =true;
            this.questionsqui=false;
            this.questionsquand=false;
            this.questionsou=false;
            this.questionsresult=false;
            this.go =function() {
                if($scope.ready){
                    $scope.movie = $scope.data[0]["0"];
                    this.inputgo =false;
                    this.i = 0;
                    this.questionsqui=true;
                    this.questionnaires =true;
                }
            }


            this.goquand =function() {
                if(this.questionsqui){
                    this.questionsqui=false;
                    this.questionsquand=true;
                    this.questionsou=false;
                }
            }
            this.goou =function() {
                if(this.questionsquand){
                    this.questionsqui=false;
                    this.questionsquand=false;
                    this.questionsou=true;
                }
            }
            this.goresult =function() {
                if(this.questionsou){
                    this.quitrue = $scope.movie.qui_vrai;
                    this.quandtrue = $scope.movie.quand_vrai;
                    this.latitudetrue = $scope.movie.latitude;
                    this.longitudetrue = $scope.movie.longitude;
                    if(this.quitrue==this.resultqui)
                        this.resultquitrue = true;
                    else
                        this.resultquitrue = false;

                    if(this.quandtrue==this.resultquand)
                        this.resultquandtrue = true;
                    else
                        this.resultquandtrue = false;

                    if((this.latitudetrue>this.resultlatitude-20) && (this.latitudetrue<this.resultlatitude+20) && (this.longitudetrue>this.resultlongitude-20) && (this.longitudetrue<this.resultlongitude+20) )
                        this.resultoutrue = true;
                    else
                        this.resultoutrue = false;

                    if(this.resultquitrue&&this.resultquandtrue&&this.resultoutrue)
                        this.score = this.score + 100;
                    else if(this.resultquitrue&&this.resultquandtrue||this.resultquandtrue&&this.resultoutrue||this.resultquitrue&&this.resultoutrue)
                        this.score = this.score + 60;
                    else if(this.resultquitrue||this.resultquandtrue||this.resultoutrue)
                        this.score = this.score + 25;

                    this.questionsou=false;
                    this.questionsresult=true;

                }
            }
            this.score = 0;
            this.resultqui = "";
            this.resultquand = "";
            this.resultlatitude = null;
            this.resultlongitude = null;

            this.resultquitrue = false;
            this.resultquandtrue = false;
            this.resultoutrue = false;

            this.quitrue ="";
            this.quandtrue ="";
            this.latitudetrue =0;
            this.longitudetrue =0;


            this.suivant =function() {
                if(this.i<9){
                    this.resultqui = "";
                    this.resultquand = "";
                    this.resultlatitude = null;
                    this.resultlongitude = null;
                    this.i++;
                    $scope.movie = $scope.data[0][this.i];
                    this.questionsqui=true;
                    this.questionsquand=false;
                    this.questionsou=false;
                    this.questionsresult=false;
                }else{
                    this.questionsresult=false;
                    this.questionnaires =false;
                    this.inputgo =true;
                }
            }
        }
    ]);
})();