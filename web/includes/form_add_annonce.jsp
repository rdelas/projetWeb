<%-- 
    Document   : form_add_produit
    Created on : 20 mars 2016, 14:59:40
    Author     : Clem
--%>

<%@include file="header.jsp" %>
    <fieldset>
        <legend>Ajouter une annonce</legend>
        <form action="ServletUsers" method="post">
            <label for="titre">Titre : </label><input type="text" name="titre" required/><br>
            <label for="prix">Prix : </label><input type="number" name="prix" id="prenom" required/><br>
            <label for="categorie"> Catégorie : </label>
            <select name="listCat" size="1">
                <option>Vehicule
                <option>Musique
                <option>Vêtement
                <option>Meuble
                <option>Multimédia
            </select>
            <label for="description">Description : </label><input type="text" name="description" required/><br>
            <label for="tel">Téléphone : </label><input type="tel" name="tel"/><br>
            <input type="hidden" name="action" value="${param.action}"/>
            <input type="submit" value="${param.btnLabel}" name="submit" class="bouton"/>
        </form>
    </fieldset>
