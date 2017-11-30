package com.hualu.main.java.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hualu.main.java.dao.OperationlogDao;
import com.hualu.main.java.entity.Operationlog;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Service
public class Interceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private OperationlogDao operationlogDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	
		// urls not to be filtered
		String[] noFilters = new String[] { "index","admin","signin","signup","signout","test","api"};
		String uri = request.getRequestURI();
		if(!uri.endsWith("/")) {
			uri = uri.concat("/");
		}
		boolean beFilter = true;
		for (String s : noFilters) {
			if (uri.indexOf(s) != -1) {
				beFilter = false;
				break;
			}
		}
		if (beFilter) {
			Object obj = request.getSession().getAttribute("user");
			if (obj == null) {
				String[] suburis = uri.split("/");
				String subpath = "/";
				if (suburis != null && suburis.length > 1) {
					subpath += suburis[1];
				}
				if(uri.indexOf("dm") > 0 || uri.indexOf("nurse") > 0 
						|| uri.indexOf("nl") > 0 || uri.indexOf("nm") > 0
						|| uri.indexOf("doctor") > 0 || uri.indexOf("manager") > 0) {
					subpath += "/admin/";
				}
				response.sendRedirect(subpath);
				return false;
			} else {
				if(obj != null && obj instanceof Operator) {
					Operator operator = (Operator) obj;
					Integer targetid = null;
					Object targetidObject = request.getSession().getAttribute("targetid");
					if(targetidObject != null) {
						targetid = (Integer) targetidObject;
					}
					Operationlog operationlog = new Operationlog();
					operationlog.setOperatorid(operator.getId());
					operationlog.setOperatorrole(operator.getRole());
					operationlog.setTargetid(targetid);
					operationlog.setTime(Hualu.getStandardTimestamp());
					boolean needlog = false;
					if(uri.indexOf("/device/save/") > 0) {
						operationlog.setTargettype(Status.TargetType.DEVICE.getInt());
						operationlog.setOperationtype(Status.OperationType.INSERT.getInt());
						needlog = true;
					} else if(uri.indexOf("/recordTask/") > 0) {
						operationlog.setTargettype(Status.TargetType.RECORD.getInt());
						if(uri.indexOf("/next/") > 0) {
							operationlog.setOperationtype(Status.OperationType.INSERT.getInt());
						} else if(uri.indexOf("/reject/") > 0) {
							operationlog.setOperationtype(Status.OperationType.REJECT.getInt());
						} else if(uri.indexOf("/recall/") > 0) {
							operationlog.setOperationtype(Status.OperationType.RECALL.getInt());
						} else {
							operationlog.setOperationtype(Status.OperationType.QUERY.getInt());
						}
						needlog = true;
					} else if(uri.indexOf("/healthTask/") > 0) {
						operationlog.setTargettype(Status.TargetType.HEALTH.getInt());
						if(uri.indexOf("/save/") > 0) {
							operationlog.setOperationtype(Status.OperationType.INSERT.getInt());
						} else if(uri.indexOf("/update/") > 0) {
							operationlog.setOperationtype(Status.OperationType.UPDATE.getInt());
						} else {
							operationlog.setOperationtype(Status.OperationType.QUERY.getInt());
						}
						needlog = true;
					} else if(uri.indexOf("/bound/") > 0) {
						operationlog.setTargettype(Status.TargetType.USER.getInt());
						operationlog.setOperationtype(Status.OperationType.BOUND.getInt());
						needlog = true;
					} else if(uri.indexOf("/unbound/") > 0) {
						operationlog.setTargettype(Status.TargetType.USER.getInt());
						operationlog.setOperationtype(Status.OperationType.UNBOUND.getInt());
						needlog = true;
					} else if(uri.indexOf("/remind/remove/") > 0) {
						operationlog.setTargettype(Status.TargetType.REMIND.getInt());
						operationlog.setOperationtype(Status.OperationType.DELETE.getInt());
						needlog = true;
					} else if(uri.indexOf("/plan/remove/") > 0) {
						operationlog.setTargettype(Status.TargetType.PLAN.getInt());
						operationlog.setOperationtype(Status.OperationType.DELETE.getInt());
						needlog = true;
					} else if(uri.indexOf("/reportTask/next/") > 0) {
						operationlog.setTargettype(Status.TargetType.REPORT.getInt());
						operationlog.setOperationtype(Status.OperationType.INSERT.getInt());
						needlog = true;
					}
					if(needlog) {
						operationlogDao.saveOrUpdate(operationlog);
					} else {
						operationlog = null;
					}
				}
				return true;
			}
		}
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav) {}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {}

}
