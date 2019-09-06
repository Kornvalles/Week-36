/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


let divlist = document.getElementById("listOutput");

let nameList = ['Lars', 'Jan', 'Peter', 'Bo', 'Frederik']

function makeList() {

    let li = nameList.map(name => "<li>" + name + "</li>");

    li.unshift("<ul>");
    li.push("</ul>");

    divlist.innerHTML = li.join("");
}
makeList();


function addName(event) {
    event.preventDefault()
    let newName = document.getElementById("addName").value;
    nameList.push(newName);
    makeList();
}

document.getElementById("addButton").addEventListener("click", function (event) {
    addName(event);
});

function deleteNameTop(event) {
    event.preventDefault()
    nameList.shift();
    makeList();
}

document.getElementById("deleteFirstButton").addEventListener("click", function (event) {
    deleteNameTop(event);
});

function deleteNameBottom(event) {
    event.preventDefault()
    nameList.pop();
    makeList();
}

document.getElementById("deleteLastButton").addEventListener("click", function (event) {
    deleteNameBottom(event);
});