
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Exercise 1.


//  Function Declaration
//Observe: no return type, no type on parameters
//function add(n1, n2) {
//    return n1 + n2;
//}
//
////  Function Expression
//var sub = function (n1, n2) {
//    return n1 - n2
//}
//
////  Callback example
//var cb = function (n1, n2, callback) {
//    return "Result from the two numbers: " + n1 + "+" + n2 + "=" + callback(n1, n2);
//};



// Exercise 2.


// 1.
// console.log( add(1,2) )     // What will this print?
// It will print 3

// 2.
//console.log( add )          // What will it print and what does add represent?
// It will print the reference.

// 3.
//console.log( add(1,2,3) ) ; // What will it print
// It will print 3 and neglect the value 3.

// 4.
//console.log( add(1) );	  // What will it print 
// NaN cause n2 is not a number.

// 5.
//console.log( cb(3,3,add) ); // What will it print
// Result from the two numbers: 3+3=6

// 6.
//console.log( cb(4,3,sub) ); // What will it print
//Result from the two numbers: 4+3=1

// 7.
//console.log(cb(3,3,add())); // What will it print (and what was the problem)
// Error, add is missing arguments.

// 8.
//console.log(cb(3,"hh",add));// What will it print
// Result from the two numbers: 3+hh=3hh


// Exercise 3.

//var cb = function (n1, n2, callback) {
//    try{
//    if(n1 === "number")
//        throw new Error('n1 is undefined, or is not a number');
//    return "Result from the two numbers: "+n1+"+"+n2+"="+callback(n1,n2);    
//    } catch (e){
//        console.log(e.name + ': ' + e.message);
//    }     
//};

//var cb = function (n1, n2, callback) {
//    try{
//    if(callback === "function")
//        throw new Error('callback is undefined, or is not a number');
//    return "Result from the two numbers: "+n1+"+"+n2+"="+callback(n1,n2);    
//    } catch (e){
//        console.log(e.name + ': ' + e.message);
//    }     
//};


// Exercise 4 & 5. - works.
//
//function divide(n1, n2) {
//    return n1 / n2;
//}
//
//console.log(cb(3,2,divide));


// ** Getting comfortable with filter, map and forEach: ** \\

//Exercise 1. - works.
//let nameList = ['Lars', 'Jan', 'Peter', 'Bo', 'Frederik']
//const filteredNL = nameList.filter(name => name.length <= 3);
//console.log(filteredNL);


//  Exercise 2. - works.
//let bigName = nameList.map(name => name.toUpperCase());
//console.log(bigName);

//  Exercise 3. works.
//let li = nameList.map(name => "<li>" + name + "</li>");
//li.unshift("<ul>");
//li.push("</ul>");
//console.log(li.join(""));


//  Exercise 4.
//var cars = [
//    {id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000},
//    {id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900},
//    {id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000},
//    {id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799},
//    {id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799}
//];

//  Cars newer than 1999. works.
//const filtCars = cars.filter(car => car.year > 1999);
//console.log(filtCars);

//  All Volvo's. works.
//const filtCars = cars.filter(car => car.make === 'Volvo');
//console.log(filtCars);

//  All cars with a price below 5000. works.
//const filtCars = cars.filter(car => car.price < 5000);
//console.log(filtCars);


//  Exercise 4.
//let sqlString = cars.map(sql => "INSERT INTO cars (id,year,make,sql.model,sql.price) VALUES ("+ sql.id + "," + sql.year+","+ sql.make+","+ sql.model+ ","+ sql.price+");");
//console.log(sqlString);


// ** Asynchronous Callbacks ** \\

// Exercise 1.
// a --> d --> f --> e --> b.
//var msgPrinter = function(msg,delay){
//  setTimeout(function(){
//    console.log(msg);
//  },delay);
//};
//console.log("aaaaaaaaaa");
//msgPrinter ("bbbbbbbbbb",2000);
//console.log("dddddddddd");
//msgPrinter ("eeeeeeeeee",1000);
//console.log("ffffffffff");

// The order was correct.


// ** this and constructor functions ** \\

//  Exercise 1, 2 & 3. - works.
//function Person(name){
//  this.name = name;
//  var self = this;
//  console.log("Name: "+ this.name);
//  setTimeout(function(){
//    console.log("Hi  "+ self.name);  //Explain this
//  },bind(this),2000);
//}
//call it like this (do it, even if you know it’s silly ;-)
//Person("Kurt Wonnegut");  //This calls the function
//console.log("I'm global: "+ name);  //Explain this
/* This calls the Person first, 
 * then prints ("I'm global: "+ name)
 * then it prints ("Hi  "+ this.name) which say Hi undefined
 * because the Person has been referred to in this method.
 */

//var p = new Person("Kurt Wonnegut");  //Create an instance using the constructor function
//console.log("I'm global: "+ name);  //What’s different ?
//  Person is now declared as a variable.


// Exercise 4. - works.
//var greeter = function(){
//  console.log(this.message);
//};
//var comp1 = { message: "Hello World" };
//var comp2 = { message: "Hi" };
//
//var g1 = greeter.bind(comp1 );//We can store a reference, with a specific “this” to use
//var g2 = greeter.bind(comp2 );//And here another “this”
//setTimeout(g1,500);
//setTimeout(g2,1000);


// ** JavaScript Objects ** \\

//  Exercise 1.
//let jsObj = {channel: "Cartoon Network", show: "Ed, Edd 'n' Eddy", seasons: "11", episodes: "76"};
//
//var loop = function (obj) {
//    for (prop in obj) {
//        console.log(prop, obj[prop]);
//    }
//}
//console.log("all 4");
//loopObj(jsObj)
//console.log("No channel");


// Exercise 2.
//let Person {
//
//    constructor(firstName, lastName, age) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.age = age;
//    }
//
//    get details() {
//        for (prop in Person) {
//            console.log(prop, obj[prop]);
//        }
//    }
//}