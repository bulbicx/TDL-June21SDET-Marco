package com.qa.tdlproject.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tdlproject.models.ToDos;
import com.qa.tdlproject.services.ToDosService;

@RestController
@RequestMapping("/todos")
public class ToDosController {

	private ToDosService service;
	
	@Autowired
	public ToDosController(ToDosService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ToDos>> getAllToDos() {
		return new ResponseEntity<List<ToDos>>(this.service.getAllToDos(), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<ToDos>> getToDoListByUserId(@PathVariable("userId") int userId) {
		List<ToDos> toDosList = this.service.getToDoListByUserId(userId);
		return new ResponseEntity<List<ToDos>>(toDosList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ToDos> addToDos(@RequestBody @Valid ToDos toDos) {
		ToDos data = this.service.addToDos(toDos);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(URI.create("localhost:8080/" + data.getTodosId()));
		
		return new ResponseEntity<ToDos>(data, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ToDos> updateToDos(@PathVariable("id") Long id, @RequestBody @Valid ToDos todos) {
		ToDos data = this.service.updateToDos(id, todos);
		return new ResponseEntity<ToDos>(data, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteToDos(@PathVariable("id") Long id) {
		this.service.deleteToDos(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}