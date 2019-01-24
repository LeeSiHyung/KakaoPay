package com.kakaopay.app.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kakaopay.app.backend.model.ToDoList;

public interface ToDoService {
	public Page<ToDoList> list(Pageable pageable);
	public ToDoList update(ToDoList toDoList);
	public ToDoList insert(ToDoList toDoList);
	public ToDoList view(long id);
	public ToDoList complete(long id);
	public void delete(long id);
}
