<%-- 
    Document   : form_add
    Created on : 17 mars 2016, 11:29:32
    Author     : Clem
--%>

<%@include file="header.jsp" %>


<fieldset>
    <legend>${titre}</legend>
    <form action="ServletUserForm" method="post" id="formEnregistrement">
        <label for="nom">Nom : </label><input type="text" name="nom" id="nom" value="${form.nom}" required/><br/>
        <c:if test="${errors['nom'] != null}" >
            <p class="error" >${errors['nom']}<p>
        </c:if>
        <label for="prenom">Prénom : </label><input type="text" name="prenom" id="prenom" value="${form.prenom}" required/><br/>
        <c:if test="${errors['prenom'] != null}" >
            <p class="error" >${errors['prenom']}<p>
        </c:if>
        <label for="mail">Adresse mail : </label><input type="email" name="mail" value="${form.mail}" id="mail"/><br/>            
        <c:if test="${errors['mail'] != null}" >
            <p class="error" >${errors['mail']}<p>
        </c:if>
        <label for="pwd">Mot de passe : </label>
        <input type="password" name="pwd" id="pwd" minlength="6" title="6 caracteres minimum" value="${form.pwd}" required/><br/>
        <c:if test="${errors['pwd'] != null}" >
            <p class="error" >${errors['pwd']}<p>
        </c:if>
        <label for="confirm">Confirmer votre mot de passe</label>
        <input type="password" name="confirm" id="confirm" minlength="6" title="6 caracteres minimum" required/><br/>
        <c:if test="${errors['confirm'] != null}" >
            <p class="error" >${errors['confirm']}<p>
        </c:if>
        
        <label for="telephone">Téléphone</label>
        <input type="tel" id="telephone" name="telephone" maxlength="10" value="${form.telephone}" /><br/>
        <c:if test="${errors['telephone'] != null}" >
            <p class="error" >${errors['telephone']}<p>
        </c:if>
        <label for="campusId">Campus : </label>
        <select id ="campusId" name="campusId" value="${form.campusId}" required>
            <option disabled selected hidden value="" >Votre campus</option>
            <c:forEach items="${campusList}" var="campus">
                <option value="${campus.id}">${campus.nom}</option>
            </c:forEach>
            </option>            </select>
        <br/>
        <c:if test="${errors['campusId'] != null}" >
            <p class="error" >${errors['campusId']}<p>
        </c:if>
        <label for="phptoUrl">Photo : </label><input type="file" id="photoUrl" name="photoUrl" value="${form.photoUrl}"/><br/>
        <c:if test="${errors['photoUrl'] != null}" >
            <p class="error" >${errors['photoUrl']}<p>
        </c:if>
        <input type="submit" value="${btnLabel}" name="submit" class="bouton"/>
    </form>
</fieldset>
