(() => {
  let toDoContainer = document.querySelector(".to-do-container");


  const getAllToDos = () => {
    fetch("http://localhost:8080/todos")
      .then(response => response.json())
      .then(data => console.log(data))
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

  getAllToDos();
  deleteToDo(1);
  // updateToDo(1, new ToDo("NO BANANA", true));
  // getOneToDo(1);
  // addToDo(new ToDo("Buy shoes", false));
})();