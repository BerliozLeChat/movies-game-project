<!DOCTYPE html>
<html lang="fr"  ng-app="movies-game">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@page import="java.util.*"%>

<%

    String nomUser = (String) request.getAttribute("nomUser");
    boolean connexion;
    boolean admin = false;
    String url;
    if (request.getAttribute("nomUser") == null){
        connexion = false;
        url = (String) request.getAttribute("urlCo");
    }else{
        connexion = true;
        url = (String) request.getAttribute("urlDeco");
        admin = (boolean) request.getAttribute("admin");

    }
%>
<head>
    <meta charset="utf-8" />
    <title>Movies game</title>
    <script>document.write('<base href="' + document.location + '" />');</script>
	<!-- BOOTSTRAP -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<!-- END BOOTSTRAP -->
    <link href="../styleheader.css" rel="stylesheet" />
    <link href="style.css" rel="stylesheet" />
    <script type="text/javascript" src="https://code.angularjs.org/1.5.8/angular.js" data-require="angular.js@1.5.x" data-semver="1.5.8"></script>
    <script type="text/javascript" src="app.js"></script>
    <meta name="description" content="game Movies Game">
    <meta name="keywords" content="HTML,CSS,JavaScript,Angular,GoogleAppEngine">
    <meta name="author" content="Le Luet Camille, Hallereau François, Vallée Sebastien & Pineau Sullivan">
    <link rel="icon" href="../favicon.ico">
</head>
<body>
<div id="header">
    <div id="headerleft">
        <a href="https://moviesgameoff.appspot.com/"><img id="image_header" src="/Film-icon.png" alt="icon" height="50" width="50"></a>
    </div>
    <div id="headercenter">
        <% if(connexion){ %>
        <div class="link_right"><a id="mon compte" href="https://moviesgameoff.appspot.com/account/">Mon Compte</a></div>
        <div class="link_right"><a id="deconnexion" href="<% out.println(url); %>">Se déconnecter</a></div>
        <% }else{ %>
        <div class="link_right"><a id="connexion" href="<% out.println(url); %>">Se Connecter</a></div>
        <% } %>
        <div class="link_right"><a id="about" href="https://moviesgameoff.appspot.com/about/">A propos</a></div>
    </div>
    <div id="headerright">
        <a href="https://github.com/BerliozLeChat/movies-game-project"><img id="image_git" src="/github.png" alt="githubicon" height="50" width="50"></a>
    </div>
</div>

<div id="administration"  ng-controller="madonnee_dynamique as mydonnee_dynamique">
    <h1>Admin</h1>
    <div class="commandes">
        <form name="reviewForm_dynamique" ng-submit="function_ajoutdirectors()">
            <label>Nombre de directors à mettre en tout dans le datastore : <input type="number" name="cbox1" ng-model="nbdirectors"></label><br>
            <p>{{nbdirectors}}</p>
            <button class="btn"  type="button">Update datastore des directors</button>
        </form>
        <form name="reviewForm_dynamique" ng-submit="function_ajoutadmin()">
            <label>id du user à ajouter en tant qu'admin : <input type="text" name="cbox1" ng-model="idadmin"></label><br>
            <p>{{idadmin}}</p>
            <button class="btn"  type="button">Ajouter en Admin !</button>
        </form>
        <form name="reviewForm_dynamique" ng-submit="function_nbdirectors()">
            <button class="btn"  type="button">Compter le nombre de directors disponible</button>
        </form>
        <form name="reviewForm_dynamique" ng-submit="fonction_nbmovies()">
            <button class="btn"  type="button">Compter le nombre de movies disponible</button>
        </form>
        <form name="reviewForm_dynamique" ng-submit="fonction_generation()">
            <button class="btn"  type="button">Tester la génération questions JSON</button>
        </form>
        <a href="https://moviesgameoff.appspot.com/_ah/api/explorer"><button class="btn"  type="button" value="Aller sur l'API" /></a>
    </div>

    <div class="resultats">
        <div ng-show="ajout_directors_attente" class="wait">
            <p>L'ajout de {{nbdirectors}} directors est en cours ... ...</p>
        </div>
        <div ng-show="ajout_directors" class="functionvalid">
            <p>Les {{directorsadd}} directors ont bien été ajoutés.</p>
        </div>
        <div ng-show="ajout_directors_erreur" class="erreur">
            <p>Une Erreur s'est produite lors de l'ajout de {{nbdirectors}} directors !!!!!</p>
            <sub>(Attention il est possible que vous essayez d'ajouter un nombre plus faible que le nombre actuel de directors ou que le serveur soit saturé, veuillez vérifier que l'ajout a réelement échoué puis relancez la requète jusqu'au succes ;) ...)</sub>
        </div>
        <div ng-show="ajout_admin_attente" class="wait">
            <p>L'ajout de {{idadmin}} en admin est en cours ... ...</p>
        </div>
        <div ng-show="ajout_admin" class="functionvalid">
            <p>L'ajout de {{idadmin}} a bien été ajoutés.</p>
        </div>
        <div ng-show="ajout_admin_erreur" class="erreur">
            <p>Une Erreur s'est produite lors de l'ajout de {{idadmin}} en admin !!!!!</p>
        </div>
        <div ng-show="nb_directors_disponible_attente" class="wait">
            <p>La demande du nombre de directors est en cours ... ...</p>
        </div>
        <div ng-show="nb_directors_disponible" class="functionvalid">
            <p>Le nombre de directors disponible est : {{nbdirectordispo}}</p>
        </div>
        <div ng-show="nb_directors_disponible_erreur" class="erreur">
            <p>Une Erreur s'est produite lors de la demande du nombre de directors !!!!!</p>
        </div>
        <div ng-show="nb_movies_disponible_attente" class="wait">
            <p>La demande du nombre de movies est en cours ... ...</p>
        </div>
        <div ng-show="nb_movies_disponible" class="functionvalid">
            <p>Le nombre de movies disponible est : {{nbmovies}}</p>
        </div>
        <div ng-show="nb_movies_disponible_erreur" class="erreur">
            <p>Une Erreur s'est produite lors de la demande du nombre de movies !!!!!</p>
        </div>
        <div ng-show="gereration_attente" class="wait">
            <p>La génération des question est en cours ... ...</p>
        </div>
        <div ng-show="gereration" class="functionvalid">
            <p>Voici le réponse optenu : </p>
            <p>{{generationjson}}</p>
        </div>
        <div ng-show="gereration_erreur" class="erreur">
            <p>Une Erreur s'est produite lors de la génération des questions !!!!!</p>
        </div>
        <form name="reviewForm_dynamique" ng-submit="clear()">
            <button class="btn"  type="button">Clear</button>
        </form>
    </div>

</div>

<script src="https://apis.google.com/js/client.js?onload=init"></script>

</body>


</html>