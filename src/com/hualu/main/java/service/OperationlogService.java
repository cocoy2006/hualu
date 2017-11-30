package com.hualu.main.java.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.OperationlogComponent;
import com.hualu.main.java.dao.OperationlogDao;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.entity.Operationlog;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Service
public class OperationlogService {
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private OperationlogDao operationlogDao;
	
//	public void saveOrUpdate(Operationlog operationlog) {
//		operationlogDao.saveOrUpdate(operationlog);
//	}
	
	public void saveOrUpdate(Operator operator, int operationtype, int targetid, int targettype) {
		Operationlog operationlog = new Operationlog();
		operationlog.setOperatorid(operator.getId());
		operationlog.setOperatorrole(operator.getRole());
		operationlog.setOperationtype(operationtype);
		operationlog.setTargetid(targetid);
		operationlog.setTargettype(targettype);
		operationlog.setTime(Hualu.getStandardTimestamp());
		operationlogDao.saveOrUpdate(operationlog);
	}
	
	public List<OperationlogComponent> findAll(String operatorid, String operatorrole, 
			String operationtype, String targettype, String start, String finish) {
		DetachedCriteria dc = DetachedCriteria.forClass(Operationlog.class);
		if(operatorid != null && !"0".equals(operatorid)) {
			dc.add(Restrictions.eq("operatorid", Integer.parseInt(operatorid)));
		}
		if(operatorrole != null && !"0".equals(operatorrole)) {
			dc.add(Restrictions.eq("operatorrole", Integer.parseInt(operatorrole)));
		}
		if(operationtype != null && !"0".equals(operationtype)) {
			dc.add(Restrictions.eq("operationtype", Integer.parseInt(operationtype)));
		}
		if(targettype != null && !"0".equals(targettype)) {
			dc.add(Restrictions.eq("targettype", Integer.parseInt(targettype)));
		}
		if(start != null && !"".equals(start)
				&& finish != null && !"".equals(finish)) {
			dc.add(Restrictions.between("time", Timestamp.valueOf(start), Timestamp.valueOf(finish)));
		}
		List<Operationlog> operationlogs = operationlogDao.findByCriteria(dc);
		if(operationlogs != null && operationlogs.size() > 0) {
			Map<Integer, Operator> operators = new HashMap<Integer, Operator>();
			for(Operator operator : (List<Operator>) operatorDao.findAllIncludingRemoved()) {
				operators.put(operator.getId(), operator);
			}
			List<OperationlogComponent> ocs = new ArrayList<OperationlogComponent>();
			for(int i = operationlogs.size() - 1; i >= 0; i--) {
				Operationlog operationlog = operationlogs.get(i);
				Operator operator = operators.get(operationlog.getOperatorid());
				if(operator.getStatus() != Status.OperatorStatus.REMOVED.getInt()) {
					OperationlogComponent oc = new OperationlogComponent();
					oc.setOperationlog(operationlog);
					oc.setOperator(operator);
					ocs.add(oc);
				}
			}
			return ocs;
		}
		return null;
	}

}
