package com.qa.tdlproject.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tdlproject.models.ToDos;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ToDosControllerIntegrationTest {

	//Mock Controller and relevevant mappers
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;// Convert requests to JSON
	
	@Test
	void testCreate() throws Exception {
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
		ToDos savedToDos = new ToDos(1L, "Buy strawberries", false);
		
		//Convert saved object into JSON String
		String savedToDosAsJSON = this.mapper.writeValueAsString(savedToDos);
		
		//Check if the status is 201(Created)
		ResultMatcher matchStatus = status().isCreated();
		
		//Check if the body is the correct object(toDo)
		ResultMatcher matchBody = content().json(savedToDosAsJSON);
		
		//Build the request
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
}
