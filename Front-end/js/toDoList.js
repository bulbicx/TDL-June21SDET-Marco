(() => {
  let toDoContainer = document.querySelector(".to-do-container");
  let crudSelect = document.querySelector(".form-select");
  
  //display form to submit to-do id
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

  //display form to submit a new to-do
  const displayAddForm = () => {
    const form = `
      <section class="text-center">
        <p class="mb-4">Please insert the details of a <strong>new</strong> to do</p>
        <form class="form-todo">
          <div class="col-8 mx-2">
            <input 
              class="form-control" 
              type="text"  
              id="todo-title" 
              placeholder="Title"
            >
          </div>
          <div class="col-4">
            <button class="btn btn-primary">Add new todo</button>
          </div>
        </form>
      </section>
      `;
    toDoContainer.innerHTML = form;

    let btn = document.querySelector(".btn");
    
    btn.addEventListener("click", (e) => {
      e.preventDefault();
      let value = document.querySelector("#todo-title").value;
      toDoContainer.innerHTML = "";
      addToDo(new ToDo(value, false));
    })
  }

  //Displays all todos onto the DOM
  const displayAllToDosResult = (data) => {
    setStyleContainer();
    let ul = document.createElement("ul");
    ul.classList.add("todos");
    toDoContainer.appendChild(ul);
    for (let i = 0; i < data.length; i++) {
      let el;
      if (data[i].completed) {
        el = `<li><del>${ data[i].title }</del></li>`;
      } else {
        el = `<li>${ data[i].title }</li>`;
      }
      ul.innerHTML += el;
    }
  }

  //Displays one todo onto the DOM
  const displayOneToDoResult = (data) => {
    setStyleContainer();
    let ul = document.createElement("ul");
    ul.classList.add("todos");
    toDoContainer.appendChild(ul);
    let el;
    if (data.completed) {
      el = `<li><del>${ data.title }</del></li>`;
    } else {
      el = `<li>${ data.title }</li>`;
    }
    toDoContainer.innerHTML = el;
  }

  //Displays background style
  const setStyleContainer = () => {
    toDoContainer.classList.remove("empty-container");
  }

  //Clear background style and remove any element previosly added
  const clearToDoContainer = () => {
    toDoContainer.innerHTML = "";
    toDoContainer.classList.add("empty-container");
  }

  //Retrieve the value chosen from dropdown and do a CRUD operation
  //accordingly
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
        displayAddForm();
        break;
      case "update":
        break;
      case "delete":
        break;
      default:
        console.log("error choosing the option");
    }
  }
  
  //GET all fetch API
  const getAllToDos = () => {
    fetch("http://localhost:8080/todos")
    .then(response => response.json())
    .then(data => displayAllToDosResult(data))
    .catch(error => console.error(error));
  }
  
  //GET one fetch API
  const getOneToDo = (id) => {
    fetch(`http://localhost:8080/todos/${id}`)
    .then(response => response.json())
    .then(data => displayOneToDoResult(data))
    .catch(error => console.error(error));
  }
  
  //POST fetch API
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
  
  //PUT fetch API
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
  
  //DELETE fetch API
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
})();