<%-- 
    Document   : form_add
    Created on : 17 mars 2016, 11:29:32
    Author     : Clem
--%>

<%@include file="header.jsp" %>
    <fieldset>
        <legend>${param.title}</legend>
        <form action="ServletUsers" method="post" id="formEnregistrement">
            <label for="nom">Nom : </label><input type="text" name="nom" id="nom" required/><br>
            <label for="prenom">Prénom : </label><input type="text" name="prenom" id="prenom" required/><br>
            <label for="adresseMail">Adresse mail : </label><input type="email" name="adresseMail" id="mail"/><br>
            <label for="campus">Campus : </label><input type="text" id="campus" name="campus" required/><br/>
            <label for="tel">Téléphone : </label><input type="tel" id="tel" name="tel"/><br/>
            <label for="phptoUrl">Photo : </label><input type="file" id="photoUrl" name="photoUrl"/><br/>
            <label for="password">Password : </label><input type="password" name="password" id="password" minlength="8" title="8 caracteres minimum" required/><br>
            <input type="hidden" name="action" value="${param.action}"/>
            <input type="submit" value="${param.btnLabel}" name="submit" class="bouton"/>
        </form>
    </fieldset>
