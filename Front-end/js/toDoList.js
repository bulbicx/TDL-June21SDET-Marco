(() => {
  let toDoContainer = document.querySelector(".to-do-container");
  let crudSelect = document.querySelector(".form-select");
  
  const displayAllToDos = (data) => {
    for (let i = 0; i < data.length; i++) {
      const el = `
        <input type="checkbox" name=${data[i].id} id=${data[i].id} value=${data[i].id}>
        <label for="todo1">${data[i].title}</label><br>
        `;
      toDoContainer.innerHTML += el;
    }
  }

  const setStyleContainer = () => {
    toDoContainer.classList.remove("empty-container");
  }

  const clearToDoContainer = () => {
    toDoContainer.innerHTML = "";
    toDoContainer.classList.add("empty-container");
  }

  const getOptionValue = () => {
    let value = crudSelect.options[crudSelect.selectedIndex].value;
    switch (value) {
      case "readAll":
        getAllToDos();
        console.log(1);
        break;
      case "readOne":
        console.log(2);
        break;
      case "create":
        console.log(3);
        break;
      case "update":
        console.log(4);
        setStyleContainer();
        break;
      case "delete":
        console.log(5);
        clearToDoContainer();
        break;
      default:
        console.log("error choosing the option");
    }
  }
  
  const getAllToDos = () => {
    fetch("http://localhost:8080/todos")
    .then(response => response.json())
    .then(data => displayAllToDos(data))
    .catch(error => console.error(error));
  }
  
  const getOneToDo = (id) => {
    fetch(`http://localhost:8080/todos/${id}`)
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
  }
  
  const addToDo = (toDo) => {
    fetch("http://localhost:8080/todos", {
      method: 'POST',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify(new ToDo(toDo.title, toDo.completed))
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
  }
  
  const updateToDo = (id, toDo) => {
    fetch(`http://localhost:8080/todos/${id}`, {
      method: 'PUT',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify(new ToDo(toDo.title, toDo.completed))
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
  }
  
  const deleteToDo = (id) => {
    fetch(`http://localhost:8080/todos/${id}`, {
      method: 'DELETE',
      headers: {
        'Content-type': 'application/json'
      }
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
  }
  
  function ToDo(title, completed) {
    this.title = title;
    this.completed = completed;
  }
  
  crudSelect.addEventListener("change", getOptionValue);
  // getAllToDos();
  // getOneToDo(1);
  // deleteToDo(1);
  // updateToDo(1, new ToDo("NO BANANA", true));
  // addToDo(new ToDo("Buy shoes", false));
})();