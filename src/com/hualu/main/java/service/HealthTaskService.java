package com.hualu.main.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.HealthTaskComponent;
import com.hualu.main.java.dao.DoctorHealthTaskDao;
import com.hualu.main.java.dao.HealthTaskDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.DoctorHealthTask;
import com.hualu.main.java.entity.HealthTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.User;

@Service
public class HealthTaskService {
	
	@Autowired
	private DoctorHealthTaskDao doctorHealthTaskDao;
	
	@Autowired
	private HealthTaskDao healthTaskDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OperatorDao operatorDao;
	
	public HealthTaskComponent findComponentById(int taskid) {
		HealthTaskComponent htc = new HealthTaskComponent();
		// find health task and fill it
		HealthTask ht = healthTaskDao.findById(taskid);
		htc.setHealthTask(ht);
		// find doctor health task and fill it
		DoctorHealthTask dht = doctorHealthTaskDao.findByTaskid(taskid);
		htc.setDoctorHealthTask(dht);
		// fill user
		User user = userDao.findById(ht.getUserid());
		htc.setUser(user);
		// fill nurse
		Operator nurse = operatorDao.findById(ht.getNurseid());
		htc.setNurse(nurse);
		// fill doctor
		Operator doctor = operatorDao.findById(ht.getDoctorid());
		htc.setDoctor(doctor);
		return htc;
	}

}
