<%-- 
    Document   : liste_annonce
    Created on : 28 mars 2016, 18:41:27
    Author     : Clem
--%>

<%@include file="header.jsp" %>
<%--<c:out value="${listeDesAnnonces}"></c:out>--%>
<table>
    <thead>
    <th>Titre</th>
    <th>Photo</th>
    <th>Prix</th>
    <!--<th>Université</th>-->
    <th>Date de publication</th>
</thead>

<c:forEach var="a" items="${listeDesAnnonces}" varStatus="status">
    <tr>
        <td id="td-titre-${status.index}" class="tdTitre" >${a.titre}</td>
        <td id="td-photo-${status.index}" class="tdPhotoUrl">${a.photoUrl}</td>
        <td id="td-prix-${status.index}" class="tdPrix">${a.prix}</td>
       <%-- <td id="td-universite-${status.index}" class="tdUniversité">${u.universite}</td> --%>
       <td id="td-dateDepot-${status.index}" class="tdDateDepot"><fmt:formatDate type="date" value="${a.dateDepot}" /></td>
    </tr>
</c:forEach>

</table>
<c:if test="${(currentPage-1) gt 1}" >
    <a href="#" onclick="return getAnnonceList(1);">&lt;&lt;</a>
    <a href="#" onclick="return getAnnonceList(${(currentPage-1==1)?1:currentPage-1});">&lt;</a>
    <c:forEach begin="1" end="${nbPages}" varStatus="status">
        <a href="#" onclick="return getAnnonceList(${status.index});">${status.index}</a>
    </c:forEach>
    <a href="#" onclick="return getAnnonceList(${(currentPage+1==nbPages)?nbPages:currentPage+1});">&gt;</a>
    <a href="#" onclick="return getAnnonceList(${nbPages});">&gt;&gt;</a>
</c:if>