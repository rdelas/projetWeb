<%-- 
    Document   : liste_annonce
    Created on : 28 mars 2016, 18:41:27
    Author     : Clem
--%>

<%@include file="header.jsp" %>
<%--<c:out value="${listeDesAnnonces}"></c:out>--%>
<div id="list_results">
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
                <td id="td-prix-${status.index}" class="tdPrix"><fmt:formatNumber type="currency" currencyCode="EUR" minFractionDigits="2" maxFractionDigits="2" value="${a.prix}"/></td>
                <%-- <td id="td-universite-${status.index}" class="tdUniversité">${u.universite}</td> --%>
                <td id="td-dateDepot-${status.index}" class="tdDateDepot"><fmt:formatDate type="date" value="${a.dateDepot}" /></td>
            </tr>
        </c:forEach>

    </table>
    
    <c:if test="${(nbPages-1) gt 1}" >
        <a href="#" onclick="return getAnnonceList(1);">&lt;&lt;</a>
        <a href="#" onclick="return getAnnonceList(${(currentPage-1==1)?1:currentPage-1});">&lt;</a>
        <c:forEach begin="${(currentPage-5) le 0 ? 1 : currentPage-5 }" end="${(currentPage+5) ge nbPages ? nbPages : currentPage+5 }" varStatus="status">
            <a href="#" onclick="return getAnnonceList(${status.index});"
               <c:if test="${status.index eq currentPage}" >
                   style="font-weight: bold;"
               </c:if>
               >${status.index}</a>
        </c:forEach>
        <a href="#" onclick="return getAnnonceList(${(currentPage+1==nbPages)?nbPages:currentPage+1});">&gt;</a>
        <a href="#" onclick="return getAnnonceList(${nbPages});">&gt;&gt;</a>
    </c:if>
</div>
    
<script type="text/javascript" src="js/script.js"></script>