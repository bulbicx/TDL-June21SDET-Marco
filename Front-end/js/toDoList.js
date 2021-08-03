(() => {
  let toDoContainer = document.querySelector(".to-do-container");
  let crudSelect = document.querySelector(".form-select");
  

  const displayOneToDoForm = () => {
    const form = `
      <section class="text-center">
        <p class="mb-4">Please insert a <strong>to-do ID</strong>, you would like to retrieve</p>
        <form class="form-todo">
          <div class="col-5 mx-4">
            <input 
              class="form-control" 
              type="number" 
              name="todo-id" 
              id="todo-id" 
              placeholder="To-do ID"
            >
          </div>
          <button class="btn btn-primary">Retrieve</button>
        </form>
      </section>
      `;
    toDoContainer.innerHTML = form;

    let btn = document.querySelector(".btn");
    
    btn.addEventListener("click", (e) => {
      e.preventDefault();
      let idValue = document.querySelector("#todo-id").value;
      toDoContainer.innerHTML = "";
      getOneToDo(idValue);
    })
  }

  const displayAllToDosResult = (data) => {
    setStyleContainer();
    for (let i = 0; i < data.length; i++) {
      let el;
      if (data[i].completed) {
        el = `
          <input
          type="checkbox"
          name=${data[i].todosId } 
          id=${ data[i].todosId } 
          value=${ data[i].todosId }
          checked
        >
        <label for="todo1"><del>${data[i].title}</del></label><br>
        `;
      } else {
        el = `
        <input
          type="checkbox"
          name=${data[i].todosId } 
          id=${ data[i].todosId } 
          value=${ data[i].todosId }
        >
        <label for="todo1">${data[i].title}</label><br>
        `;
      }
      toDoContainer.innerHTML += el;
    }

  }

  const displayOneToDoResult = (data) => {
    setStyleContainer();
    let el;
    if (data.completed) {
       el = ` <label for="todo1"><del>${ data.title }</del></label><br>`;
    } else {
      el = ` <label for="todo1">${ data.title }</label><br>`;
    }
    toDoContainer.innerHTML = el;
  }

  const setStyleContainer = () => {
    toDoContainer.classList.remove("empty-container");
  }

  const clearToDoContainer = () => {
    toDoContainer.innerHTML = "";
    toDoContainer.classList.add("empty-container");
  }

  const getOptionValue = () => {
    clearToDoContainer();
    let value = crudSelect.options[crudSelect.selectedIndex].value;
    switch (value) {
      case "readAll":
        getAllToDos();
        break;
      case "readOne":
        displayOneToDoForm();
        break;
      case "create":
        break;
      case "update":
        break;
      case "delete":
        break;
      default:
        console.log("error choosing the option");
    }
  }
  
  const getAllToDos = () => {
    fetch("http://localhost:8080/todos")
    .then(response => response.json())
    .then(data => displayAllToDosResult(data))
    .catch(error => console.error(error));
  }
  
  const getOneToDo = (id) => {
    fetch(`http://localhost:8080/todos/${id}`)
    .then(response => response.json())
    .then(data => displayOneToDoResult(data))
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