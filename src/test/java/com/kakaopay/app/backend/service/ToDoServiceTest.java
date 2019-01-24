package com.kakaopay.app.backend.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kakaopay.app.backend.exception.ReferenceDataException;
import com.kakaopay.app.backend.model.ToDoList;
import com.kakaopay.app.backend.repository.ToDoListRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ 
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/transaction-context.xml"
})

public class ToDoServiceTest {

	@Autowired
	private ToDoServiceImp apiService;
	
	@Autowired
	private ToDoListRepository toDoListRepository;
	
	
	ToDoList saveToDo;
	
	@Before
	public void setUp() {
		toDoListRepository.deleteAll();
		saveToDo = new ToDoList("할일0", "Y");
	}
	
	//@Test
	@Transactional
	public void insertTest() {
		saveToDo = apiService.insert(saveToDo);
		assertThat(toDoListRepository.count(), is(1L));
	}

	//@Test
	@Transactional
	public void updateTest() {
		saveToDo = apiService.insert(saveToDo);
		saveToDo.setTodo("할일0 수정");;
		saveToDo = apiService.update(saveToDo);
		assertThat(apiService.view(saveToDo.getId()).getTodo(), is("할일0 수정"));
	}
	
	
	//@Test
	@Transactional
	public void completeTest() {
		saveToDo = apiService.insert(saveToDo);
		
		List<ToDoList> saveToDoList = Arrays.asList(
				new ToDoList("할일1", "N"), 
				new ToDoList("할일2", "Y"),
				new ToDoList("할일3", "Y"));
		
		saveToDoList = toDoListRepository.save(saveToDoList);
		assertThat(toDoListRepository.count(), is(4L));
		
		ToDoList toDoList = apiService.view(saveToDoList.get(0).getId());
		toDoList.setComYn("Y");
		toDoList = apiService.complete(saveToDo.getId());
		assertThat(apiService.view(saveToDo.getId()).getComYn(), is("Y"));
	}
	
	@Test
	@Transactional
	public void completeErrorTest() {
		saveToDo.setComYn("N");
		long id = apiService.insert(saveToDo).getId();

		ToDoList tmp = apiService.insert(new ToDoList("체크@" + id, "N"));

		tmp.setComYn("Y");
		saveToDo = apiService.complete(tmp.getId());
		assertThat(saveToDo.getErrorCode(), is("500"));
	}
	
    
	//@Test
	@Transactional
	public void viewTest() {
		saveToDo = apiService.insert(saveToDo);
		ToDoList toDoListUsingId = apiService.view(saveToDo.getId());
		assertThat(toDoListUsingId.getId(), is(saveToDo.getId()));
    
		saveToDo.setComYn("Y");
		saveToDo = apiService.insert(saveToDo);
		assertThat(apiService.view(saveToDo.getId()).getComYn(), is("Y"));
	}
	
	
	//@Test
	@Transactional
	public void deleteTest() {
		saveToDo = apiService.insert(saveToDo);
		apiService.delete(saveToDo.getId());
		assertNull(apiService.view(saveToDo.getId()));
	}
	

}
