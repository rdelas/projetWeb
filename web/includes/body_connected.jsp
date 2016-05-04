<%-- 
    Document   : body_connect
    Created on : 17 mars 2016, 11:53:21
    Author     : Clem
--%>

<%@include file="header.jsp" %>

<nav id="menu">
    <table>
        <td class="menu"><a href="#">Accueil</a></td>
        <td class="menu"><a href="#oModalAjoutAnnonce">Ajouter une annonce</a></td>
        <td class="menu"><a href="#oModalModifUser">Compte</a></td>
    </table>
</nav>

<div id='oModalModifUser' class='cModalModifUser'>
    <jsp:include page="/ServletUserForm?action=modify"/>  
    <a href="#" class="btn">Fermer</a>
</div>

<div id='oModalAjoutAnnonce' class="cModalAjoutAnnonce">
    <jsp:include page="/ServletAnnonceForm"/>
    <a href="#" class="btn">Fermer</a>
</div>

<%--<jsp:include page="/ServletAnnonceSearch"/>
<jsp:include page="/ServletAnnoncePaginate" >
    <jsp:param name="listeDesAnnonces" value="${requestScope['listeDesAnnonces']}" />
</jsp:include>--%>
