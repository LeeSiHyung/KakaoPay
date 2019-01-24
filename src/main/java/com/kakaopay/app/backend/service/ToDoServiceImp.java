package com.kakaopay.app.backend.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kakaopay.app.backend.exception.ReferenceDataException;
import com.kakaopay.app.backend.model.ErrorResult;
import com.kakaopay.app.backend.model.ToDoList;
import com.kakaopay.app.backend.repository.ToDoListRepository;
import com.kakaopay.app.backend.utils.StringUtils;

@Service
public class ToDoServiceImp implements ToDoService{
	
	private static final Logger logger = LoggerFactory.getLogger(ToDoServiceImp.class);

	@Autowired
	private ToDoListRepository toDoListRepository;
	
	@Override
	public Page<ToDoList> list(Pageable pageable) {
		return toDoListRepository.findAll(pageable);
	}
	
	@Override
	@Transactional
	public ToDoList insert(ToDoList toDoList) {
		toDoList.setRegDate(new Date());
		return toDoListRepository.save(toDoList);
	}
	
	@Override
	@Transactional
	public ToDoList update(ToDoList toDoList) {
		toDoList.setModDate(new Date());
		return toDoListRepository.save(toDoList);
	}
	
	@Override
	@Transactional
	public ToDoList complete(long id) {
		ToDoList toDoList = toDoListRepository.findOne(id);
		
		List<Long> list = StringUtils.toDoListRefId(toDoList.getTodo());
		if(list.size() > 0) {
			for(Long refId : list) {
				ToDoList ref = toDoListRepository.findOne(refId);
				if(ref != null && "N".equals(ref.getComYn())) {
					return new ToDoList(new ErrorResult("500", "해당 DodoList는 참조가 걸려 있습니다. 완료처리가 불가능합니다."));
				}
			}
		}
		
		toDoList.setComYn("Y");
		return toDoListRepository.save(toDoList);
	}

	@Override
	public ToDoList view(long id) {
		return toDoListRepository.findOne(id);
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		toDoListRepository.delete(id);;
	}

	
	
	
}
