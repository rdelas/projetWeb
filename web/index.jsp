<%-- 
    Document   : index
    Created on : 17 mars 2016, 11:24:53
    Author     : Clem
--%>
<%@include file="includes/header.jsp" %>

<!DOCTYPE html>
<html>
    <%@include file="includes/head.jsp" %>
    <body onload="getAnnonceList(1);">

        <%@include file="includes/body_header.jsp" %>

        <c:if test="${user != null}" >            
            <jsp:include page="includes/menu.jsp"/>  
        </c:if>

        <jsp:include page="/ServletAnnonceSearch"/>
        <jsp:include page="/ServletAnnoncePaginate" >
            <jsp:param name="listeDesAnnonces" value="${requestScope['listeDesAnnonces']}" />
        </jsp:include>

        <c:if test="${user == null}" >            
            <div id='oModal' class='cModalEnregistrementUser'>
                <jsp:include page="/ServletUserForm?action=save"/>
                <a href="#" class="btn">Fermer</a>
            </div>
        </c:if>
    </body>
</html>
