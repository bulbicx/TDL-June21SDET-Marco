package com.qa.tdlproject.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.tdlproject.models.ToDos;
import com.qa.tdlproject.repositories.ToDosRepository;

@Service
public class ToDosService {
	
	private ToDosRepository repository;
	
	@Autowired
	public ToDosService(ToDosRepository repository) {
		this.repository = repository;
	}
	
	//It will return all the to-dos present in the database
	public List<ToDos> getAllToDos() {
		return this.repository.findAll();
	}
	
	//It returns one to-dos based on id provided
	public ToDos getOneToDoById(Long id) {
		Optional<ToDos> opt = repository.findById(id);
		return opt.orElseThrow(() -> new EntityNotFoundException());
	}
	
	//It adds a new to-do to the database
	public ToDos addToDos(ToDos toDos) {
		return this.repository.saveAndFlush(toDos);
	}
	
	//It updates an existing to-do from the database
	public ToDos updateToDos(Long id, ToDos toDos) {
		if (!repository.existsById(id)) throw new EntityNotFoundException();
		ToDos toDosInDb = repository.getById(id);
		
		toDosInDb.setTitle(toDos.getTitle());
		toDosInDb.setCompleted(toDos.isCompleted());
		
		return this.repository.saveAndFlush(toDosInDb);
	}
	
	//It deletes an existing to-do from the database
	public Long deleteToDos(Long id) {
		if (!repository.existsById(id)) throw new EntityNotFoundException();
		repository.deleteById(id);
		return id;
	}
}
