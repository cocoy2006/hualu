package com.hualu.main.java.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class Hualu {
	
	private static final String HUALU_PROPERTIES = "/WEB-INF/classes/hualu.properties";
	private static final String RECORD_PERIOD = "RECORD_PERIOD"; // milliseconds
	private static final String DEFAULT_RECORD_PERIOD = "600000"; // milliseconds
	private static final String JDBC_MYSQL_PROPERTIES = "/WEB-INF/classes/jdbc.mysql.properties";
	private static final Logger LOG = Logger.getLogger(Hualu.class.getName());

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * @category
	 * 	set accuracy of the ecgwave's value */
	public static double setEcgAccuracy(double value) {
		value *= Math.pow(10, Constants.ECGWAVE_SCALE);
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(Constants.ECGWAVE_SCALE, BigDecimal.ROUND_HALF_UP);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}
	
	public static Float setAccuracy(Float f, int degree) {
		int index = (int) Math.pow(10, degree);
		return (float) (Math.round(f * index)) / index;
	}
	
	public static Integer setAccuracy(Float f) {
		if(f != null) {
			return Math.round(f);
		}
		return null;
	}
	
	public static boolean isLegalData(Integer data) {
		return data != null && data != -10 && data != -1;
	}
	
	public static boolean isLegalData(Float data) {
		return data != null && data != -10 && data != -1;
	}
	
	public static int parseIdIndex(int index, int page, int length) {
		return (page - 1) * 8 + (index / length + 1);
	}
	
	public static Float parse(Double d) {
		if(d == null) {
			return null;
		}
		BigDecimal b = new BigDecimal(d);
		return b.floatValue();
	}
	
	public static boolean isZeroEcgwave1(String ecgwave1) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < ecgwave1.length() / 4; i++) {
			sb.append("0080");
		}
		return ecgwave1.equals(sb.toString());
	}
	
	public static String getInfoOfRecordTaskStatus(int status) {
		for(Status.RecordTask rt : Status.RecordTask.values()) {
			if(status == rt.getInt()) {
				return rt.getInfo();
			}
		}
		return null;
	}
	
	public static Integer getLevelOfBp(Float bphigh, Float bplow, Float bpavg) {
		if(bphigh == null || bplow == null || bpavg == null) {
			return null;
		}
		if(bphigh <= 90) {
			return Status.BPLevel.LOW.getInt();
		} else if(bphigh > 90 && bphigh <= 120) {
			return Status.BPLevel.FINE.getInt();
		} else if(bphigh > 120 && bphigh <= 140) {
			return Status.BPLevel.NORMAL.getInt();
		} else if(bphigh > 140 && bphigh <= 159) {
			return Status.BPLevel.MILD.getInt();
		} else if(bphigh > 159 && bphigh <= 179) {
			return Status.BPLevel.MEDIUM.getInt();
		} else if(bphigh > 179 && bphigh <= 209) {
			return Status.BPLevel.SEVERE.getInt();
		} else if(bphigh > 209) {
			return Status.BPLevel.EXTREME.getInt();
		} else {
			return 0;
		}
	}
	
	public static int getHour(Timestamp timestamp) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timestamp.getTime());
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	public static String getToday() {
		try {
			return new SimpleDateFormat("yyyyMMdd").format(new Date());
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	/**
	 * yyyyMMdd
	 * */
	public static String getLiteDate() {
		try {
			return new SimpleDateFormat("yyyyMMdd").format(new Date());
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	/**
	 * yyyyMMdd
	 * */
	public static String getLiteDate(long time) {
		try {
			return new SimpleDateFormat("yyyyMMdd").format(new Date(time));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	/**
	 * yyyy-MM-dd
	 * */
	public static String getStandardDate() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	/**
	 * yyyy-MM-dd
	 * */
	public static String getStandardDate(Date date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	/**
	 * yyyy-MM-dd
	 * */
	public static String getStandardDate(long time) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	/**
	 * yyyy-MM-dd
	 * */
	public static String getStandardYesterdayDate() {
		try {
			long today = new Date().getTime();
			long yesterday = today - 86400000l; // 24hours * 3600seconds * 1000millseconds
			return new SimpleDateFormat("yyyy-MM-dd").format(yesterday);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * */
	public static String getStandardDatetime(Date date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * */
	public static String getStandardDatetime() {
		try {
			return getStandardDatetime(new Date());
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	public static Timestamp getStandardTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Timestamp getStandardTimestamp(String date) {
		return Timestamp.valueOf(date.concat(" 01:00:00"));
	}
	
	/**
	 * x月x日x点x分
	 * */
	public static String getSmsDatetime(Timestamp datetime) {
		try {
			return new SimpleDateFormat("MM月dd日HH点mm分").format(datetime);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			return null;
		}
	}
	
	public static Timestamp getStarttimeOfLastMonth() {
		Calendar c = resetCalendar(Calendar.getInstance());
		int month = c.get(Calendar.MONTH);
		if (month == Calendar.JANUARY) {
			c.add(Calendar.YEAR, -1);
			c.set(Calendar.MONTH, Calendar.DECEMBER);
		} else {
			c.add(Calendar.MONTH, -1);
		}
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static Timestamp getStarttimeOfLastQuarter() {
		Calendar c = resetCalendar(Calendar.getInstance());
		int month = c.get(Calendar.MONTH);
		if (month == Calendar.JANUARY) {
			c.add(Calendar.YEAR, -1);
			c.set(Calendar.MONTH, Calendar.OCTOBER);
		} else {
			c.add(Calendar.MONTH, -3);
		}
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static Timestamp getStarttimeOfLastYear() {
		Calendar c = resetCalendar(Calendar.getInstance());
		c.add(Calendar.YEAR, -1);
		c.set(Calendar.MONTH, 0);
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static Timestamp getEndtime() {
		Calendar c = resetCalendar(Calendar.getInstance());
		return new Timestamp(c.getTimeInMillis() - 1);
	}

	public static Calendar resetCalendar(Calendar c) {
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}
	
	public static int getAge(Date birthday) {
		Calendar cal = Calendar.getInstance();
		int thisYear = cal.get(Calendar.YEAR);
		cal.setTime(birthday);
		int birthYear = cal.get(Calendar.YEAR);
		return thisYear - birthYear;
	}

//	private static int period = 600000;
	public static boolean isNext(Timestamp now, Timestamp previous) {
		int period = loadRecordPeriod();
		if(now.getTime() - previous.getTime() > period) { 
			return true;
		}
		return false;
	}
	
	public static boolean isNext(Timestamp now) {
		int period = loadRecordPeriod();
		if(System.currentTimeMillis() - now.getTime() > period) {
			return true;
		}
		return false;
	}
	
	public static boolean isNext(Timestamp now, Timestamp previous, long period) {
		if(now.getTime() - previous.getTime() > period) { 
			return true;
		}
		return false;
	}
	
	private static Properties hualuProperties = null;	
	private static int loadRecordPeriod() {
		hualuProperties = Hualu.loadHualuProperties();
		return Integer.parseInt(hualuProperties.getProperty(RECORD_PERIOD, DEFAULT_RECORD_PERIOD));
	}
	
	public static String getProperty(String file, String key) {
		try {
			Properties props = loadProperties(file);
			return props.getProperty(key);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	public static String getProperty(InputStream is, String key) {
		try {
			Properties props = loadProperties(is);
			return props.getProperty(key);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	public static Properties loadHualuProperties() {
		try {
			return loadProperties(Hualu.getServletContext().getResourceAsStream(HUALU_PROPERTIES));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	public static Properties loadJDBCMySQLProperties() {
		try {
			return loadProperties(Hualu.getServletContext().getResourceAsStream(JDBC_MYSQL_PROPERTIES));
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	public static Properties loadProperties(String file) {
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			return loadProperties(is);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}

	public static Properties loadProperties(InputStream is) {
		Properties props = new Properties();
		try {
			props.load(is);
			return props;
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
	
	private static ServletContext servletContext;

	public static void setServletContext(ServletContext sc) {
		servletContext = sc;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}
}
