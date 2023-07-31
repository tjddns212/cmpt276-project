const nameInput = document.getElementById("roommateName");
const roommatesContainer = document.querySelector(".roommates");
console.log(roommates);
const renderRoommates = () => {
  roommatesContainer.innerHTML = "";
  let roommatesToBeRendered = roommates.filter((roommate) =>
    roommate.first.startsWith(nameInput.value)
  );
  if (nameInput.value == "") {
    roommatesToBeRendered = roommates;
  }
  roommatesToBeRendered.forEach((roommate) => {
    const roommateDiv = document.createElement("div");
    roommateDiv.classList.add("roommate");
    const infoDiv = document.createElement("div");
    infoDiv.classList.add("roommate-info");
    const name = document.createElement("p");
    name.textContent = roommate.first;
    const gender = document.createElement("p");
    gender.textContent = roommate.gender;
    const button = document.createElement("button");
    button.classList.add("btn");
    button.classList.add("btn-secondary");
    button.textContent = "View Profile";
    infoDiv.appendChild(name);
    infoDiv.appendChild(gender);
    roommateDiv.appendChild(infoDiv);
    roommateDiv.appendChild(button);
    roommatesContainer.appendChild(roommateDiv);
  });
  if (roommatesToBeRendered.length == 0) {
    roommatesContainer.innerHTML = "No results :(";
  }
};
renderRoommates();
nameInput.addEventListener("input", renderRoommates);
/*
<div th:each="roommate : ${roommates}" class="roommate">
            <div class="roommate-info">
              <p th:text="${roommate.first}"></p>
              <p th:text="${roommate.gender}"></p>
            </div>
            <button class="btn btn-secondary">View Profile</button>
          </div>
*/
