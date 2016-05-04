<%-- 
    Document   : mail_template
    Created on : 2 mai 2016, 14:08:36
    Author     : rdelas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Votre annonce a du succès!</h1>
        <p>Vous avez été contacté pour votre annonce : ${annonce.titre}</p>
        
        <div>
            ${content}
        </div>
    </body>
</html>
