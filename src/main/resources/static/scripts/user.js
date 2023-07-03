function validateForm_adduser() {

    var last = document.getElementById("last")
    if (last.value == "") {
      alert("Last Name must be filled out");
      return false;
    }

    var first = document.getElementById("first")
    if (first.value == "") {
      alert("First Name must be filled out");
      return false;
    }

    var email = document.getElementById("email")
    if (email.value == "") {
      alert("Email must be filled out");
      return false;
    }

    var password = document.getElementById("password")
    if (password.value == "") {
      alert("Password must be filled out");
      return false;
    }

    var gender = document.getElementById("gender")
    if (gender.value != "M" && gender.value != "F") {
      alert("Gender must be M or F");
      return false;
    }
      
}