package com.hualu.main.java.util;

import java.util.List;

public class Page<T> {
	private String sEcho;
	private Integer iTotalRecords;
	private Integer iTotalDisplayRecords;
	private List<T> aaData;

	/**
	 * @return the sEcho
	 */
	public String getsEcho() {
		return sEcho;
	}

	/**
	 * @param sEcho
	 *            the sEcho to set
	 */
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	/**
	 * @return the iTotalRecords
	 */
	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	/**
	 * @param iTotalRecords
	 *            the iTotalRecords to set
	 */
	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	/**
	 * @return the iTotalDisplayRecords
	 */
	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	/**
	 * @param iTotalDisplayRecords
	 *            the iTotalDisplayRecords to set
	 */
	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	/**
	 * @return the aaData
	 */
	public List<T> getAaData() {
		return aaData;
	}

	/**
	 * @param aaData
	 *            the aaData to set
	 */
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}

}
