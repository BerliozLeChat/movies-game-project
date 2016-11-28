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

    <link href="https://fonts.googleapis.com/css?family=Signika|Montserrat" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="../styleheader.css" rel="stylesheet" />
    <link href="style.css" rel="stylesheet" />

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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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
</div>
<div>
    <div id="errors">
        <h1>ATTENTION OU TU TRAINES TES PATES !</h1>
    </div>
</div>

</body>

</html>