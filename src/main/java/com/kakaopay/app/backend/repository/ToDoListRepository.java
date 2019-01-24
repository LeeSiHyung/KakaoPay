package com.kakaopay.app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kakaopay.app.backend.model.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository <ToDoList , Long>{
	
	

}
