package com.hualu.main.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.User;

@Service
public class YmRecordDataService {
	
	@Autowired
	private RecordTaskDao recordTaskDao;
	
	@Autowired
	private UserDao userDao;

}
