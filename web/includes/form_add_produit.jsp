<%-- 
    Document   : form_add_produit
    Created on : 20 mars 2016, 14:59:40
    Author     : Clem
--%>

<%@include file="header.jsp" %>
    <fieldset>
        <legend>Ajouter un produit à vendre</legend>
        <form action="ServletUsers" method="post">
            <label for="nomProduit">Nom du produit: </label><input type="text" name="nomProduit" required/><br>
            <label for="prix">Prix : </label><input type="number" name="prix" id="prenom" required/><br>
            <label for="ville">Ville : </label><input type="text" name="ville" required/><br>    
            <label for="cp">Code postal : </label><input type="number" name="cp" required/><br>
            <label for="description">Description : </label><input type="text" name="description" required/><br>
            <label for="tel">Téléphone : </label><input type="tel" name="tel"/><br>
            <input type="hidden" name="action" value="${param.action}"/>
            <input type="submit" value="${param.btnLabel}" name="submit" class="bouton"/>
        </form>
    </fieldset>
