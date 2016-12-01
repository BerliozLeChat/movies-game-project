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