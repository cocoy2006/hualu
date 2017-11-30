package com.hualu.main.java.component.api;

import java.util.List;

public class RecordTaskJson {
	private Integer id;
	private Integer userid;
	private String starttime;
	private String endtime;
	private String piecestarttime;
	private String pieceendtime;
	private Float heartrate;
	private Integer heartrateresult;
	private Float breathrate;
	private Integer breathrateresult;
	private Float spo2value;
	private Integer spo2valueresult;
	private Float pluserate;
	private Integer pluserateresult;
	private Float bphigh;
	private Float bplow;
	private Float bpavg;
	private Integer bpresult;
	private Float bprate;
	private Integer bprateresult;
	private Float temp1;
	private Integer temp1result;
	private List<String> ecgwaveData;
	private Integer hour;
	private NurseRecordTaskJson nurserecordtask;
	private DoctorRecordTaskJson doctorrecordtask;
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
	public String getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime
	 *            the starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime
	 *            the endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the piecestarttime
	 */
	public String getPiecestarttime() {
		return piecestarttime;
	}

	/**
	 * @param piecestarttime
	 *            the piecestarttime to set
	 */
	public void setPiecestarttime(String piecestarttime) {
		this.piecestarttime = piecestarttime;
	}

	/**
	 * @return the pieceendtime
	 */
	public String getPieceendtime() {
		return pieceendtime;
	}

	/**
	 * @param pieceendtime
	 *            the pieceendtime to set
	 */
	public void setPieceendtime(String pieceendtime) {
		this.pieceendtime = pieceendtime;
	}

	/**
	 * @return the heartrate
	 */
	public Float getHeartrate() {
		return heartrate;
	}

	/**
	 * @param heartrate
	 *            the heartrate to set
	 */
	public void setHeartrate(Float heartrate) {
		this.heartrate = heartrate;
	}

	/**
	 * @return the heartrateresult
	 */
	public Integer getHeartrateresult() {
		return heartrateresult;
	}

	/**
	 * @param heartrateresult
	 *            the heartrateresult to set
	 */
	public void setHeartrateresult(Integer heartrateresult) {
		this.heartrateresult = heartrateresult;
	}

	/**
	 * @return the breathrate
	 */
	public Float getBreathrate() {
		return breathrate;
	}

	/**
	 * @param breathrate
	 *            the breathrate to set
	 */
	public void setBreathrate(Float breathrate) {
		this.breathrate = breathrate;
	}

	/**
	 * @return the breathrateresult
	 */
	public Integer getBreathrateresult() {
		return breathrateresult;
	}

	/**
	 * @param breathrateresult
	 *            the breathrateresult to set
	 */
	public void setBreathrateresult(Integer breathrateresult) {
		this.breathrateresult = breathrateresult;
	}

	/**
	 * @return the spo2value
	 */
	public Float getSpo2value() {
		return spo2value;
	}

	/**
	 * @param spo2value
	 *            the spo2value to set
	 */
	public void setSpo2value(Float spo2value) {
		this.spo2value = spo2value;
	}

	/**
	 * @return the spo2valueresult
	 */
	public Integer getSpo2valueresult() {
		return spo2valueresult;
	}

	/**
	 * @param spo2valueresult
	 *            the spo2valueresult to set
	 */
	public void setSpo2valueresult(Integer spo2valueresult) {
		this.spo2valueresult = spo2valueresult;
	}

	/**
	 * @return the pluserate
	 */
	public Float getPluserate() {
		return pluserate;
	}

	/**
	 * @param pluserate
	 *            the pluserate to set
	 */
	public void setPluserate(Float pluserate) {
		this.pluserate = pluserate;
	}

	/**
	 * @return the pluserateresult
	 */
	public Integer getPluserateresult() {
		return pluserateresult;
	}

	/**
	 * @param pluserateresult
	 *            the pluserateresult to set
	 */
	public void setPluserateresult(Integer pluserateresult) {
		this.pluserateresult = pluserateresult;
	}

	/**
	 * @return the bphigh
	 */
	public Float getBphigh() {
		return bphigh;
	}

	/**
	 * @param bphigh
	 *            the bphigh to set
	 */
	public void setBphigh(Float bphigh) {
		this.bphigh = bphigh;
	}

	/**
	 * @return the bplow
	 */
	public Float getBplow() {
		return bplow;
	}

	/**
	 * @param bplow
	 *            the bplow to set
	 */
	public void setBplow(Float bplow) {
		this.bplow = bplow;
	}

	/**
	 * @return the bpavg
	 */
	public Float getBpavg() {
		return bpavg;
	}

	/**
	 * @param bpavg
	 *            the bpavg to set
	 */
	public void setBpavg(Float bpavg) {
		this.bpavg = bpavg;
	}

	/**
	 * @return the bpresult
	 */
	public Integer getBpresult() {
		return bpresult;
	}

	/**
	 * @param bpresult
	 *            the bpresult to set
	 */
	public void setBpresult(Integer bpresult) {
		this.bpresult = bpresult;
	}

	/**
	 * @return the bprate
	 */
	public Float getBprate() {
		return bprate;
	}

	/**
	 * @param bprate
	 *            the bprate to set
	 */
	public void setBprate(Float bprate) {
		this.bprate = bprate;
	}

	/**
	 * @return the bprateresult
	 */
	public Integer getBprateresult() {
		return bprateresult;
	}

	/**
	 * @param bprateresult
	 *            the bprateresult to set
	 */
	public void setBprateresult(Integer bprateresult) {
		this.bprateresult = bprateresult;
	}

	/**
	 * @return the temp1
	 */
	public Float getTemp1() {
		return temp1;
	}

	/**
	 * @param temp1
	 *            the temp1 to set
	 */
	public void setTemp1(Float temp1) {
		this.temp1 = temp1;
	}

	/**
	 * @return the temp1result
	 */
	public Integer getTemp1result() {
		return temp1result;
	}

	/**
	 * @param temp1result
	 *            the temp1result to set
	 */
	public void setTemp1result(Integer temp1result) {
		this.temp1result = temp1result;
	}

	/**
	 * @return the ecgwaveData
	 */
	public List<String> getEcgwaveData() {
		return ecgwaveData;
	}

	/**
	 * @param ecgwaveData
	 *            the ecgwaveData to set
	 */
	public void setEcgwaveData(List<String> ecgwaveData) {
		this.ecgwaveData = ecgwaveData;
	}

	/**
	 * @return the hour
	 */
	public Integer getHour() {
		return hour;
	}

	/**
	 * @param hour
	 *            the hour to set
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
	}

	/**
	 * @return the nurserecordtask
	 */
	public NurseRecordTaskJson getNurserecordtask() {
		return nurserecordtask;
	}

	/**
	 * @param nurserecordtask
	 *            the nurserecordtask to set
	 */
	public void setNurserecordtask(NurseRecordTaskJson nurserecordtask) {
		this.nurserecordtask = nurserecordtask;
	}

	/**
	 * @return the doctorrecordtask
	 */
	public DoctorRecordTaskJson getDoctorrecordtask() {
		return doctorrecordtask;
	}

	/**
	 * @param doctorrecordtask
	 *            the doctorrecordtask to set
	 */
	public void setDoctorrecordtask(DoctorRecordTaskJson doctorrecordtask) {
		this.doctorrecordtask = doctorrecordtask;
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
