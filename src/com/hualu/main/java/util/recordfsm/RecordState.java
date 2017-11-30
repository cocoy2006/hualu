package com.hualu.main.java.util.recordfsm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.util.Status;

@Service
public class RecordState {
	
	protected int id = 0;
	protected int status = Status.RecordTask.INIT.getInt();
	
	@Autowired
	private RecordTaskDao rtDao;

	public void execute(int id) {
	}

	public void draft(int id) {
	}

	public void reject(int id) {
	}

	public void recall(int id) {
	}

	public void setStatus(int id) {
		RecordTask rt = rtDao.findById(id);
		if(rt != null) {
			rt.setStatus(status);
			rtDao.saveOrUpdate(rt);
		}
	}

}
