(() => {
  let toDoContainer = document.querySelector(".to-do-container");


  const getAllToDos = () => {
    fetch("http://localhost:8080/todos")
      .then(response => response.json())
      .then(data => console.log(data))
      .catch(error => console.error(error));
  }

  getAllToDos();
})();