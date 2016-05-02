<%-- 
    Document   : index
    Created on : 17 mars 2016, 11:24:53
    Author     : Clem
--%>
<%@include file="includes/header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/style.css"/>        
        <script type="text/javascript" src="scripts/script.js"/>"></script>
        <title>Le bon coin �tudiant</title>
    </head>
    <body onload="getAnnonceList(1);">
        <header>
            <c:choose>
                <c:when test="${user != null}">
                    <div id="headerDeco">
                        Bonjour ${user.lastname} ${user.firstname}
                        <form action="ServletLogin" method="get">
                            <input type="hidden" name="action" value="disconnect"/>
                            <input type="submit" value="D�connexion" name="submit" class="bouton"/>
                        </form>
                    </div>
                </c:when>    
                <c:otherwise>
                    <div id="headerLogin">
                        <form action="ServletLogin" method="post" id="loginForm">
                            Adresse mail : <input type="email" name="mail" minlength="3" title="3 caracteres minimum"/>
                            Password : <input type="password" name="pwd" minlength="8" title="6 caracteres minimum"/>
                            <!-- Astuce pour passer des param�tres � une servlet depuis un formulaire JSP !-->
                            <input type="hidden" name="action" value="connect"/>
                            <input type="submit" value="Connexion" name="submit" class="bouton"/>
                        </form>
                    </div>
                    <jsp:include page="/ServletAnnonceSearch"/>
                    <jsp:include page="/ServletAnnoncePaginate" >
                        <jsp:param name="listeDesAnnonces" value="${requestScope['listeDesAnnonces']}" />
                    </jsp:include>
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
    </body>
</html>
