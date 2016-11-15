<!DOCTYPE html>
<html lang="fr"  ng-app="movies-game">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@page import="java.util.*"%>

<%

  String nomUser = (String) request.getAttribute("nomUser");
  String idUser = (String) request.getAttribute("idUser");
  boolean admin = (boolean) request.getAttribute("admin");
  boolean connexion;
  String url;
  if (request.getAttribute("nomUser") == null){
    connexion = false;
    url = (String) request.getAttribute("urlCo");
    response.sendRedirect("/");
  }else{
    connexion = true;
    url = (String) request.getAttribute("urlDeco");
  }
%>
<head>
  <meta charset="utf-8" />
  <title>Movies game</title>
  <script>document.write('<base href="' + document.location + '" />');</script>
  <link href="style.css" rel="stylesheet" />
  <link href="/styleheader.css" rel="stylesheet" />
  <script type="text/javascript" src="https://code.angularjs.org/1.5.8/angular.js" data-require="angular.js@1.5.x" data-semver="1.5.8"></script>
  <script type="text/javascript" src="app.js"></script>
  <meta name="description" content="game Movies Game">
  <meta name="keywords" content="HTML,CSS,JavaScript,Angular,GoogleAppEngine">
  <meta name="author" content="Le Luet Camille, Hallereau François, Vallée Sebastien & Pineau Sullivan">
  <link rel="icon" href="../favicon.ico">
  <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCub8fjqFW8TnU4ICK7AtN1_hJcNwlccRM"></script>
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular.js"></script>
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular-sanitize.min.js"></script>
  <script src="https://rawgit.com/allenhwkim/angularjs-google-maps/master/build/scripts/ng-map.js"></script>
</head>

<body>
<div id="header">
  <div id="headerleft">
    <a href="https://moviesgameoff.appspot.com/"><img id="image_header" src="/Film-icon.png" alt="icon" height="50" width="50"></a>
  </div>
  <div id="headerright">
    <div class="link_right"><a id="mon compte" href="https://moviesgameoff.appspot.com/account/">Mon Compte</a></div>
    <div class="link_right"><a id="deconnexion" href="<% out.println(url); %>">Se déconnecter</a></div>
    <div class="link_right"><a id="about" href="https://moviesgameoff.appspot.com/about/">A propos</a></div>
    <div class="link_right"><a href="https://github.com/BerliozLeChat/movies-game-project"><img id="image_git" src="/github.png" alt="githubicon" height="50" width="50"></a></div>
  </div>
