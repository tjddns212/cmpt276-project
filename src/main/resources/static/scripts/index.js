const locationInput = document.getElementById("location-input");
locationInput.addEventListener("keydown", function (event) {
  if (event.key === "Enter") {
    let locationValue = encodeURIComponent(locationInput.value.toLowerCase());
    window.location.href = "/rooms/view?location=" + locationValue;
  }
});
