const priceFilterButton = document.getElementById("price-filter");
const locationSelect = document.querySelector("select");
const roomCards = document.querySelectorAll(".room-card");

window.onload = function () {
  let urlParams = new URLSearchParams(window.location.search);
  let locationFilter = urlParams.get("location");
  if (locationFilter) {
    locationSelect.value = decodeURIComponent(locationFilter);
    sortByLocation();
  }
};

priceFilterButton.addEventListener("click", () => {
  sortByPrice();
});

locationSelect.addEventListener("change", () => {
  sortByLocation();
});

function sortByPrice() {
  // Convert the NodeList to an array for easier manipulation
  const roomCardsArray = Array.from(roomCards);

  // Sort the room cards based on the price
  roomCardsArray.sort((a, b) => {
    const priceA = parseFloat(a.querySelector("#price").textContent);
    const priceB = parseFloat(b.querySelector("#price").textContent);
    return priceA - priceB;
  });

  // Re-append the sorted room cards to the parent container
  const roomCardsParent = document.querySelector(".room-cards");
  roomCardsArray.forEach((roomCard) => {
    roomCardsParent.appendChild(roomCard);
  });
}

function sortByLocation() {
  const selectedLocation = locationSelect.value;

  // Array of valid locations
  const validLocations = [
    "vancouver",
    "surrey",
    "richmond",
    "burnaby",
    "coquitlam",
    "delta",
    "langley",
    "northVancouver",
    "mapleRidge",
    "newWestminster",
    "portCoquitlam",
    "westVancouver",
    "whiteRock",
    "pittMeadows",
  ];

  // Show all cards if the selected location is not in the list of valid locations
  if (!validLocations.includes(selectedLocation)) {
    roomCards.forEach((card) => {
      card.style.display = "block";
    });
  } else {
    roomCards.forEach((card) => {
      const city = card.querySelector("#city").textContent;
      card.style.display = city === selectedLocation ? "block" : "none";
    });
  }
}

function menuToggle() {
  //user dropdown menu
  const toggleMenu = document.querySelector(".menu");
  toggleMenu.classList.toggle("active");
}
