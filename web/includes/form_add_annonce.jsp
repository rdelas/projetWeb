<%-- 
    Document   : form_add_produit
    Created on : 20 mars 2016, 14:59:40
    Author     : Clem
--%>

<%@include file="header.jsp" %>
<fieldset>
    <legend>${titre}</legend>
    <form action="ServletAnnonceForm" method="post">
        <label>Type d'annonce : </label>
            <c:forEach items="${types}" varStatus="stat" var="type" >
                <input id="${"type".concat(stat.index)}" type="radio" name="type" value="${type}" 
                       <c:if test="${stat.first}" >
                           required
                       </c:if>                    
                />
                <label for="${"type".concat(stat.index)}" >${fn:toUpperCase(fn:substring(type, 0, 1))}${fn:toLowerCase(fn:substring(type, 1,-1))}</label>
            </c:forEach>
        <br/>
        <label for="titre">Titre : </label><input type="text" id="titre" name="titre" required/><br/>
        <label for="prix">Prix : </label><input type="number" id="prix" name="prix" step="0.01" min="0" pattern="\d+((\.|,)\d{2})?"  required/><br/>
        <label for="categorie"> Catégorie : </label>
        <select id="categorie" name="categorie" size="1">
            <c:forEach items="${categories}" var="categorie" >
                <option value="${categorie}" >${fn:toUpperCase(fn:substring(categorie, 0, 1))}${fn:toLowerCase(fn:substring(categorie, 1,-1))}</option>
            </c:forEach>
        </select>
        <br/>
        <label for="description">Description : </label>
        <textarea id="description" name="description" required></textarea><br>
        <label for="phptoUrl">Photo : </label><input type="file" id="photoUrl" name="photoUrl"/><br/>
        <input type="hidden" name="action" value="${action}"/>
        <input type="submit" value="${btnLabel}" name="submit" class="bouton"/>
    </form>
</fieldset>
<c:if test="${errors != null}">
    <ul>
        <c:forEach items="${errors}" varStatus="status" var="error" >
            <li>${status.index} : ${error}</li>
            </c:forEach>    
    </ul>
</c:if>
