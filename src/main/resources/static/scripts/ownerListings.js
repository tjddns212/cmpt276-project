const myModal = document.getElementById("exampleModal");
const deleteForm = document.getElementById("delete-form");
const deleteRoomButtons = document.querySelectorAll(".delete-room-button");
const addIdToModal = (e) => {
  const button = e.currentTarget;
  console.log(button);
  const button_id = button.getAttribute("data-id");
  console.log(button_id);
  myModal.setAttribute("data-id", button_id);
  deleteForm.setAttribute(
    "action",
    `/rooms/delete/${myModal.getAttribute("data-id")}`
  );
};
deleteRoomButtons.forEach((button) => {
  button.addEventListener("click", addIdToModal);
});

console.log(deleteRoomButtons);

///rooms/delete/{uid}
