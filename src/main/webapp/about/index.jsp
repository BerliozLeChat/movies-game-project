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
	<link href="https://fonts.googleapis.com/css?family=Signika|Montserrat" rel="stylesheet">
    <!-- BOOTSTRAP -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <!-- END BOOTSTRAP -->
    <link href="/styleheader.css" rel="stylesheet" />
    <link href="style.css" rel="stylesheet" />
    <meta name="description" content="About Movies Game">
    <meta name="keywords" content="HTML,CSS,JavaScript,Angular,GoogleAppEngine">
    <meta name="author" content="Le Luet Camille, Hallereau François, Vallée Sebastien & Pineau Sullivan">
    <link rel="icon" href="../favicon.ico">
</head>
<body>
<div id="header">
    <div id="headerleft">
        <a href="/"><img id="image_header" src="/Film-icon.png" alt="icon" height="50" width="50"></a>
    </div>
    <div id="headercenter">
        <% if(connexion){ %>
        <div class="link_right"><a id="mon compte" href="/account/">Mon Compte</a></div>
        <div class="link_right"><a id="deconnexion" href="<% out.println(url); %>">Se déconnecter</a></div>
        <% }else{ %>
        <div class="link_right"><a id="connexion" href="<% out.println(url); %>">Se Connecter</a></div>
        <% } %>
        <div class="link_right"><a id="about" href="/about/">A propos</a></div>
    </div>
    <div id="headerright">
        <a href="https://github.com/BerliozLeChat/movies-game-project"><img id="image_git" src="/github.png" alt="githubicon" height="50" width="50"></a>
    </div>
	<div class="separator"></div>
</div>
<div id="about">
    <h1>À propos</h1>
    <div class="barre_about"></div>
    <div id="description_app"><p>Dans le cadre de notre master informatique en architectures logicielles, nous avons créé une application web déployée avec Google App Engine. Grâce à nos cours de Web&Cloud encadré par M. Molly, nous avons pu réaliser
    cette application. Le jeu consiste à répondre à un maximum de questions sur les films stockés sur DBpedia. Le questionnaire est composé de 10 films comportant chacun 3 questions (qui a réalisé le film ? quand a-t-il été réalisé ? ou a-t-il été réalisé?).
    Si le joueur, connecté via son compte google, réussi à atteindre le tableau des meilleurs score celui-ci entre alors dans le tableau.</p></div>
    <div class="barre_about">
    </div>
    <div class="person">
		<div class="photoContainer" style="background-image: url('camille.png');"></div>
        <p class="description">Camille Le Luët</p>
        <p class="description">Master Alma 1 Nantes 2016-2017</p>
        <a href="mailto:camille.leluet@etu.univ-nantes.fr">Contacter Camille</a>
    </div>
    <div class="person">
		<div class="photoContainer" style="background-image: url('francois.png');"></div>
        <p class="description">François Hallereau</p>
        <p class="description">Master Alma 1 Nantes 2016-2017</p>
        <a href="mailto:francois.hallereau@etu.univ-nantes.fr">Contacter François</a>
    </div>
    <div class="person">
		<div class="photoContainer" style="background-image: url('sebastien.png');"></div>
        <p class="description">Sebastien Vallée</p>
        <p class="description">Master Alma 1 Nantes 2016-2017</p>
        <a href="mailto:sebastien.vallee@etu.univ-nantes.fr">Contacter Sebastien</a>
    </div>
    <div class="person">
		<div class="photoContainer" style="background-image: url('sullivan.png');"></div>
        <p class="description">Sullivan Pineau</p>
        <p class="description">Master Alma 1 Nantes 2016-2017</p>
        <a href="mailto:sullivan.pineau@etu.univ-nantes.fr">Contacter Sullivan</a>
    </div>
</div>
</body>
</html>