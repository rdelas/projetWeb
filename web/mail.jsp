<%-- 
    Document   : mail.jsp
    Created on : 2 mai 2016, 13:39:34
    Author     : rdelas
--%>

<%@include file="includes/header.jsp" %>

<!DOCTYPE html>
<html>
    <%@include file="includes/head.jsp" %>
    <body>
        <jsp:include page="includes/body_header.jsp"/> 
        <jsp:include page="includes/menu.jsp"/> 
        <div class="titre">Contacter pour l'annonce : ${annonce.titre}</div>
        <c:set var="u" value="${annonce.utilisateur}" />
        <form method="POST" action="ServletMail" id="formMail">
            <p>Destinataire : ${fn:toUpperCase(fn:substring(u.firstname, 0, 1))}${fn:toLowerCase(fn:substring(u.firstname, 1,-1))} ${u.lastname}</p>
            <label id="labelMail" for="content">Votre mail : </label><br>
            <textarea id="content" name="content" rows="15" spellcheck="true"></textarea><br>
            <input type="hidden" name="id" value="${annonce.encryptedId}" /> 
            <input type="submit" value="Envoyer" class="bouton"/>
        </form>
    </body>
</html>
