package com.kakaopay.app.backend.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.app.backend.model.ToDoList;
import com.kakaopay.app.backend.service.ToDoServiceImp;


public class ToDoControllerTest{
	
	private MockMvc mockMvc;
	
	private ToDoController controller;
	
	String json;
	
	@Before
	public void setup() throws Exception{
		
		ToDoList toDoList = new ToDoList();
		toDoList.setTodo("test");
		toDoList.setId(10L);
        json = new ObjectMapper().writeValueAsString(toDoList);
		
		controller = new ToDoController();
		controller.service = mock(ToDoServiceImp.class);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
	}
	
	@Test
	public void list() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/list.do")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		;
	}
	
	@Test
	public void view() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/view.do?id={var}", 10L)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		;
	}
	
	@Test
	public void insert() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/insert.do")
				.content(json)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		;
	}
	
	@Test
	public void update() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/update.do")
				.content(json)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		;
	}
	
	@Test
	public void complete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/complete.do")
				.content(json)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andDo(print())
		.andExpect(status().isOk())
		;
	}


}
