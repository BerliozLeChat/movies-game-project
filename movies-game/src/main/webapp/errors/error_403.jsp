<!DOCTYPE html>
<html lang="fr" >
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
    <link href="../errors/style.css" rel="stylesheet" />

    <meta name="description" content="game Movies Game">
    <meta name="keywords" content="HTML,CSS,JavaScript,Angular,GoogleAppEngine">
    <meta name="author" content="Le Luet Camille, Hallereau François, Vallée Sebastien & Pineau Sullivan">
    <link rel="icon" href="../favicon.ico">
</head>

<body>

<jsp:include page="/header.jsp"></jsp:include>
<div>
    <div id="errors">
        <div class="sub_error">
            <h1>ERREUR 403 !</h1>
            <sub>Vous essayez d'accéder à une page pour laquelle vous n'avez pas la permission ! veuillez vous rediriger vers l'<a href="/">accueil</a> !</sub>
        </div>
        <img class="sub_error" src="../errors/erreur_403_image.jpg"/>
    </div>
</div>

</body>

</html>