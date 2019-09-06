/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("buttons").addEventListener("click", function () {
    document.getElementById("display").innerHTML += event.target.innerText;
});

document.getElementById("calculate").addEventListener("click", function () {
    event.stopPropagation();
    var display = document.getElementById("display").innerHTML;
    document.getElementById("display").innerHTML = eval(display);
});
