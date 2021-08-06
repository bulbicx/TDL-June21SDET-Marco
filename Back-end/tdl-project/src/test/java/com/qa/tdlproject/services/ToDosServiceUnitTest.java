package com.qa.tdlproject.services;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdlproject.models.ToDos;
import com.qa.tdlproject.repositories.ToDosRepository;

@SpringBootTest
class ToDosServiceUnitTest {

	@MockBean
	private ToDosRepository repo;
	
	@Autowired
	private ToDosService service;
	
	@Test
	void testCreateUnit() {
		//Given
		ToDos toDo = new ToDos("Buy strawberries", false);
		ToDos toDoWithId = new ToDos(1L, "Buy strawberries", false);
		
		//When
		Mockito.when(this.repo.saveAndFlush(toDo)).thenReturn(toDoWithId);
		
		//Then
		assertThat(this.service.addToDos(toDo)).isEqualTo(toDoWithId);
		
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(toDo);
	}
	
	@Test
	void testListAllToDos() {
		//Given
		List<ToDos> list = new ArrayList<>();
		ToDos toDo = new ToDos(1L, "Buy strawberries", false);
		ToDos anotherToDo = new ToDos(2L, "Buy apples", false);
		list.add(toDo);
		list.add(anotherToDo);
		
		//When 
		Mockito.when(this.repo.findAll()).thenReturn(list);
		
		//Then
		assertThat(this.service.getAllToDos()).isEqualTo(list);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testGetOneToDoById() {
		//Given
		ToDos toDo = new ToDos(1L, "Buy strawberries", false);
		Optional<ToDos> opt = Optional.of(toDo);
		
		//When
		Mockito.when(this.repo.findById(1L)).thenReturn(opt);
		
		//Then
		assertThat(this.service.getOneToDoById(1L)).isEqualTo(toDo);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	void testUpdateToDo() {
		//Given
		ToDos toDo = new ToDos(1L, "Buy strawberries", false);
		ToDos updatedToDo = new ToDos(1L, "Buy straberries", true);
		
		//When
		Mockito.when(this.repo.existsById(1L)).thenReturn(true);
		Mockito.when(this.repo.getById(1L)).thenReturn(toDo);
		Mockito.when(this.repo.saveAndFlush(toDo)).thenReturn(updatedToDo);
		
		//Then
		assertThat(this.service.updateToDos(1L, toDo)).isEqualTo(updatedToDo);
		
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).getById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(toDo);
	}
	
	@Test
	void testDeleteToDo() {
		//Given
		
		//When
		Mockito.when(this.repo.existsById(1L)).thenReturn(true);
		this.repo.deleteById(1L);
		
		//Then
		assertThat(this.service.deleteToDos(1L)).isEqualTo(1L);
	}
}
