<%-- 
    Document   : mon_compte
    Created on : 4 mai 2016, 11:38:55
    Author     : Delas
--%>

<%@include file="includes/header.jsp" %>
<!DOCTYPE html>
<html>
    <%@include file="includes/head.jsp" %>
    <body>        
        <%@include file="includes/body_header.jsp" %>
        <%@include file="includes/menu.jsp" %>
        <jsp:include page="/ServletUserForm?action=modify"/>  
    </body>
</html>
