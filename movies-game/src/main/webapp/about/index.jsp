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
    }
%>
<head>
    <meta charset="UTF-8">
    <title>About</title>
    <link href="style.css" rel="stylesheet" />
    <link href="/styleheader.css" rel="stylesheet" />
    <meta name="description" content="About Movies Game">
    <meta name="keywords" content="HTML,CSS,JavaScript,Angular,GoogleAppEngine">
    <meta name="author" content="Le Luet Camille, Hallereau François, Vallée Sebastien & Pineau Sullivan">
    <link rel="icon" href="../favicon.ico">
</head>
<body>
<div id="header">
    <div id="headerleft">
        <a href="https://moviesgameoff.appspot.com/"><img id="image_header" src="/Film-icon.png" alt="icon" height="50" width="50"></a>
    </div>
    <div id="headerright">
        <% if(connexion){ %>
        <div class="link_right"><a id="mon compte" href="https://moviesgameoff.appspot.com/account/">Mon Compte</a></div>
        <div class="link_right"><a id="deconnexion" href="<% out.println(url); %>">Se déconnecter</a></div>
        <% }else{ %>
        <div class="link_right"><a id="connexion" href="<% out.println(url); %>">Se Connecter</a></div>
        <% } %>
        <div class="link_right"><a id="about" href="https://moviesgameoff.appspot.com/about/">A propos</a></div>
        <div class="link_right"><a href="https://github.com/BerliozLeChat/movies-game-project"><img id="image_git" src="/github.png" alt="githubicon" height="50" width="50"></a></div>
    </div>
</div>

<div id="about">
    <h1>À propos</h1>
    <div class="barre_about"></div>
    <div id="description_app"><p>Dans le cadre de notre master, nous avons l'occasion de créer une application Google App Engine. Grâce à nos cours de Web&Cloud encadré par Mr Molly, nous avons pu réaliser
    cette application. Elle consiste à jouer à un quizz de 10 films et que chaque film possède 3 questions (Qui ?, Quand ?, Ou ?).
    Et si la personne connecté réussi à atteindre un meilleur score parmis tous ces scores, alors celui-ci est ajouté dans notre Datastore.
    (Les données des films sont récupérer via une requète Sparql sur DBpedia)</p></div>
    <div class="barre_about">
    </div>
    <div class="person">
        <img id="photo" src="camille.png" alt="photo_camille">
        <p class="description">Camille Le Luet</p>
        <p class="description">Master Alma 1 Nantes 2016-2017</p>
        <a href="mailto:camille.leluet@etu.univ-nantes.fr">Contacter Camille</a>
    </div>
    <div class="person">
        <img id="photo" src="francois.png" alt="photo_francois">
        <p class="description">François Hallereau</p>
        <p class="description">Master Alma 1 Nantes 2016-2017</p>
        <a href="mailto:francois.hallereau@etu.univ-nantes.fr">Contacter François</a>
    </div>
    <div class="person">
        <img id="photo" src="sebastien.png" alt="photo_sebastien">
        <p class="description">Sebastien Valée</p>
        <p class="description">Master Alma 1 Nantes 2016-2017</p>
        <a href="mailto:sebastien.vallee@etu.univ-nantes.fr">Contacter Sebastien</a>
    </div>
    <div class="person">
        <img id="photo" src="sullivan.png" alt="photo_sullivan">
        <p class="description">Sullivan Pineau</p>
        <p class="description">Master Alma 1 Nantes 2016-2017</p>
        <a href="mailto:sullivan.pineau@etu.univ-nantes.fr">Contacter Sullivan</a>
    </div>
</div>
</body>
</html>