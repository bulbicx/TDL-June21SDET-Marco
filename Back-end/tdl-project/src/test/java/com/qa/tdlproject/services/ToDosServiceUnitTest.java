package com.qa.tdlproject.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tdlproject.models.ToDos;
import com.qa.tdlproject.repositories.ToDosRepository;

@SpringBootTest
public class ToDosServiceUnitTest {

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
}
