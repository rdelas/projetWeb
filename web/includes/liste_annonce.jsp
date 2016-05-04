<%-- 
    Document   : liste_annonce
    Created on : 28 mars 2016, 18:41:27
    Author     : Clem
--%>

<%@include file="header.jsp" %>
<div id="list_results">
    
    <c:forEach var="a" items="${listeDesAnnonces}" varStatus="status">
        <div id="annonce-${status.index}" class="annonce">
            <h2>${a.titre}</h2>
            <h3>
                <fmt:formatNumber type="currency" currencySymbol="&euro;" minFractionDigits="2" maxFractionDigits="2" value="${a.prix}"/>
            </h3>
                
            <p>
                <em>
                    <fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${a.dateDepot}" />
                </em>
            </p>
            <p><em>${a.categorie}</em></p>
            <p><em>${a.utilisateur.campus.nom}</em></p>
            <p>${a.description}</p>
            <c:url value="/ServletMail" var="url">
                <c:param name="id" value="${a.encryptedId}" />
              </c:url>
            
            <a href="${url}">Me contacter</a>
        </div>
        
    </c:forEach>
    
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
