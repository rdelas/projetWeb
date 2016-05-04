<%-- 
    Document   : mes_annonces
    Created on : 4 mai 2016, 14:56:25
    Author     : rdelas
--%>

<%@include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
    <%@include file="includes/head.jsp" %>
    <body>
        <%@include file="includes/body_header.jsp" %>
        <%@include file="includes/menu.jsp" %>

        <div>
            <c:forEach var="a" items="${annonces}" varStatus="status">
                <article id="annonce-${status.index}" class="annonce">
                    <h2>${a.titre}</h2>
                    <p>
                        ${fn:toUpperCase(fn:substring(a.categorie, 0, 1))}${fn:toLowerCase(fn:substring(a.categorie, 1,-1))}
                    </p>
                    <p>
                        <fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${a.dateDepot}" />
                    </p>
                    <input type="button" value="Modifier"  class="bouton"/>
                    <input type="button" value="Supprimer" class="bouton"/>
                </article>

            </c:forEach>

        </div>

    </body>
</html>