</div>
<div ng-controller="madonnee_dynamique as mydonnee_dynamique">
  <div id="div_game">
    <div ng-show="!ready||inputgo">
        <label><input type="radio" name="cbox1" ng-model="timerchoix" value=1000>Niveau 1 (pas de temps pour répondre)</label><br>
        <label><input type="radio" name="cbox1" ng-model="timerchoix" value=100>Niveau 2 (10 secondes pour répondre avec bonus de 1.5)</label><br>
        <label><input type="radio" name="cbox1" ng-model="timerchoix" value=50>Niveau 3 (5 secondes pour répondre avec bonus de 2)</label><br>
      </div>
      <div ng-show="!ready">
        <h1>Génération des questions en cours ... ...</h1>
        <img id="loader" src="ajax-loader.gif" alt="loader" height="35" width="35">
        <br></br>
        <sub>(Si au bout d'une dizaine de secondes la génération des questions n'est pas terminée, veuillez actualiser la page.)</sub>
      </div>
      <div ng-show="ready">
        <div ng-show="inputgo">
          <form name="reviewForm_dynamique" ng-submit="go()">
            <input type="submit" value="Commencer" />
          </form>
        </div>
        <div ng-show="questionnaires">
          <div id="barre_progression">
            <h1>Score : {{score}}</h1>
            <p ng-show="!endgame">Question numéro {{i+1}}/10</p>
            <p ng-show="!questionsresult && !endgame && (timerchoix!=1000)">Timer : <font color='red'>{{(timer)/10}}s</font></p>
            <div ng-show="!questionsresult && !endgame && (timerchoix!=1000)" id="barre" style="margin-right:{{((105-((timerchoix-timer)/timerchoix)*100)/105)*100}}% !important;"></div>
          </div>
          <div ng-show="questionsqui">
            <h1>{{movie.nom}}</h1>
            <h1>Qui a réalisé ce film ?</h1>
            <form name="reviewForm_dynamique" ng-submit="goquand()">
              <div id="choix">
                <label><input type="radio" name="cbox1" ng-model="resultqui" value={{movie.qui_r1}}>{{movie.qui_r1}}</label><br>
                <label><input type="radio" name="cbox1" ng-model="resultqui" value={{movie.qui_r2}}>{{movie.qui_r2}}</label><br>
                <label><input type="radio" name="cbox1" ng-model="resultqui" value={{movie.qui_r3}}>{{movie.qui_r3}}</label><br>
              </div>
              <input id="valid1" type="submit" value="Valider" />
            </form>
          </div>
          <div ng-show="questionsquand">
            <h1>{{movie.nom}}</h1>
            <h1>Quand a été réalisé ce film ?</h1>
            <form name="reviewForm_dynamique" ng-submit="goou()">
              <div id="choix">
                <label><input type="radio" name="cbox1" ng-model="resultquand" value={{movie.quand_r1}}>{{movie.quand_r1}}</label><br>
                <label><input type="radio" name="cbox1" ng-model="resultquand" value={{movie.quand_r2}}>{{movie.quand_r2}}</label><br>
                <label><input type="radio" name="cbox1" ng-model="resultquand" value={{movie.quand_r3}}>{{movie.quand_r3}}</label><br>
              </div>
              <input id="valid2" type="submit" value="Valider" />
            </form>
          </div>
          <div ng-show="questionsou">
            <h1>{{movie.nom}}</h1>
            <h1>Ou a été réalisé ce film ?</h1>

            <form name="reviewForm_dynamique" ng-submit="goresult()">
  <!--
              <label>Latitude : <div id="choix"><input type="number" name="cbox1" ng-model="resultlatitude"></div></label><br>
              <label>Longitude : <div id="choix"><input type="number" name="cbox1" ng-model="resultlongitude"></div></label><br>
  -->
              <p>Veuillez cliquer l'endroit sur la map en dessous !</p>
              <p>Latitude sélectionnée : {{resultlatitude}}</p>
              <p>Longitude sélectionnée : {{resultlongitude}}</p>
              <input id="valid3" type="submit" value="Valider" />
            </form>
          </div>
          <div ng-show="questionsresult">
            <h1>{{movie.nom}}</h1>
            <div id="resultats">
              <p>RESULTATS : </p>
              <div ng-show="resultquitrue">
                <p style="color:green;">Ce film a bien été réalisé par "{{resultqui}}"</p>
              </div>
              <div ng-show="!resultquitrue">
                <p style="color:#942d40;">Ce film n'a pas été réalisé par "{{resultqui}}" mais par "{{quitrue}}"</p>
              </div>
              <div ng-show="resultquandtrue">
                <p style="color:green;">Ce film a bien été réalisé en "{{resultquand}}"</p>
              </div>
              <div ng-show="!resultquandtrue">
                <p style="color:#942d40;">Ce film n'a pas été réalisé en "{{resultquand}}" mais en "{{quandtrue}}"</p>
              </div>
              <div ng-show="resultoutrue">
                <p style="color:green;">Ce film a été réalisé en latitude : "{{latitudetrue}}" et en longitude : "{{longitudetrue}}" (Votre réponse a
                  été validée car vous étiez à moins de 20 pour chaques coordonnées ! )</p>
              </div>
              <div ng-show="!resultoutrue">
                <p style="color:#942d40;">Ce film n'a pas été réalisé en latitude : "{{resultlatitude}}" et en longitude : "{{resultlongitude}}" mais en latitude : "{{latitudetrue}}" et en longitude : "{{longitudetrue}}"</p>
              </div>
            </div>
            <div ng-show="!endquestionnaire">
              <form name="reviewForm_dynamique" ng-submit="suivant()">
                <input type="submit" value="Film suivant" />
              </form>
            </div>
            <div ng-show="endquestionnaire">
              <form name="reviewForm_dynamique" ng-submit="goendgame()">
                <input type="submit" value="Fin" />
              </form>
            </div>
          </div>
          <div ng-show="endgame">
            <p>Merci d'avoir joué à notre jeu :)</p>
            <p>Si vous souhaitez faire un score encore mieux, cliquez sur REJOUER !</p>
            <form name="reviewForm_dynamique" ng-submit="suivant()">
              <input type="submit" value="REJOUER" />
            </form>
          </div>
        </div>
      </div>
    <div class="map">
      <p ng-show="!ready&&mapshow" >Initialisation de la map Google ... ... </p>
      <ng-map ng-show="mapshow" id="map" zoom="1" on-click="getpos($event)">
        <marker position="{{latlng}}" title="Hello World!" on-dragend="getpos($event)"
                animation="DROP" draggable="true"></marker>
      </ng-map>
    </div>
  </div>
</div>

<script src="https://apis.google.com/js/client.js?onload=init"></script>

</body>

</html>