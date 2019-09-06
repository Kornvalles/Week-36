/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



Array.from(document.getElementsByClassName("mydiv"))
        .forEach(function (div) {
            div.addEventListener("click", function () {
                saySomething(this.id);
            });
        });
function saySomething(someID) {
  document.getElementById("text").innerHTML = "Hi from: " + someID;
}
