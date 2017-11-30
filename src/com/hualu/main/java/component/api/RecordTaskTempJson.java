package com.hualu.main.java.component.api;

import java.sql.Timestamp;

public class RecordTaskTempJson {
	private Integer id;
	private Integer userid;
	private Timestamp starttime;
	private Timestamp endtime;
	private Timestamp piecestarttime;
	private Timestamp pieceendtime;
	private Integer status;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the userid
	 */
	public Integer getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	/**
	 * @return the starttime
	 */
	public Timestamp getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime
	 *            the starttime to set
	 */
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public Timestamp getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime
	 *            the endtime to set
	 */
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the piecestarttime
	 */
	public Timestamp getPiecestarttime() {
		return piecestarttime;
	}

	/**
	 * @param piecestarttime
	 *            the piecestarttime to set
	 */
	public void setPiecestarttime(Timestamp piecestarttime) {
		this.piecestarttime = piecestarttime;
	}

	/**
	 * @return the pieceendtime
	 */
	public Timestamp getPieceendtime() {
		return pieceendtime;
	}

	/**
	 * @param pieceendtime
	 *            the pieceendtime to set
	 */
	public void setPieceendtime(Timestamp pieceendtime) {
		this.pieceendtime = pieceendtime;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
