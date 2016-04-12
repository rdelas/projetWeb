<%-- 
    Document   : index
    Created on : 17 mars 2016, 11:24:53
    Author     : Clem
--%>
<%@include file="includes/header.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css"/>
        <title>Le bon coin étudiant</title>
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body>
        <header>
            <c:choose>
                <c:when test="${user != null}">
                    <div id="headerDeco">
                        Bonjour ${user.lastname} ${user.firstname}
                        <form action="ServletLogin" method="get">
                            <input type="hidden" name="action" value="disconnect"/>
                            <input type="submit" value="Déconnexion" name="submit" class="bouton"/>
                        </form>
                    </div>
                </c:when>    
                <c:otherwise>
                    <div id="headerLogin">
                        <form action="ServletLogin" method="post" id="loginForm">
                            Adresse mail : <input type="email" name="adresseMail" minlength="3" title="3 caracteres minimum"/>
                            Password : <input type="password" name="password" minlength="8" title="6 caracteres minimum"/>
                            <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->
                            <input type="hidden" name="action" value="connect"/>
                            <input type="submit" value="Connexion" name="submit" class="bouton"/>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </header>

        <c:choose>
            <c:when test="${user != null}">
                <jsp:include page="includes/body_connected.jsp">                  
                    <jsp:param name="param" value="${param}" />
                </jsp:include>
            </c:when>
            <c:otherwise>
                <h1>Bienvenu sur le site!</h1>

                <jsp:include page="/ServletUserForm?action=save"/>
            </c:otherwise>    
        </c:choose>

        <c:if test="${param.action == 'listerLesAnnonces'}" >
            <jsp:include page="liste_annonce.jsp" >
                <jsp:param name="listeDesAnnonces" value="${requestScope['listeDesAnnonces']}" />
            </jsp:include>
        </c:if>
    </body>
</html>
