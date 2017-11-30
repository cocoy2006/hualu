package com.hualu.main.java.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RecordDaily entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "record_daily", catalog = "hualu")
public class RecordDaily implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Date date;
	private String ids;
	private String hours;
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

	// Constructors

	/** default constructor */
	public RecordDaily() {
	}

	/** minimal constructor */
	public RecordDaily(Integer userid) {
		this.userid = userid;
	}

	/** full constructor */
	public RecordDaily(Integer userid, Date date, String ids, String hours,
			Float heartrate, Integer heartrateresult, Float breathrate,
			Integer breathrateresult, Float spo2value, Integer spo2valueresult,
			Float pluserate, Integer pluserateresult, Float bphigh,
			Float bplow, Float bpavg, Integer bpresult, Float bprate,
			Integer bprateresult, Float temp1, Integer temp1result) {
		this.userid = userid;
		this.date = date;
		this.ids = ids;
		this.hours = hours;
		this.heartrate = heartrate;
		this.heartrateresult = heartrateresult;
		this.breathrate = breathrate;
		this.breathrateresult = breathrateresult;
		this.spo2value = spo2value;
		this.spo2valueresult = spo2valueresult;
		this.pluserate = pluserate;
		this.pluserateresult = pluserateresult;
		this.bphigh = bphigh;
		this.bplow = bplow;
		this.bpavg = bpavg;
		this.bpresult = bpresult;
		this.bprate = bprate;
		this.bprateresult = bprateresult;
		this.temp1 = temp1;
		this.temp1result = temp1result;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid", nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "ids")
	public String getIds() {
		return this.ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Column(name = "hours")
	public String getHours() {
		return this.hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	@Column(name = "heartrate", precision = 12, scale = 0)
	public Float getHeartrate() {
		return this.heartrate;
	}

	public void setHeartrate(Float heartrate) {
		this.heartrate = heartrate;
	}

	@Column(name = "heartrateresult")
	public Integer getHeartrateresult() {
		return this.heartrateresult;
	}

	public void setHeartrateresult(Integer heartrateresult) {
		this.heartrateresult = heartrateresult;
	}

	@Column(name = "breathrate", precision = 12, scale = 0)
	public Float getBreathrate() {
		return this.breathrate;
	}

	public void setBreathrate(Float breathrate) {
		this.breathrate = breathrate;
	}

	@Column(name = "breathrateresult")
	public Integer getBreathrateresult() {
		return this.breathrateresult;
	}

	public void setBreathrateresult(Integer breathrateresult) {
		this.breathrateresult = breathrateresult;
	}

	@Column(name = "spo2value", precision = 12, scale = 0)
	public Float getSpo2value() {
		return this.spo2value;
	}

	public void setSpo2value(Float spo2value) {
		this.spo2value = spo2value;
	}

	@Column(name = "spo2valueresult")
	public Integer getSpo2valueresult() {
		return this.spo2valueresult;
	}

	public void setSpo2valueresult(Integer spo2valueresult) {
		this.spo2valueresult = spo2valueresult;
	}

	@Column(name = "pluserate", precision = 12, scale = 0)
	public Float getPluserate() {
		return this.pluserate;
	}

	public void setPluserate(Float pluserate) {
		this.pluserate = pluserate;
	}

	@Column(name = "pluserateresult")
	public Integer getPluserateresult() {
		return this.pluserateresult;
	}

	public void setPluserateresult(Integer pluserateresult) {
		this.pluserateresult = pluserateresult;
	}

	@Column(name = "bphigh", precision = 12, scale = 0)
	public Float getBphigh() {
		return this.bphigh;
	}

	public void setBphigh(Float bphigh) {
		this.bphigh = bphigh;
	}

	@Column(name = "bplow", precision = 12, scale = 0)
	public Float getBplow() {
		return this.bplow;
	}

	public void setBplow(Float bplow) {
		this.bplow = bplow;
	}

	@Column(name = "bpavg", precision = 12, scale = 0)
	public Float getBpavg() {
		return this.bpavg;
	}

	public void setBpavg(Float bpavg) {
		this.bpavg = bpavg;
	}

	@Column(name = "bpresult")
	public Integer getBpresult() {
		return this.bpresult;
	}

	public void setBpresult(Integer bpresult) {
		this.bpresult = bpresult;
	}

	@Column(name = "bprate", precision = 12, scale = 0)
	public Float getBprate() {
		return this.bprate;
	}

	public void setBprate(Float bprate) {
		this.bprate = bprate;
	}

	@Column(name = "bprateresult")
	public Integer getBprateresult() {
		return this.bprateresult;
	}

	public void setBprateresult(Integer bprateresult) {
		this.bprateresult = bprateresult;
	}

	@Column(name = "temp1", precision = 12, scale = 0)
	public Float getTemp1() {
		return this.temp1;
	}

	public void setTemp1(Float temp1) {
		this.temp1 = temp1;
	}

	@Column(name = "temp1result")
	public Integer getTemp1result() {
		return this.temp1result;
	}

	public void setTemp1result(Integer temp1result) {
		this.temp1result = temp1result;
	}

}