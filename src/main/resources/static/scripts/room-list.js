const priceFilterButton = document.getElementById("price-filter");
const locationSelect = document.querySelector("select");
const roomCards = document.querySelectorAll(".room-card");

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
  roomCards.forEach((card) => {
    const city = card.querySelector("#city").textContent;
    card.style.display = city === selectedLocation ? "block" : "none";
  });
}
