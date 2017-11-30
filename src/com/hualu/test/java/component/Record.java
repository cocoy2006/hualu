package com.hualu.test.java.component;

import java.sql.Timestamp;
import java.util.Date;

public class Record {

	private Integer id;
	private Integer userid;
	private String dsn;
	private String dstr;
	private String rfilename;
	private Timestamp rcreatetime;
	private Timestamp rdatetime;
	private String ecgwave1;
	private byte[] ecgwave;
	private String breathwave;
	private Integer heartrate;
	private Integer breathrate;
	private String spo2wave;
	private Integer spo2value;
	private Integer pluserate;
	private Integer bphigh;
	private Integer bplow;
	private Integer bpavg;
	private Integer bprate;
	private Float temp1;
	private float temp2;
	private String memo;
	private Date rdate;

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
	 * @return the dsn
	 */
	public String getDsn() {
		return dsn;
	}

	/**
	 * @param dsn
	 *            the dsn to set
	 */
	public void setDsn(String dsn) {
		this.dsn = dsn;
	}

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
		this.dstr = dstr;
	}

	/**
	 * @return the rfilename
	 */
	public String getRfilename() {
		return rfilename;
	}

	/**
	 * @param rfilename
	 *            the rfilename to set
	 */
	public void setRfilename(String rfilename) {
		this.rfilename = rfilename;
	}

	/**
	 * @return the rcreatetime
	 */
	public Timestamp getRcreatetime() {
		return rcreatetime;
	}

	/**
	 * @param rcreatetime
	 *            the rcreatetime to set
	 */
	public void setRcreatetime(Timestamp rcreatetime) {
		this.rcreatetime = rcreatetime;
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
		this.rdatetime = rdatetime;
	}

	/**
	 * @return the ecgwave1
	 */
	public String getEcgwave1() {
		return ecgwave1;
	}

	/**
	 * @param ecgwave1
	 *            the ecgwave1 to set
	 */
	public void setEcgwave1(String ecgwave1) {
		this.ecgwave1 = ecgwave1;
	}

	/**
	 * @return the ecgwave
	 */
	public byte[] getEcgwave() {
		return ecgwave;
	}

	/**
	 * @param ecgwave
	 *            the ecgwave to set
	 */
	public void setEcgwave(byte[] ecgwave) {
		this.ecgwave = ecgwave;
	}

	/**
	 * @return the breathwave
	 */
	public String getBreathwave() {
		return breathwave;
	}

	/**
	 * @param breathwave
	 *            the breathwave to set
	 */
	public void setBreathwave(String breathwave) {
		this.breathwave = breathwave;
	}

	/**
	 * @return the heartrate
	 */
	public Integer getHeartrate() {
		return heartrate;
	}

	/**
	 * @param heartrate
	 *            the heartrate to set
	 */
	public void setHeartrate(Integer heartrate) {
		this.heartrate = heartrate;
	}

	/**
	 * @return the breathrate
	 */
	public Integer getBreathrate() {
		return breathrate;
	}

	/**
	 * @param breathrate
	 *            the breathrate to set
	 */
	public void setBreathrate(Integer breathrate) {
		this.breathrate = breathrate;
	}

	/**
	 * @return the spo2wave
	 */
	public String getSpo2wave() {
		return spo2wave;
	}

	/**
	 * @param spo2wave
	 *            the spo2wave to set
	 */
	public void setSpo2wave(String spo2wave) {
		this.spo2wave = spo2wave;
	}

	/**
	 * @return the spo2value
	 */
	public Integer getSpo2value() {
		return spo2value;
	}

	/**
	 * @param spo2value
	 *            the spo2value to set
	 */
	public void setSpo2value(Integer spo2value) {
		this.spo2value = spo2value;
	}

	/**
	 * @return the pluserate
	 */
	public Integer getPluserate() {
		return pluserate;
	}

	/**
	 * @param pluserate
	 *            the pluserate to set
	 */
	public void setPluserate(Integer pluserate) {
		this.pluserate = pluserate;
	}

	/**
	 * @return the bphigh
	 */
	public Integer getBphigh() {
		return bphigh;
	}

	/**
	 * @param bphigh
	 *            the bphigh to set
	 */
	public void setBphigh(Integer bphigh) {
		this.bphigh = bphigh;
	}

	/**
	 * @return the bplow
	 */
	public Integer getBplow() {
		return bplow;
	}

	/**
	 * @param bplow
	 *            the bplow to set
	 */
	public void setBplow(Integer bplow) {
		this.bplow = bplow;
	}

	/**
	 * @return the bpavg
	 */
	public Integer getBpavg() {
		return bpavg;
	}

	/**
	 * @param bpavg
	 *            the bpavg to set
	 */
	public void setBpavg(Integer bpavg) {
		this.bpavg = bpavg;
	}

	/**
	 * @return the bprate
	 */
	public Integer getBprate() {
		return bprate;
	}

	/**
	 * @param bprate
	 *            the bprate to set
	 */
	public void setBprate(Integer bprate) {
		this.bprate = bprate;
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
	 * @return the temp2
	 */
	public float getTemp2() {
		return temp2;
	}

	/**
	 * @param temp2
	 *            the temp2 to set
	 */
	public void setTemp2(float temp2) {
		this.temp2 = temp2;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the rdate
	 */
	public Date getRdate() {
		return rdate;
	}

	/**
	 * @param rdate
	 *            the rdate to set
	 */
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

}
