package com.kakaopay.app.front;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kakaopay.app.backend.service.ToDoServiceImp;
import com.kakaopay.app.front.controller.PageController;

public class PageControllerTest{
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		PageController controller = new PageController();
		controller.service = mock(ToDoServiceImp.class);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
	}
	
	@Test
	public void indexTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/index.do"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("index"));
		;
	}

}
