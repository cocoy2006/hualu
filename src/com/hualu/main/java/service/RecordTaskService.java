package com.hualu.main.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.entity.RecordTask;

@Service
public class RecordTaskService {
	
	@Autowired
	private RecordTaskDao recordTaskDao;
	
	public RecordTask findById(int id) {
		return recordTaskDao.findById(id);
	}

}
