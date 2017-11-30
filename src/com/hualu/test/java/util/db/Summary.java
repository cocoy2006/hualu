package com.hualu.test.java.util.db;

import java.util.Map;

public class Summary {

	private Map<String, DBMapping> map;
	
//	public Summary() {
//		// load sorted database records and parse them
//		map = JDBCMySQLService.loadSortedAll();
//	}	
	/**
	 * @return the map
	 */
	public Map<String, DBMapping> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, DBMapping> map) {
		this.map = map;
	}
	
}
