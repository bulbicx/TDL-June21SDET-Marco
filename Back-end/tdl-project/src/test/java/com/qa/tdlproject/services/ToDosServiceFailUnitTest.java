package com.qa.tdlproject.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdlproject.models.ToDos;
import com.qa.tdlproject.repositories.ToDosRepository;

@SpringBootTest
public class ToDosServiceFailUnitTest {

	@MockBean
	private ToDosRepository repo;
	
	@Autowired
	private ToDosService service;
	
	@Test
	void testGetOneToDoById() {
		//When
		Mockito.when(this.repo.findById(1L)).thenThrow(new EntityNotFoundException());
		
		//Then
		assertThatThrownBy(() -> this.service.getOneToDoById(1L))
				.isInstanceOf(EntityNotFoundException.class)
				.hasMessage(null);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	void testUpdateToDo() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertThatThrownBy(() -> this.service.updateToDos(1L, new ToDos("something", false)))
		.isInstanceOf(EntityNotFoundException.class)
		.hasMessage(null);
		
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
	
	@Test
	void testDeleteToDo() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertThatThrownBy(() -> this.service.updateToDos(1L, new ToDos("something", false)))
		.isInstanceOf(EntityNotFoundException.class)
		.hasMessage(null);
		
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
}
