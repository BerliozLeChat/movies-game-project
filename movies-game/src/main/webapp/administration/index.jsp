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
    boolean connexion;
    boolean admin = false;
    String url;
    if (request.getAttribute("nomUser") == null){
        connexion = false;
        url = (String) request.getAttribute("urlCo");
        response.sendRedirect("/");
    }else{
        connexion = true;
        url = (String) request.getAttribute("urlDeco");
        admin = (boolean) request.getAttribute("admin");
        // a supprimer une fois l'id du compte ajouter en admin 
        admin = true;
        
        if(!admin)
            response.sendRedirect("/");
    }
%>
<head>
    <meta charset="utf-8" />
    <title>Movies game</title>
    <script>document.write('<base href="' + document.location + '" />');</script>
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
        <form name="reviewForm_dynamique" ng-submit="function_ajoutmovies()">
            <label>Nombre de movies à ajouter dans le datastore (max 500 à la fois): <input type="number" ng-model="nbmoviesadd"></label><br>
            <p>{{nbmoviesadd}}</p>
            <input type="submit" value="Update datastore des movies" />
        </form>
        <form name="reviewForm_dynamique" ng-submit="function_ajoutdirectors()">
            <label>Nombre de directors à mettre en tout dans le datastore : <input type="number" ng-model="nbdirectors"></label><br>
            <p>{{nbdirectors}}</p>
            <button type="button">Update datastore des directors</button>
        </form>
        <form name="reviewForm_dynamique" ng-submit="function_ajoutadmin()">
            <label>id du user à ajouter en tant qu'admin : <input type="text" name="cbox1" ng-model="idadmin"></label><br>
            <p>{{idadmin}}</p>
            <button type="button">Ajouter en Admin !</button>
        </form>
        <form name="reviewForm_dynamique" ng-submit="function_nbmoviesdatastore()">
            <input type="submit" value="Compter le nombre de movies disponible dans le Datastore" />
        </form>
        <form name="reviewForm_dynamique" ng-submit="function_nbdirectors()">
            <button type="button">Compter le nombre de directors disponible</button>
        </form>
        <form name="reviewForm_dynamique" ng-submit="fonction_nbmovies()">
            <button type="button">Compter le nombre de movies disponible</button>
        </form>
        <form name="reviewForm_dynamique" ng-submit="fonction_generation()">
            <button type="button">Tester la génération questions JSON</button>
        </form>
        <a href="https://moviesgameoff.appspot.com/_ah/api/explorer"><button type="button" value="Aller sur l'API" /></a>
    </div>

    <div class="resultats">
        <div ng-show="ajout_movies_attente" class="wait">
            <p>L'ajout de {{nbmoviesadd}} movies est en cours ... ...</p>
        </div>
        <div ng-show="ajout_movies" class="functionvalid">
            <p>Les {{nbmoviesadd}} movies ont bien été ajoutés.</p>
        </div>
        <div ng-show="ajout_movies_full" class="functionvalid">
            <p>Tous les films de DBpedia ont été ajoutés (Veuillez verifier si le nombre de movies dans le datastore est égal à celui de DBpedia).</p>
        </div>
        <div ng-show="ajout_movies_erreur" class="erreur">
            <p>Une Erreur s'est produite lors de l'ajout de {{nbmoviesadd}} movies !!!!!</p>
            <sub>(Attention il est possible que vous essayez d'ajouter trop de movies ou que le serveur soit saturé, veuillez vérifier que l'ajout a réelement échoué puis relancez la requète jusqu'au succes ;) ...)</sub>
        </div>
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
        <div ng-show="nb_moviesdatastore_disponible_attente" class="wait">
            <p>La demande du nombre de movies dans le datastore est en cours ... ...</p>
        </div>
        <div ng-show="nb_moviesdatastore_disponible" class="functionvalid">
            <p>Le nombre de movies disponible dans le datastore est : {{nbmoviesdispo}}</p>
        </div>
        <div ng-show="nb_moviesdatastore_disponible_erreur" class="erreur">
            <p>Une Erreur s'est produite lors de la demande du nombre de movies dans le datastore !!!!!</p>
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
            <button type="button">Clear</button>
        </form>
    </div>

</div>

<script src="https://apis.google.com/js/client.js?onload=init"></script>

</body>


</html>