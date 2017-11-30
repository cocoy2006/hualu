package com.hualu.main.java.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hualu.main.java.util.Hualu;
import com.hualu.test.java.component.Record;

public class MySQL {

	private static final String JDBC_DRIVER_NAME = "DRIVER_NAME";
	private static final String DEFAULT_JDBC_DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String JDBC_CONNECTION_URL = "CONNECTION_URL";
	private static final String DEFAULT_JDBC_CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/hualu";
	private static final String JDBC_USERNAME = "USERNAME";
	private static final String DEFAULT_JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "PASSWORD";
	private static final String DEFAULT_JDBC_PASSWORD = "root";
	private static final Logger LOG = Logger.getLogger(MySQL.class.getName());
	
	private static Properties properties = null;
	
	public static ResultSet executeQuery(String sql) {
		Connection con = null; //定义一个MYSQL链接对象
		Statement stmt = null;
		try {
			properties = Hualu.loadJDBCMySQLProperties();
            
            Class.forName(properties.getProperty(JDBC_DRIVER_NAME, DEFAULT_JDBC_DRIVER_NAME)).newInstance(); //MYSQL驱动
            con = DriverManager.getConnection(
            		properties.getProperty(JDBC_CONNECTION_URL, DEFAULT_JDBC_CONNECTION_URL), 
            		properties.getProperty(JDBC_USERNAME, DEFAULT_JDBC_USERNAME), 
            		properties.getProperty(JDBC_PASSWORD, DEFAULT_JDBC_PASSWORD));
            stmt = con.createStatement();
            return stmt.executeQuery(sql);
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, e.getMessage());
        }
		return null;
	}
	
	public static int executeUpdate(String sql) {
		Connection con = null; //定义一个MYSQL链接对象
		Statement stmt = null;
		try {
			properties = Hualu.loadJDBCMySQLProperties();
            
            Class.forName(properties.getProperty(JDBC_DRIVER_NAME, DEFAULT_JDBC_DRIVER_NAME)).newInstance(); //MYSQL驱动
            con = DriverManager.getConnection(
            		properties.getProperty(JDBC_CONNECTION_URL, DEFAULT_JDBC_CONNECTION_URL), 
            		properties.getProperty(JDBC_USERNAME, DEFAULT_JDBC_USERNAME), 
            		properties.getProperty(JDBC_PASSWORD, DEFAULT_JDBC_PASSWORD));
            stmt = con.createStatement();
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, e.getMessage());
        }
		return 0;
	}
	
	public static int executeInsert(String sql) {
		Connection con = null; //定义一个MYSQL链接对象
		Statement stmt = null;
		try {
			properties = Hualu.loadJDBCMySQLProperties();
            
            Class.forName(properties.getProperty(JDBC_DRIVER_NAME, DEFAULT_JDBC_DRIVER_NAME)).newInstance(); //MYSQL驱动
            con = DriverManager.getConnection(
            		properties.getProperty(JDBC_CONNECTION_URL, DEFAULT_JDBC_CONNECTION_URL), 
            		properties.getProperty(JDBC_USERNAME, DEFAULT_JDBC_USERNAME), 
            		properties.getProperty(JDBC_PASSWORD, DEFAULT_JDBC_PASSWORD));
            stmt = con.createStatement();
            int result = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if(result > 0) {
            	ResultSet rs = stmt.getGeneratedKeys();
                int id = 0;
                if(rs.next()) {
                	id = rs.getInt(1);
                }
                return id;
            }
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, e.getMessage());
        }
		return 0;
	}
	
	public static List<Record> executeBulkQuery(String[] sqls) throws Exception {
		Connection con = null; //定义一个MYSQL链接对象
		Statement stmt = null;
//		try {
//			
//        } catch (Exception e) {
//        	LOG.log(Level.SEVERE, e.getMessage());
//        }
//        return null;
		properties = Hualu.loadJDBCMySQLProperties();
        
		Class.forName(properties.getProperty(JDBC_DRIVER_NAME, DEFAULT_JDBC_DRIVER_NAME)).newInstance(); //MYSQL驱动
        con = DriverManager.getConnection(
        		properties.getProperty(JDBC_CONNECTION_URL, DEFAULT_JDBC_CONNECTION_URL), 
        		properties.getProperty(JDBC_USERNAME, DEFAULT_JDBC_USERNAME), 
        		properties.getProperty(JDBC_PASSWORD, DEFAULT_JDBC_PASSWORD));
        stmt = con.createStatement();
        List<Record> records = new ArrayList<Record>();
        for(String sql : sqls) {
        	ResultSet rs = stmt.executeQuery(sql);
        	if(rs.next()) {
        		Record record = new Record();
        		record.setId(rs.getInt("id"));
				record.setRdatetime(rs.getTimestamp("rdatetime"));
				record.setEcgwave1(rs.getString("HEX(ecgwave1)"));
				record.setHeartrate(rs.getObject("heartrate") == null ? null : rs.getInt("heartrate"));
				record.setBreathrate(rs.getObject("breathrate") == null ? null : rs.getInt("breathrate"));
				record.setSpo2value(rs.getObject("spo2value") == null ? null : rs.getInt("spo2value"));
				record.setPluserate(rs.getObject("pluserate") == null ? null : rs.getInt("pluserate"));
				record.setBphigh(rs.getObject("bphigh") == null ? null : rs.getInt("bphigh"));
				record.setBplow(rs.getObject("bplow") == null ? null : rs.getInt("bplow"));
				record.setBprate(rs.getObject("bprate") == null ? null : rs.getInt("bprate"));
				record.setBpavg(rs.getObject("bpavg") == null ? null : rs.getInt("bpavg"));
				record.setTemp1(rs.getObject("temp1") == null ? null : rs.getFloat("temp1"));
				records.add(record);
        	}
        	
        }
        return records;
	}
	
}
