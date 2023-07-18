input = document.getElementsByTagName("input")
span = document.getElementsByTagName("span")
td = document.getElementsByTagName("td")
center = document.getElementsByClassName("center")
var email = document.getElementById("email")
form = document.getElementById("click")
changeButton = document.getElementById("changeButton")
file = document.getElementById("file")

// Reference: https://www.simplilearn.com/tutorials/javascript-tutorial/email-validation-in-javascript
var emailFormat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
if (!email.value.match(emailFormat)) {
    alert("Invalid Email Format");
}

setInterval(function() {
    width = window.innerWidth
    height = window.innerHeight

    for (var i = 0; i < center.length; i ++) {
        center[i].style.width = (Number(width) - 16).toString() + "px";
    }

    for (var i = 0; i < 6; i ++) {
        td[i].style.width = width / 2 + "px"
    }

    for (var i = 0; i < 6; i ++) {
        input[i].style.width = (parseInt(td[i].style.width, 10) - parseInt(span[i].style.width, 10) - 60).toString() + "px";
    }

    form.style.top = (height / 2 - 50).toString() + "px"
    form.style.left = (width / 2 - 200).toString() + "px"

    if (file.files.length > 0) {
        changeButton.style.visibility = "visible"
    }
    if (form.style.visibility == "hidden") {
        changeButton.style.visibility = "hidden";
    }

}, 100);

function click() {
    form.style.visibility = "visible"
    alert("pressed")
    console.log("pressed")
}