package com.kakaopay.app.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kakaopay.app.backend.service.ToDoService;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	public ToDoService service;
	
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String list(@PageableDefault(page=0, size=10, sort="id",direction = Sort.Direction.DESC) Pageable pageable, Model model) {
		logger.info("### index page st ###");
		service.list(pageable);
		model.addAttribute("result", service.list(pageable));
		logger.info("### index page ed ###");
		return "index";
	}
	
}
