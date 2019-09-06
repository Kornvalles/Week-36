/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

Array.from(document.getElementsByClassName("mydiv"))
        .forEach(function (element){
            element.addEventListener("click", function (){
                "hi" + "from: "(this.id);
            });
        });

