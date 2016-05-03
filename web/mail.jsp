<%-- 
    Document   : mail.jsp
    Created on : 2 mai 2016, 13:39:34
    Author     : rdelas
--%>

<%@include file="includes/header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/style.css"/>        
        <script type="text/javascript" src="scripts/script.js"/>"></script>
    <title>Le bon coin étudiant - Mail</title>
</head>
<body>
    <header>
        <div class="titre">Contacter pour l'annonce : ${annonce.titre}</div>
    </header>
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
