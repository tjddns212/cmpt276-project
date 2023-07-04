input = document.getElementsByTagName("input")
span = document.getElementsByTagName("span")
td = document.getElementsByTagName("td")
center = document.getElementsByClassName("center")

setInterval(function() {
    width = window.innerWidth

    for (var i = 0; i < center.length; i ++) {
        center[i].style.width = (Number(width) - 16).toString() + "px";
    }

    if (document.getElementById("room").value != 0) {

        for (var i = 0; i < td.length - 1; i ++) {
            td[i].style.width = width / 2 + "px"
        }
        td[td.length - 1].style.width = width + "px"

        for (var i = 0; i < input.length - 1; i ++) {
            input[i].style.width = (parseInt(td[i].style.width, 10) - parseInt(span[i].style.width, 10) - 50).toString() + "px";
        }
        input[input.length - 1].style.width = (parseInt(input[input.length -1].style.width, 10) - 9).toString() + "px"
    }
    else {
        
        for (var i = 0; i < 6; i ++) {
            td[i].style.width = width / 2 + "px"
        }

        for (var i = 0; i < 6; i ++) {
            input[i].style.width = (parseInt(td[i].style.width, 10) - parseInt(span[i].style.width, 10) - 50).toString() + "px";
        }
    }
}, 100);