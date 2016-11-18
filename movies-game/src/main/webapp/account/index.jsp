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
    }
%>
<head>
    <meta charset="UTF-8">
    <title>Mon compte</title>  
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="../styleheader.css" rel="stylesheet" />
    <link href="style.css" rel="stylesheet" />
    <meta name="description" content="Acueil Movies Game">
    <meta name="keywords" content="HTML,CSS,JavaScript,Angular,GoogleAppEngine">
    <meta name="author" content="Le Luet Camille, Hallereau François, Vallée Sebastien & Pineau Sullivan">
    <link rel="icon" href="favicon.ico">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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

<div id="account">
    <h1>Mon compte</h1>
    <p>Mon meilleur score est : {{monscore}}</p>
    <p> Voici le top 10 du jeu : </p>
    <table style="width:100%">
        <tr>
            <th>Position</th>
            <th>Scores</th>
        </tr>
        <tr>
            <td>1</td>
            <td>1500</td>
        </tr>
        <tr>
            <td>2</td>
            <td>1480</td>
        </tr>
        <tr>
            <td>3</td>
            <td>1330</td>
        </tr>
        <tr>
            <td>4</td>
            <td>1300</td>
        </tr>
        <tr>
            <td>5</td>
            <td>1250</td>
        </tr>
        <tr>
            <td>6</td>
            <td>1100</td>
        </tr>
        <tr>
            <td>7</td>
            <td>1000</td>
        </tr>
        <tr>
            <td>8</td>
            <td>988</td>
        </tr>
        <tr>
            <td>9</td>
            <td>977</td>
        </tr>
        <tr>
            <td>10</td>
            <td>754</td>
        </tr>
    </table>
    <br></br>
    <br></br>
    <br></br>
    <p>Mon id google : <% out.println(idUser);%></p>
</div>
</body>
</html>