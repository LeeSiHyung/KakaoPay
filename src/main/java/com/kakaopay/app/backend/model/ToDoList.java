package com.kakaopay.app.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ToDoList extends ErrorResult{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private String todo;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date regDate;
 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date modDate;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date compDate;
    
    private String comYn = "N";
    
    public ToDoList() {
    	regDate = new Date();
    };
    
    public ToDoList(String todo) {
    	this.todo = todo;
    };
    
    public ToDoList(ErrorResult e) {
    	super();
    	this.errorCode = e.errorCode;
    	this.errorMessage = e.errorMessage;
    };
    
    public ToDoList(String todo, String comYn) {
    	this.todo = todo;
    	this.comYn = comYn;
    	if("Y".equals(comYn)) compDate = new Date();
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getComYn() {
		return comYn;
	}
	public void setComYn(String comYn) {
		if("Y".equals(comYn)) compDate = new Date();
		this.comYn = comYn;
	}
	public Date getCompDate() {
		return compDate;
	}
	public void setCompDate(Date compDate) {
		this.compDate = compDate;
	}
	

}
