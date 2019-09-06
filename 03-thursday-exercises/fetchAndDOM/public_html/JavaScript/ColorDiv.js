/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//  Simple DOM manipulation and Event handling
//  Add three divs to an HTML page, each with a unique id

/*  a) When the page initially is loaded all divs should be 
 *  given a colour of your choice
 *  Hints: use document.getElementsByTagName() 
 *  (will return an array of Dom Nodes) and 
 *  element.style.backgroundColor 
 */
function divcolor() {
    let divArray = Array.from(document.getElementsByTagName('div'));
    divArray.forEach(element => {
        element.style.backgroundColor = "blue";
    });
}

/*
 *  b) Add a button, and assign a click handler to the button. 
 *  When the button is clicked each div should be given 
 *  a unique colour.
 *  Hints: use document.getElementById(..) 
 *  to get the individual div’s
 */
function colorChanger() {
    document.getElementById('div1').style.backgroundColor = "green";
    document.getElementById('div2').style.backgroundColor = "blue";
    document.getElementById('div3').style.backgroundColor = "red";
}