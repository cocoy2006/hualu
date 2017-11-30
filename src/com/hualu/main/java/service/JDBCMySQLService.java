package com.hualu.main.java.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.jdbc.MySQL;
import com.hualu.test.java.component.Record;
import com.hualu.test.java.util.db.DBMapping;

public class JDBCMySQLService {
	
	private static final Logger LOG = Logger.getLogger(JDBCMySQLService.class.getName());
	
	public static Map<String, DBMapping> loadSortedAll() {
		String sql = "SELECT dstr, rdatetime, id FROM ym_record_data_".concat(Hualu.getToday())
			.concat(" ORDER BY dstr ASC, rdatetime ASC");
		try {
			ResultSet rs = MySQL.executeQuery(sql);
			Map<String, DBMapping> map = new HashMap<String, DBMapping>();
			DateFormat format = new SimpleDateFormat("HHmmss");
			Timestamp previousDatetime = null;
			String previousDstr = null, summaryId = null;
			while(rs.next()) {
				String dstr = rs.getString(1);
				Timestamp rdatetime = rs.getTimestamp(2);
				int id = rs.getInt(3);
				if(previousDstr != null && previousDstr.equals(dstr) && !Hualu.isNext(rdatetime, previousDatetime)) { // don't need to
					Object[] object = new Object[2];
					object[0] = rdatetime;
					object[1] = id;
					map.get(summaryId).getRecords().add(object);
				} else { // go to next summary
					DBMapping mapping = new DBMapping();
					// set dstr
					mapping.setDstr(dstr);
					// set rdatetime
					mapping.setRdatetime(rdatetime);
					// set records -- start
					List<Object[]> records = new ArrayList<Object[]>();
					Object[] object = new Object[2];
					object[0] = rdatetime;
					object[1] = id;
					records.add(object);
					mapping.setRecords(records);
					// set records -- end
					summaryId = dstr.concat(format.format(rdatetime));
					map.put(summaryId, mapping);
				}
				previousDatetime = rdatetime;
				previousDstr = dstr;
			}
			return map;
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	public static String findIdsOfProcessedRecordTask(String day) throws SQLException {
		String start = day.concat(" 00:00:00"), end = day.concat(" 23:59:59");
		String sql = "SELECT ids from record_task WHERE starttime BETWEEN '" + 
			Timestamp.valueOf(start) + "' AND '" + Timestamp.valueOf(end) + "'";
		ResultSet rs = MySQL.executeQuery(sql);
		StringBuffer sb = new StringBuffer();
		while(rs.next()) {
			sb.append(rs.getString("ids"));
		}
		sb.append("0");
		return sb.toString();
	}
	
	public static List<Record> findRecordTask(String day, String ids) throws Exception {
		String[] idss = ids.split(",");
		int length = idss.length;
		String[] sqls = new String[length];
		for(int i = 0; i < length; i++) {
			sqls[i] = "SELECT id,rdatetime,HEX(ecgwave1),heartrate,breathrate,spo2value,pluserate,bphigh,bplow,bpavg,bprate,temp1 FROM ym_record_data_"
				.concat(day).concat(" WHERE id=").concat(idss[i]);
		}
		return MySQL.executeBulkQuery(sqls);
	}

}
