/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var users = [
    {name: 'Goku'}
];

ul = document.getElementById("users-list");

var render_lists = function (lists) {
    var li = "";
    for (index in lists) {
        li += "<li>" + lists[index] + "</li>";
    }
    ul.innerHTML = li;
    let value = Object.value(li);
    return value;
}

render_lists(users);

// lets filters it
input = document.getElementById('filter_users');

var filterUsers = function (event) {
    event.preventDefault()
    keyword = input.value.toLowerCase();
    filtered_users = users.filter(function (user) {
        user = user.toLowerCase();
        return user.indexOf(keyword) > -1;
    });

    render_lists(filtered_users);
}

input.addEventListener('keyup', filterUsers);



