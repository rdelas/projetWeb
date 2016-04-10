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
        <li class="liMenu"><a href="#" onclick="return getAnnonceList(1);">Voir toutes les annonces publi�es</a></li>
        <li class="liMenu"><a href="">Compte</a></li>
    </ul>
</nav>

<jsp:include page="form_add.jsp">  
    <jsp:param name="title" value="Modifier les d�tails d'un utilisateur :" />
    <jsp:param name="btnLabel" value="Modifier l'utilisateur" />                
    <jsp:param name="action" value="updateUtilisateur" />
</jsp:include>

<jsp:include page="/ServletAnnoncePaginate" >
    <jsp:param name="listeDesAnnonces" value="${requestScope['listeDesAnnonces']}" />
</jsp:include>

<div id="corps" >

</div>
