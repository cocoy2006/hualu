package com.hualu.main.java.util.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicConverter {

	public static Integer StringToInteger(String s) {
		if(s != null) {
			return Integer.parseInt(s);
		}
		return null;
	}
	
	public static Timestamp StringToTimestamp(String s) {
		if(s != null) {
			return Timestamp.valueOf(s);
		}
		return null;
	}
	
	public static Date StringToDate(String s) throws ParseException {
		if(s != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(s);
		}
		return null;
	}
}
