package com.hualu.test.java.util.db;

import java.sql.Timestamp;
import java.util.List;

public class DBMapping {

	private String dstr;
	private Timestamp rdatetime;
	private List<Object[]> records;

	/**
	 * @return the dstr
	 */
	public String getDstr() {
		return dstr;
	}

	/**
	 * @param dstr
	 *            the dstr to set
	 */
	public void setDstr(String dstr) {
		if (this.dstr == null) {
			this.dstr = dstr;
		}
	}

	/**
	 * @return the rdatetime
	 */
	public Timestamp getRdatetime() {
		return rdatetime;
	}

	/**
	 * @param rdatetime
	 *            the rdatetime to set
	 */
	public void setRdatetime(Timestamp rdatetime) {
		if (this.rdatetime == null) {
			this.rdatetime = rdatetime;
		}
	}

	/**
	 * @return the records
	 */
	public List<Object[]> getRecords() {
		return records;
	}

	/**
	 * @param records
	 *            the records to set
	 */
	public void setRecords(List<Object[]> records) {
		this.records = records;
	}

}
