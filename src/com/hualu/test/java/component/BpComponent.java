package com.hualu.test.java.component;

public class BpComponent {

	private String startTime; // format HH:mm:ss
	private String endTime;
	private int bphigh;
	private int bplow;

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the bphigh
	 */
	public int getBphigh() {
		return bphigh;
	}

	/**
	 * @param bphigh
	 *            the bphigh to set
	 */
	public void setBphigh(int bphigh) {
		this.bphigh = bphigh;
	}

	/**
	 * @return the bplow
	 */
	public int getBplow() {
		return bplow;
	}

	/**
	 * @param bplow
	 *            the bplow to set
	 */
	public void setBplow(int bplow) {
		this.bplow = bplow;
	}

}
