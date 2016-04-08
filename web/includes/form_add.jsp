<%-- 
    Document   : form_add
    Created on : 17 mars 2016, 11:29:32
    Author     : Clem
--%>

<%@include file="header.jsp" %>
    <fieldset>
        <legend>${param.title}</legend>
        <form action="ServletUserForm" method="post" id="formEnregistrement">
            <label for="nom">Nom : </label><input type="text" name="nom" id="nom" required/><br/>
            <label for="prenom">Prénom : </label><input type="text" name="prenom" id="prenom" required/><br/>
            <label for="adresseMail">Adresse mail : </label><input type="email" name="adresseMail" id="mail"/><br/>            
            <label for="password">Mot de passe : </label>
            <input type="password" name="password" id="password" minlength="6" title="6 caracteres minimum" required/><br/>
            <label for="confirm_password">Confirmer votre mot de passe</label>
            <input type="password" name="confirm_password" id="confirm_password" minlength="6" title="6 caracteres minimum" required/><br/>
            <label for="telephone">Téléphone</label>
            <input type="tel" id="telephone" name="telephone" maxlength="10" /><br/>
            <label for="campus">Campus : </label>
            <select id ="campus" name="campus" required>
                <option disabled selected hidden value="" >Votre campus</option>
                <c:forEach items="${campusList}" var="campus">
                    <option value="${campus.id}">${campus.nom}</option>
                </c:forEach>
                </option>            </select>
            <br/>
            <label for="phptoUrl">Photo : </label><input type="file" id="photoUrl" name="photoUrl"/><br/>
            <!--<input type="hidden" name="action" value="${param.action}"/>-->
            <input type="submit" value="${param.btnLabel}" name="submit" class="bouton"/>
        </form>
    </fieldset>
