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
        <h1>Contacter pour l'annonce</h1>
        <form method="POST" action="ServletMail">
            <label for="content">Votre mail : </label>
            <textarea id="content" name="content" rows="15" spellcheck="true"></textarea>
            <input type="submit" value="Envoyer" />
        </form>
    </body>
</html>
