package com.kakaopay.app.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.app.backend.model.ToDoList;
import com.kakaopay.app.backend.service.ToDoService;

@RestController
@RequestMapping("/api/v1/")
public class ToDoController {

	private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@Autowired
	ToDoService service;
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public Page<ToDoList> list(@PageableDefault(page=0, size=10, sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
		logger.info("ApiController list called : " + pageable);
		return service.list(pageable);
	}
	
	@RequestMapping(value = "/view.do", method = RequestMethod.GET)
	public ToDoList view(long id) {
		logger.info("ApiController view called : " + id);
		return service.view(id);
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public ToDoList insert(@RequestBody ToDoList toDoList) {
		logger.info("ApiController insert called : " + toDoList);
		return service.insert(toDoList);
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.PUT)
	public ToDoList update(@RequestBody ToDoList toDoList) {
		logger.info("ApiController update called : " + toDoList);
		return service.update(toDoList);
	}
	
	@RequestMapping(value = "/complete.do", method = RequestMethod.PUT)
	public ToDoList complete(@RequestBody ToDoList toDoList) {
		logger.info("ApiController complete called : " + toDoList);
		return service.complete(toDoList.getId());
	}


}
