<%-- 
    Document   : form_seach_annonce
    Created on : 28 avr. 2016, 13:22:55
    Author     : rdelas
--%>
<%@include file="header.jsp" %>

<form id="annonceSearchForm" method="POST" onsubmit="return getAnnonceList(1, this);">
    <label for="titre"> Titre : </label><input type="text" id="titre" name="titre" />
    <br/>
    <label for="campusId">Campus : </label>
    <select id ="campusId" name="campusId">
        <option disabled selected hidden value="" >Votre campus</option>
        <c:forEach items="${campusList}" var="campus">
            <option value="${campus.id}">${campus.nom}</option>
        </c:forEach>
        </option>            
    </select>
    <br/>
    <label for="prixMin">Prix min : </label>
    <input type="number" id="prixMin" name="prixMin" step="0.01" min="0" pattern="\d+((\.|,)\d{2})?" /><br/>
    
    <label for="prixMin">Prix max : </label>
    <input type="number" id="prixMax" name="prixMax" step="0.01" min="0" pattern="\d+((\.|,)\d{2})?" /><br/>
       
    <label>Type d'annonce : </label>
    <c:forEach items="${types}" varStatus="stat" var="type" >
        <input id="${"type".concat(stat.index)}" type="radio" name="type" value="${type}" 
               <c:if test="${stat.index eq 1}">checked</c:if>
        />
        <label for="${"type".concat(stat.index)}" >${fn:toUpperCase(fn:substring(type, 0, 1))}${fn:toLowerCase(fn:substring(type, 1,-1))}</label>
    </c:forEach>
    <br/>
    <label for="categorie"> Catégorie : </label>
    <select id="categorie" name="categorie" size="1">
        <c:forEach items="${categories}" var="categorie" >
            <option value="${categorie}" >${fn:toUpperCase(fn:substring(categorie, 0, 1))}${fn:toLowerCase(fn:substring(categorie, 1,-1))}</option>
        </c:forEach>
    </select>
    <br/>
    <label for="photo">Photo</label>
    <input type="checkbox" id="photo" name="photo">
    <br/>
    <input type="submit" value="Rechercher" class="bouton"/>
</form>
    
