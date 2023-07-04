function validateForm_adduser() {

	// Check the first name
	var first = document.getElementById("first")
	// Empty first name
	if (first.value == "") {
		alert("First Name must be filled out");
		return false;
	}

	// Check the last name
	var last = document.getElementById("last")
	// Empty last name
	if (last.value == "") {
		alert("Last Name must be filled out");
		return false;
	}

	// Check the nickname
	var nick = document.getElementById("nick")
	// Empty nickname
	if (nick.value == "") {
		alert("Nickname must be filled out");
		return false;
	}

	// Check the gender
	// Invalid gender
	var gender = document.getElementById("gender")
	if (gender.value != "M" && gender.value != "F" && gender.value != "N") {
		alert("Gender must be M, F or N");
		return false;
	}

	// Check the landlord address
	var landlordAddress = document.getElementById("landlordAddress")
	// Empty landlord address
	if (landlordAddress.value == "") {
		alert("Landlord Address must be filled out");
		return false;
	}

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
	// Invalid password length
	if (password.value.length < 6) {
		alert("Password must be at least 6 characters");
		return false;
	}
}