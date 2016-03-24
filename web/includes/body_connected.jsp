<%-- 
    Document   : body_connect
    Created on : 17 mars 2016, 11:53:21
    Author     : Clem
--%>

<%@include file="header.jsp" %>

<nav id="menu">
    <h2 id="titreMenu">Menu</h2>
    <ul>
        <li class="liMenu"><a href="">Accueil</a></li>
        <li class="liMenu"><a href="">Ajouter une annonce</a></li>
        <li class="liMenu"><a href="">Voir toutes les annonces publiées</a></li>
        <li class="liMenu"><a href="">Compte</a></li>
    </ul>
</nav>

<jsp:include page="form_add_annonce.jsp">  
    <jsp:param name="title" value="Ajouter une annonce" />                
    <jsp:param name="btnLabel" value="Enregistrer" />
    <jsp:param name="action" value="creerUneAnnonce" />
</jsp:include>