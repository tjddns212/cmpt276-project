// function validateForm_login(){

//     var emailinput = document.getElementsById("email")
//     if (emailinput.value == "") {
//         alert("Email must be filled out");
//         return false;
//     }
//     var passwordinput = document.getElementsById("password")
//     if (passwordinput.value == "") {
//         alert("Password must be filled out");
//         return false;
//     }

//     alert("Incorrect email or password");
//     return false;
// }

// console.log ("BBB")
function validateForm_login() {

    // Check the email
	var email = document.getElementById("email")
	// Empty email
	if (email.value == "") {
		alert("Email must be filled out");
		return false;
	}
	// Invalid email format
	// Reference: https://www.simplilearn.com/tutorials/javascript-tutorial/email-validation-in-javascript
	var emailFormat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
	if (!email.value.match(emailFormat)) {
		alert("Invalid Email Format");
		return false;
	}

    // Check the password
	var password = document.getElementById("password")
	// Empty password
	if (password.value == "") {
		alert("Password must be filled out");
		return false;
	}
}