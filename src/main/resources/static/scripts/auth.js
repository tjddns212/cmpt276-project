let isLoggedIn;

const postButton = document.getElementsByClassName("post-button")[0];
const action = document.querySelector(".action");
const signUpButton = document.querySelector("#sign-up");
const logInButton = document.querySelector("#log-in");

function menuToggle() {
  //user dropdown menu
  const toggleMenu = document.querySelector(".menu");
  toggleMenu.classList.toggle("active");
}

postButton.addEventListener("click", function (event) {
  if (!isLoggedIn) {
    event.preventDefault(); // Prevent the default link behavior

    window.location.href = "login"; // Redirect to the login page
  }
});
document.addEventListener("DOMContentLoaded", function () {
  // Ajax request to check login status
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "/checkLoginStatus");
  xhr.onload = function () {
    if (xhr.status === 200) {
      var response = JSON.parse(xhr.responseText);
      if (response.loggedIn) {
        isLoggedIn = true;
        action.style.display = "inline-block";
        signUpButton.style.display = "none";
        logInButton.style.display = "none";
        console.log("User is logged in");
      } else {
        isLoggedIn = false;
        action.style.display = "none";
        signUpButton.style.display = "inline";
        logInButton.style.display = "inline";
        console.log("User is not logged in");
      }
    } else {
      console.error("Error checking login status. Status code: " + xhr.status);
    }
  };
  xhr.send();
});
