<%-- 
    Document   : form_add_produit
    Created on : 20 mars 2016, 14:59:40
    Author     : Clem
--%>

<%@include file="header.jsp" %>
<fieldset>
    <legend>${titre}</legend>
    <form action="ServletAnnonce" method="post">
        <label for="titre">Titre : </label><input type="text" id="titre" name="titre" required/><br/>
        <label for="prix">Prix : </label><input type="number" id="prix" name="prix" step="any" required/><br/>
        <label for="categorie"> Catégorie : </label>
        <select id="categorie" name="categorie" size="1">
            <option value="VEHICULE">Vehicule</option>
            <option value="MUSIQUE">Musique</option>
            <option value="VETEMENT">Vêtement</option>
            <option value="MEUBLE">Meuble</option>
            <option value="MULTIMEDIA">Multimédia</option>
        </select>
        <br/>
        <label for="description">Description : </label>
        <textarea id="description" name="description" required></textarea><br>
        <label for="phptoUrl">Photo : </label><input type="file" id="photoUrl" name="photoUrl"/><br/>
        <input type="hidden" name="action" value="${param.action}"/>
        <input type="submit" value="${btnLabel}" name="submit" class="bouton"/>
    </form>
</fieldset>
<c:if test="${errors != null}">
    <ul>
        <c:forEach items="${errors}" varStatus="status" var="error" >
            <li>${error.key} : ${error.value}</li>
        </c:forEach>    
    </ul>
</c:if>
