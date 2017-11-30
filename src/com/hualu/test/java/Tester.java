package com.hualu.test.java;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import com.hualu.main.java.util.Constants;
import com.hualu.main.java.util.Hualu;

public class Tester {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ParseException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws SQLException, ParseException, InstantiationException, IllegalAccessException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String hex = "7f7f";
		byte[] bytes = hex.getBytes();
		String str = new String(bytes);
		int i = Integer.valueOf(str, 16);
		System.out.println(Hualu.setAccuracy(1.164f, 1)); // demo
		System.out.println(Hualu.setAccuracy(null)); // demo
	}
	
	private static String parse(byte b) {
		String result = Integer.toHexString(b & 0xFF);
		if(b >= 0 && b <= 15) {
			result = "0".concat(result);
		}
		return result;
	}

	private static ArrayList<Integer> loadEcgwaveDataArray(String input) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			list.add(Integer.valueOf(input.substring(i, ++i), 16)); // y
		}
		return list;
	}

	private static String rename(String originalFilename, String newFilename) {
		int index = originalFilename.indexOf(".");
		if (index > -1) {
			newFilename = newFilename.concat(originalFilename.substring(index,
					originalFilename.length()));
		}
		return newFilename;
	}

	private static Timestamp getStarttimeOfLastMonth() {
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
	
	private static Timestamp getStarttimeOfLastQuarter() {
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
	
	private static Timestamp getStarttimeOfLastYear() {
		Calendar c = resetCalendar(Calendar.getInstance());
		c.add(Calendar.YEAR, -1);
		c.set(Calendar.MONTH, 0);
		return new Timestamp(c.getTimeInMillis());
	}
	
	private static Timestamp getEndtime() {
		Calendar c = resetCalendar(Calendar.getInstance());
		return new Timestamp(c.getTimeInMillis() - 1);
	}

	private static Calendar resetCalendar(Calendar c) {
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}

}
