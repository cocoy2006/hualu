package com.hualu.main.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.dao.DoctorRecordTaskDao;
import com.hualu.main.java.dao.RecordTaskDao;
import com.hualu.main.java.dao.UserDao;
import com.hualu.main.java.entity.DoctorRecordTask;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.sms.Sms;

@Service
public class SmsService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RecordTaskDao rtDao;
	
	@Autowired
	private DoctorRecordTaskDao drtDao;
	
	@Deprecated
	public String recordReceived(int userid, int rtId) {
		User user = userDao.findById(userid);
		RecordTask rt = rtDao.findById(rtId);
		return Sms.recordReceived(user.getPhone(), user.getName(), Hualu.getSmsDatetime(rt.getEndtime()));
	}
	
	public String recordProblem(int rtId) {
		RecordTask rt = rtDao.findById(rtId);
		DoctorRecordTask drt = drtDao.findByTaskid(rtId);
		User user = userDao.findById(rt.getUserid());
		if(user != null) {
			return Sms.recordProblem(user.getPhone(), user.getName(), Hualu.getSmsDatetime(rt.getEndtime()), drt.getAdvice());
		}
		return null;
	}
	
	public String recordNormal(int rtId) {
		RecordTask rt = rtDao.findById(rtId);
		User user = userDao.findById(rt.getUserid());
		if(user != null) {
			return Sms.recordNormal(user.getPhone(), user.getName(), Hualu.getSmsDatetime(rt.getEndtime()));
		}
		return null;
	}
	
	public RecordTaskComponent openDataReceived(int rtId) {
		RecordTask rt = rtDao.findById(rtId);
		if(rt != null) {
			RecordTaskComponent rtc = new RecordTaskComponent();
			rtc.setRecordTask(rt);
			User user = userDao.findById(rt.getUserid());
			if(user != null) {
				rtc.setUser(user);
			}
			return rtc;
		}
		return null;
	}
	
	public String dataReceived(int rtId, String[] indexs) {
		RecordTask rt = rtDao.findById(rtId);
		String result = dataResult(rt, indexs);
		User user = userDao.findById(rt.getUserid());
		if(user != null) {
			return Sms.dataReceived(user.getPhone(), user.getName(), Hualu.getSmsDatetime(rt.getEndtime()), result);
		}
		return null;
	}
	
	private String dataResult(RecordTask rt, String[] indexs) {
		StringBuffer sb = new StringBuffer();
		for(String index : indexs) {
			if("bpall".equals(index)) {
				sb.append("血压：").append(Hualu.setAccuracy(rt.getBphigh())).append("/").append(Hualu.setAccuracy(rt.getBplow())).append("， ")
					.append("平均压：").append(Hualu.setAccuracy(rt.getBpavg())).append("，");
			} else if("bprate".equals(index)) {
				sb.append("血压脉率：").append(Hualu.setAccuracy(rt.getBprate())).append("，");
			} else if("pluserate".equals(index)) {
				sb.append("脉率：").append(Hualu.setAccuracy(rt.getPluserate())).append("，");
			} else if("breathrate".equals(index)) {
				sb.append("呼吸率：").append(Hualu.setAccuracy(rt.getBreathrate())).append("，");
			} else if("heartrate".equals(index)) {
				sb.append("心率：").append(Hualu.setAccuracy(rt.getHeartrate())).append("，");
			} else if("temp1".equals(index)) {
				sb.append("体温：").append(Hualu.setAccuracy(rt.getTemp1(), 1)).append("，");
			} else if("spo2value".equals(index)) {
				sb.append("血氧：").append(Hualu.setAccuracy(rt.getSpo2value())).append("，");
			} else if("ecgwave1ids".equals(index)) {
				sb.append("您测量的心电图，我们会立刻交给医生分析，在得到结果后第一时间回复您。");
			}
		}
		return sb.toString();
	}
	
}
