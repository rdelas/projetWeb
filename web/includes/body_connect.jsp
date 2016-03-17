<%-- 
    Document   : body_connect
    Created on : 17 mars 2016, 11:53:21
    Author     : Clem
--%>

<%@include file="header.jsp" %>

<nav id="menu">
    <h2 id="titreMenu">Menu</h2>
    <ul>
        <li class="liMenu"><a href="#afficheModalModif">Modifier les détails d'un utilisateur</a></li>
        <li class="liMenu"><a href="#" onclick="return getUserList(1);">Afficher tous les utilisateurs</a></li>
        <li class="liMenu"><a href="#afficheModalDetail">Afficher les détails d'un utilisateur</a></li>
        <li class="liMenu"><a href="ServletUsers?action=creerUtilisateursDeTest">Créer des utilisateurs de test</a></li>
    </ul>
</nav>
