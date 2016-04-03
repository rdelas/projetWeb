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
            document.querySelector("#corps").innerHTML = this.responseText;
        }
    };

    xhr.send("page=" + page + "&lpageSize=10");
    
    return false;
}
