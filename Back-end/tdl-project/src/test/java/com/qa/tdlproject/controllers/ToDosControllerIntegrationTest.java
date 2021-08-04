package com.qa.tdlproject.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tdlproject.models.ToDos;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:sql-schema.sql", "classpath:sql-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ToDosControllerIntegrationTest {

	//Mock Controller and relevevant mappers
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;// Convert requests to JSON
	
	@Test
	void testCreateToDo() throws Exception {
		//Create to do Object
		ToDos toDos = new ToDos("Buy strawberries", false);
		
		//Convert it to a JSON String
		String toDoAsJSON = this.mapper.writeValueAsString(toDos);
		
		//Build mock request
		RequestBuilder mockRequest = 
							post("/todos")
							.contentType(MediaType.APPLICATION_JSON)
							.content(toDoAsJSON);
		
		//Create a to do object which should resemble the saved toDos posted
		ToDos savedToDos = new ToDos(2L, "Buy strawberries", false);
		
		//Convert saved object into JSON String
		String savedToDosAsJSON = this.mapper.writeValueAsString(savedToDos);
		
		//Check if the status is 201(Created)
		ResultMatcher matchStatus = status().isCreated();
		
		//Check if the body is the correct object(toDo)
		ResultMatcher matchBody = content().json(savedToDosAsJSON);
		
		//Build the request
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testListAllToDos() throws Exception {
		//Build Mock request
		RequestBuilder mockRequest = get("/todos");
		
		//Create object which should resemble the created object/s on database
		ToDos savedApples = new ToDos(1L, "Buy apples", false);
		
		//Create a list and add the object
		List<ToDos> list = new ArrayList<>();
		list.add(savedApples);
		
		//Convert list to JSON format
		String listAsJson = this.mapper.writeValueAsString(list);
	
		//Check status code is OK(200)
		ResultMatcher matchStatus = status().isOk();
		
		//Check body contains the list above
		ResultMatcher matchBody = content().json(listAsJson);
		
		//Build the request
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testListOneToDo() throws Exception {
		//Build mock request
		RequestBuilder getMockRequest = get("/todos/1");
		
		//Create an object which resemble the one present on the database
		ToDos savedTodo = new ToDos(1L, "Buy apples", false);
		
		//Convert saved object into JSON
		String savedToDoAsJSON = this.mapper.writeValueAsString(savedTodo);
		
		//Check status code is OK(200)
		ResultMatcher matchStatus = status().isOk();
		
		//Check body contains the related object
		ResultMatcher matchBody = content().json(savedToDoAsJSON);
		
		//Build the request
		this.mock.perform(getMockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testUpdateToDo() throws Exception {
		//Create update object and set to do as completed
		ToDos updatedToDo = new ToDos("Buy apples", true);
		
		//Convert the object into JSON
		String updatedToDoAsJSON = this.mapper.writeValueAsString(updatedToDo);
		
		//Build mock request
		RequestBuilder mockRequest = 
							put("/todos/1")
							.contentType(MediaType.APPLICATION_JSON)
							.content(updatedToDoAsJSON);
		
		//Create an object which will resemble the updated object
		ToDos savedUpdatedToDo = new ToDos(1L, "Buy apples", true);
		
		//Convert savedObjectToDo into JSON
		String savedUpdatedToDoAsJSON = this.mapper.writeValueAsString(savedUpdatedToDo);
		
		//Check status code(200)
		ResultMatcher matchStatus = status().isOk();
		
		//Check body has new updated object
		ResultMatcher matchBody = content().json(savedUpdatedToDoAsJSON);
		
		//Build the request
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testDeleteToDo() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = delete("/todos/1");
		
		//Check status code(200)
		ResultMatcher matchStatus = status().isOk();
		
		//Build the mock request
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
