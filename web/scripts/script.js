/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getAnnonceList(page) {
    var url = "ServletAnnoncePaginate";
    var xhr = new XMLHttpRequest();

    
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onload = function (e) {
        if (this.status === 200) {
            console.log(document.querySelector("#list_results").innerHTML);
            document.querySelector("#list_results").innerHTML = this.responseText;
        }
    };

    var data = "page="+page+"&pageSize=10"; 
    form = document.querySelector("#annonceSearchForm");
    if(form !== undefined){
        data += "&titre="+form.querySelector('input[name="titre"]').value;
        var campI = form.querySelector("#campusId").selectedIndex;
        data += "&campusId="+form.querySelector("#campusId").options[campI].value;
        data += "&prixMin="+form.querySelector('input[name="prixMin"]').value;
        data += "&prixMax="+form.querySelector('input[name="prixMax"]').value;
        data += "&type="+form.querySelector('input[name="type"]:checked').value;        
        var catI = form.querySelector("#categorie").selectedIndex;
        data += "&categorie="+form.querySelector("#categorie").options[catI].value;
        data += "&photo="+form.querySelector('input[name="photo"]').checked;
    }

    xhr.send(data);
    
    return false;
}
