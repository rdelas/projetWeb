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
        <li class="liMenu"><a href="#" onclick="return getAnnonceList(1);">Voir toutes les annonces publiées</a></li>
        <li class="liMenu"><a href="">Compte</a></li>
    </ul>
</nav>

<jsp:include page="/ServletAnnonceForm"/>

<c:if test="${param.action == 'listerLesAnnonces'}" >
    <jsp:include page="/ServletAnnoncePaginate" >
        <jsp:param name="listeDesAnnonces" value="${requestScope['listeDesAnnonces']}" />
    </jsp:include>
</c:if>

<div id="corps" >
    
</div>