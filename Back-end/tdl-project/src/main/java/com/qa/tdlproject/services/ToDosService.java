package com.qa.tdlproject.services;

import java.util.List;

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
	
	//It returns all to-dos related to a list of a user
	public List<ToDos> getToDoListByUserId(int userId) {
		return this.repository.findToDoListByUserId(userId);
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
		
		ToDos updatedToDo = this.repository.saveAndFlush(toDosInDb);
		return updatedToDo;
	}
	
	//It deletes an existing to-do from the database
	public void deleteToDos(Long id) {
		if (!repository.existsById(id)) throw new EntityNotFoundException();
		repository.deleteById(id);
	}
}
