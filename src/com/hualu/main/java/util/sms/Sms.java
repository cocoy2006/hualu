package com.hualu.main.java.util.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.hualu.main.java.util.Constants;
import com.hualu.main.java.util.Status;

public class Sms {
	
	private static final Logger LOG = Logger.getLogger(Sms.class.getName());
	
	public static void main(String[] args) {
		System.out.println(welcome("13811581467"));
	}

	public static String welcome(String phone) {
		String msgcont = Constants.SMS_WELCOME;
		return send(phone, msgcont);
	}
	
	/**
	 * @param phone
	 * @param time x月x日x点x分
	 * @return
	 */
	@Deprecated
	public static String recordReceived(String phone, String name, String time) {
		String msgcont = MessageFormat.format(Constants.SMS_RECORD_RECEIVED, name, time);
		return send(phone, msgcont);
	}
	
	/**
	 * @param phone
	 * @param time x月x日x点x分
	 * @param problem
	 * @return
	 */
	public static String recordProblem(String phone, String name, String time, String problem) {
		String msgcont = MessageFormat.format(Constants.SMS_RECORD_PROBLEM, name, time, problem);
		return send(phone, msgcont);
	}
	
	/**
	 * @param phone
	 * @param time x月x日x点x分
	 * @return
	 */
	public static String recordNormal(String phone, String name, String time) {
		String msgcont = MessageFormat.format(Constants.SMS_RECORD_NORMAL, name, time);
		return send(phone, msgcont);
	}
	
	/**
	 * @param phone
	 * @param name
	 * @param time x月x日x点
	 * @return
	 */
	public static String bookingExamination(String phone, String name, String time) {
		String msgcont = MessageFormat.format(Constants.SMS_BOOKING_EXAMINATION, name, time);
		return send(phone, msgcont);
	}
	
	/**
	 * @param phone
	 * @param name
	 * @param time x月x日x点x分
	 * @return
	 */
	public static String recordRemind(String phone, String name, String time) {
		String msgcont = MessageFormat.format(Constants.SMS_RECORD_REMIND, name, time);
		return send(phone, msgcont);
	}
	
	/**
	 * @param phone
	 * @param name
	 * @param time x月x日x点x分
	 * @return
	 */
	public static String recordRemindDays(String phone, String name) {
		String msgcont = MessageFormat.format(Constants.SMS_RECORD_REMIND_DAYS, name);
		return send(phone, msgcont);
	}
	
	/**
	 * @param phone
	 * @param name
	 * @param time x月x日x点x分
	 * @return
	 */
	@Deprecated
	public static String ecgwaveReceived(String phone, String name, String time) {
		String msgcont = MessageFormat.format(Constants.SMS_ECGWAVE_RECEIVED, name, time);
		return send(phone, msgcont);
	}
	
	/**
	 * @param phone
	 * @param name
	 * @param time x月x日x点x分
	 * @param problem
	 * @return
	 */
	public static String ecgwaveProblem(String phone, String name, String time, String problem) {
		String msgcont = MessageFormat.format(Constants.SMS_ECGWAVE_PROBLEM, name, time, problem);
		return send(phone, msgcont);
	}
	
	/**
	 * @param phone
	 * @param time x月x日x点x分
	 * @param result 血压：116/80；平均压：91；血压脉率：75；脉率：75；呼吸率：12；心率：75；血氧：97
	 * @return
	 */
	public static String dataReceived(String phone, String name, String time, String result) {
		String msgcont = MessageFormat.format(Constants.SMS_DATA_RECEIVED, name, time, result);
		return send(phone, msgcont);
	}
	
	private static final String CPID = "beiyou";
	private static final String CPPWD = "123456";
	private static String send(String phone, String msgcont) {
		StringBuilder sb = null;
		try {
			sb = new StringBuilder("http://hl.my2my.cn/sms/push_mt.jsp?cpid="
					+ CPID + "&cppwd=" + CPPWD + "&phone=" + phone
					+ "&spnumber=" + System.currentTimeMillis() + "&msgcont="
					+ URLEncoder.encode(msgcont + "【华录健康】", "gbk"));
			LOG.info("SMS发送内容 : " + msgcont);
		} catch (UnsupportedEncodingException e) {
			LOG.severe(e.getMessage());
		}
        GetMethod post = new GetMethod(sb.toString());
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        try {
        	client.executeMethod(post);
        	String result = parse(post.getResponseBodyAsString());
        	return result;
        } catch (IOException e) {
        	LOG.severe(e.getMessage());
        } finally {
            post.releaseConnection();
        }
        return String.valueOf(Status.ERROR);
	}
	
	private static String parse(String responseBody) {
		LOG.info("SMS发送结果 : " + responseBody);
		if(responseBody.startsWith("ERROR")) {
			return String.valueOf(Status.ERROR);
		}
		return responseBody;
	}
	
}
