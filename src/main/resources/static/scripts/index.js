let isLoggedIn = checkIfUserIsLoggedIn();

const postButton = document.getElementsByClassName("post-button")[0]; // Get the first element with the class "post-button"

postButton.addEventListener("click", function (event) {
  if (!isLoggedIn) {
    event.preventDefault(); // Prevent the default link behavior

    window.location.href = "login.html"; // Redirect to the login page
  }
});

function checkIfUserIsLoggedIn() {
  return false;
}
