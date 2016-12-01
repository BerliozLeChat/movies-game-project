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
	<link href="https://fonts.googleapis.com/css?family=Signika|Montserrat" rel="stylesheet"> 
	<!-- BOOTSTRAP -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<!-- END BOOTSTRAP -->
    <link href="../styleheader.css" rel="stylesheet" />
    <link href="style.css" rel="stylesheet" />
    <meta name="description" content="game Movies Game">
    <meta name="keywords" content="HTML,CSS,JavaScript,Angular,GoogleAppEngine">
    <meta name="author" content="Le Luet Camille, Hallereau François, Vallée Sebastien & Pineau Sullivan">
    <link rel="icon" href="../favicon.ico">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<div class="middle" id="menu">
    <% if(!connexion){ %>
       <div id="input_connection">
           <a id="connexion_menu" href="<% out.println(url); %>">
               <button class="btn input" type="button">Se Connecter</button>
           </a>
       </div>
    <% }else{ %>
        <div id="input_connection">
                <a id="" href="<% out.println(url); %>">
                    <button class="btn input" type="submit">Se déconnecter</button>
                </a>
        </div>
        <div id="input_jeu">
           <a href="/game/">
               <button class="btn input" type="submit">Jouer</button>
           </a>
        </div>
    <% } %>
    <% if(admin){ %>
        <div id="input_administration">
            <a href="/administration/">
                <button class="btn input" type="submit">Administration</button>
            </a>
        </div>
    <% } %>
</div>
<div class="middle" id="top">
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
</div>
<script src="https://apis.google.com/js/client.js?onload=init"></script>
</body>


</html>
