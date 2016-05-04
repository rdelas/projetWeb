<%-- 
    Document   : body_header
    Created on : 4 mai 2016, 11:35:06
    Author     : Delas
--%>

<%@include file="header.jsp" %>

<header>
    <p class="titre">Le bon coin etudiant</p>
    <c:choose>
        <c:when test="${user != null}">

            <div>
                Bonjour ${user.lastname} ${user.firstname}
                <form action="ServletLogin" method="get">
                    <input type="hidden" name="action" value="disconnect"/>
                    <input type="submit" value="Déconnexion" name="submit" class="bouton"/>
                </form>
            </div>
        </c:when>    
        <c:otherwise>
            <div id="connexion">
                <form action="ServletLogin" method="post" id="loginForm">
                    Adresse mail : <input type="email" name="mail" minlength="3" title="3 caracteres minimum"/>
                    Password : <input type="password" name="pwd" minlength="8" title="6 caracteres minimum"/>
                    <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->
                    <input type="hidden" name="action" value="connect"/>
                    <input type="submit" value="Connexion" name="submit" class="bouton"/>
                </form>
            </div>
            <div id='enregistrement' >
                <a href="#oModal">
                    <input type="submit" value="S'enregistrer" name='enregistrer' class='bouton'/>
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</header>
