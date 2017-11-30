package com.hualu.main.java.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.util.Status;

@Service
public class ManagerService {
	
	@Autowired
	private OperatorDao operatorDao;
	
	public List<Operator> findOperatorsByRole(int role) {
		DetachedCriteria dc = DetachedCriteria.forClass(Operator.class);
		if(role != 0) {
			dc.add(Restrictions.eq("role", role));
		} else {
			Integer[] roles = {Status.OperatorRole.DM.getInt(), Status.OperatorRole.NURSE.getInt(), 
					Status.OperatorRole.NURSE_LEADER.getInt(), Status.OperatorRole.NURSE_MANAGER.getInt(),
					Status.OperatorRole.DOCTOR.getInt()};
			dc.add(Restrictions.in("role", roles));
		}
		dc.add(Restrictions.ne("status", Status.OperatorStatus.REMOVED.getInt()));
		return operatorDao.findByCriteria(dc);
	}
	
}