package com.hualu.main.java.util.listener;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Service;

import com.hualu.main.java.dao.OperatorDao;
import com.hualu.main.java.dao.SignlogDao;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Signlog;
import com.hualu.main.java.util.Status;

@Service
public class SessionListener implements HttpSessionListener {
	
	private static final Logger LOG = Logger.getLogger(SessionListener.class.getName());
	
	public void sessionCreated(HttpSessionEvent se) {}

	public void sessionDestroyed(HttpSessionEvent se) {
		Object obj = se.getSession().getAttribute("user");
		if(obj != null) {
			Operator operator = (Operator) obj;
//			String sql = "UPDATE operator SET isonline=" + Status.OPERATOR_OFFLINE + 
//				" WHERE id=" + operator.getId();
//			MySQL.executeUpdate(sql);
			OperatorDao.updateIsonline(operator.getId(), Status.OPERATOR_OFFLINE);
			Signlog signlog = SignlogDao.findLast(operator.getId());
			if(signlog != null) {
				long now = System.currentTimeMillis();
				Timestamp signouttime = new Timestamp(now);
				int onlinetime = (int) (now - signlog.getSignintime().getTime());
				SignlogDao.updateSignouttime(signlog.getId(), signouttime, onlinetime);
			}
			LOG.log(Level.INFO, operator.getName() + " SIGNOUT!");
		}
	}

}
