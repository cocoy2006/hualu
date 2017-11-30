package com.hualu.main.java.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.SignlogComponent;
import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.SignlogDao;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Signlog;
import com.hualu.main.java.util.Status;

@Service
public class SignlogService {
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private SignlogDao signlogDao;
	
	public void saveOrUpdate(Signlog signlog) {
		signlogDao.saveOrUpdate(signlog);
	}
	
	/**
	 * @param self current operator
	 * @param role
	 * @param operatorid
	 * @return
	 */
	public List<SignlogComponent> findComponentAll(int self, int role, int operatorid) {
		DetachedCriteria dc = DetachedCriteria.forClass(Operator.class);
		if(operatorid != 0) {
			dc.add(Restrictions.eq("id", operatorid));
		} else if(role != 0) {
			dc.add(Restrictions.eq("role", role));
		} else if(self == Status.OPERATOR_ADMIN) {
			Integer[] roles = {Status.OperatorRole.DM.getInt(), Status.OperatorRole.NURSE.getInt(), 
					Status.OperatorRole.NURSE_LEADER.getInt(), Status.OperatorRole.NURSE_MANAGER.getInt(),
					Status.OperatorRole.DOCTOR.getInt()};
			dc.add(Restrictions.in("role", roles));
		} else if(self == Status.OPERATOR_NURSE_MANAGER) {
			Integer[] roles = {Status.OperatorRole.NURSE.getInt(), Status.OperatorRole.NURSE_LEADER.getInt()};
			dc.add(Restrictions.in("role", roles));
		}
		dc.add(Restrictions.ne("status", Status.OperatorStatus.REMOVED.getInt()));
		dc.setProjection(Projections.property("id"));
		List<Integer> operatorids = operatorDao.findByCriteria(dc);
		if(operatorids != null && operatorids.size() > 0) {
			DetachedCriteria dc1 = DetachedCriteria.forClass(Signlog.class);
			dc1.add(Restrictions.in("operatorid", operatorids));
			List<Signlog> signlogs = signlogDao.findByCriteria(dc1);
			if(signlogs != null && signlogs.size() > 0) {
				List<SignlogComponent> scs = new ArrayList<SignlogComponent>();
				Map<Integer, Operator> operators = new HashMap<Integer, Operator>();
				for(Operator operator : (List<Operator>) operatorDao.findAll()) {
					operators.put(operator.getId(), operator);
				}
				for(int i = signlogs.size() - 1; i >= 0; i--) {
					Signlog signlog = signlogs.get(i);
					SignlogComponent sc = new SignlogComponent();
					sc.setSignlog(signlog);
//					Operator operator = operatorDao.findById(signlog.getOperatorid());
					Operator operator = operators.get(signlog.getOperatorid());
					if(operator != null) {
						sc.setOperator(operator);
					}
					scs.add(sc);
				}
				return scs;
			}
		}
		return null;
	}

}
