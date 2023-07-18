input = document.getElementsByTagName("input")
span = document.getElementsByTagName("span")
td = document.getElementsByTagName("td")
center = document.getElementsByClassName("center")
var email = document.getElementById("email")

// Reference: https://www.simplilearn.com/tutorials/javascript-tutorial/email-validation-in-javascript
var emailFormat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
if (!email.value.match(emailFormat)) {
    alert("Invalid Email Format");
}

setInterval(function() {
    width = window.innerWidth

    for (var i = 0; i < center.length; i ++) {
        center[i].style.width = (Number(width) - 16).toString() + "px";
    }

    for (var i = 0; i < 6; i ++) {
        td[i].style.width = width / 2 + "px"
    }

    for (var i = 0; i < 6; i ++) {
        input[i].style.width = (parseInt(td[i].style.width, 10) - parseInt(span[i].style.width, 10) - 60).toString() + "px";
    }
}, 100);