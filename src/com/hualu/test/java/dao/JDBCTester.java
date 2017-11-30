package com.hualu.test.java.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JDBCTester {

	public static void main(String[] args) {
		System.out.println("time 1 is : " + System.currentTimeMillis());
		exe();
		System.out.println("time 2 is : " + System.currentTimeMillis());
	}
	
	
	
	public static void exe() {
		try {
            Connection con = null; //定义一个MYSQL链接对象
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hualu", "root", "root");
            
            Statement stmt;
            stmt = con.createStatement();
            
//            String start = "2014-09-25 16:58:15";
//            String sql = "SELECT ecgwave1 from ym_record_data_20140925 WHERE rdatetime='" + Timestamp.valueOf(start) + "'"; // Timestamp.valueOf(start)
            String sql = "SELECT HEX(ecgwave1) from ym_record_data_20150104 WHERE id=2091536"; // Timestamp.valueOf(start)
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
            	String s = rs.getString("HEX(ecgwave1)");
            	for(int i = 0; i < s.length() -1000; i += 1000) {
            		System.out.println(s.substring(i, i += 1000));
            	}
//            	Blob blob = rs.getBlob("ecgwave1");
//				StringBuffer sb = new StringBuffer();
//				byte[] bytes = blob.getBytes(1, (int) blob.length());
//                for (byte b : bytes) {  
//                    sb.append(Integer.toHexString(b & 0xFF));
//                }
//                System.out.println(sb.toString());
//            	parseEcgwaveDate();
//                System.out.println(sb.toString());
//                List<ArrayList<Integer>> list = parseEcgwaveDate(sb.toString());
//                for(ArrayList<Integer> intList : list) {
//                	for(int i : intList) {
//                		System.out.print(i);
//                		System.out.print(",");
//                	}
//                	System.out.println();
//                }
            }
            
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }
	}
	
	private static final int base = 150; // screen base/屏幕基准值
	private static final int items = 7; // 7 lines/心电图的条数
	private static List<ArrayList<Integer>> parseEcgwaveDate(String hex) {
		final int step = hex.length() / items; // length = 1200 * 7
		List<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
		int stepIndex = 0, baseIndex = items;
		while(stepIndex < hex.length()) {
			ArrayList<Integer> list = loadEcgwaveDataArray(hex.substring(stepIndex, stepIndex + step));
			for(int dataIndex = 0; dataIndex < list.size(); dataIndex++) {
				int data = list.get(dataIndex);
				data += base * baseIndex;
				list.set(dataIndex, data);
			}
			lists.add(list);
			stepIndex += step;
			baseIndex--;
		}
		return lists;
	}
	
	private static ArrayList<Integer> loadEcgwaveDataArray(String input) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < input.length(); i += 2) {
			list.add(Integer.valueOf(input.substring(i, i + 2), 16)); // y
		}
		return list;
	}
	
}
