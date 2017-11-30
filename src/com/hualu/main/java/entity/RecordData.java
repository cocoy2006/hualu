package com.hualu.main.java.entity;

import java.sql.Timestamp;
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
 * RecordData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "record_data", catalog = "hualu")
public class RecordData implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String dsn;
	private String dstr;
	private String rfilename;
	private Timestamp rcreatetime;
	private Timestamp rdatetime;
	private String ecgwave1;
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
	private Float temp2;
	private String memo;
	private Date rdate;
	private Short isChecked;

	// Constructors

	/** default constructor */
	public RecordData() {
	}

	/** full constructor */
	public RecordData(Integer userid, String dsn, String dstr,
			String rfilename, Timestamp rcreatetime, Timestamp rdatetime,
			String ecgwave1, String breathwave, Integer heartrate,
			Integer breathrate, String spo2wave, Integer spo2value,
			Integer pluserate, Integer bphigh, Integer bplow, Integer bpavg,
			Integer bprate, Float temp1, Float temp2, String memo, Date rdate,
			Short isChecked) {
		this.userid = userid;
		this.dsn = dsn;
		this.dstr = dstr;
		this.rfilename = rfilename;
		this.rcreatetime = rcreatetime;
		this.rdatetime = rdatetime;
		this.ecgwave1 = ecgwave1;
		this.breathwave = breathwave;
		this.heartrate = heartrate;
		this.breathrate = breathrate;
		this.spo2wave = spo2wave;
		this.spo2value = spo2value;
		this.pluserate = pluserate;
		this.bphigh = bphigh;
		this.bplow = bplow;
		this.bpavg = bpavg;
		this.bprate = bprate;
		this.temp1 = temp1;
		this.temp2 = temp2;
		this.memo = memo;
		this.rdate = rdate;
		this.isChecked = isChecked;
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

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "dsn", length = 40)
	public String getDsn() {
		return this.dsn;
	}

	public void setDsn(String dsn) {
		this.dsn = dsn;
	}

	@Column(name = "dstr", length = 11)
	public String getDstr() {
		return this.dstr;
	}

	public void setDstr(String dstr) {
		this.dstr = dstr;
	}

	@Column(name = "rfilename", length = 50)
	public String getRfilename() {
		return this.rfilename;
	}

	public void setRfilename(String rfilename) {
		this.rfilename = rfilename;
	}

	@Column(name = "rcreatetime", length = 19)
	public Timestamp getRcreatetime() {
		return this.rcreatetime;
	}

	public void setRcreatetime(Timestamp rcreatetime) {
		this.rcreatetime = rcreatetime;
	}

	@Column(name = "rdatetime", length = 19)
	public Timestamp getRdatetime() {
		return this.rdatetime;
	}

	public void setRdatetime(Timestamp rdatetime) {
		this.rdatetime = rdatetime;
	}

	@Column(name = "ecgwave1")
	public String getEcgwave1() {
		return this.ecgwave1;
	}

	public void setEcgwave1(String ecgwave1) {
		this.ecgwave1 = ecgwave1;
	}

	@Column(name = "breathwave")
	public String getBreathwave() {
		return this.breathwave;
	}

	public void setBreathwave(String breathwave) {
		this.breathwave = breathwave;
	}

	@Column(name = "heartrate")
	public Integer getHeartrate() {
		return this.heartrate;
	}

	public void setHeartrate(Integer heartrate) {
		this.heartrate = heartrate;
	}

	@Column(name = "breathrate")
	public Integer getBreathrate() {
		return this.breathrate;
	}

	public void setBreathrate(Integer breathrate) {
		this.breathrate = breathrate;
	}

	@Column(name = "spo2wave")
	public String getSpo2wave() {
		return this.spo2wave;
	}

	public void setSpo2wave(String spo2wave) {
		this.spo2wave = spo2wave;
	}

	@Column(name = "spo2value")
	public Integer getSpo2value() {
		return this.spo2value;
	}

	public void setSpo2value(Integer spo2value) {
		this.spo2value = spo2value;
	}

	@Column(name = "pluserate")
	public Integer getPluserate() {
		return this.pluserate;
	}

	public void setPluserate(Integer pluserate) {
		this.pluserate = pluserate;
	}

	@Column(name = "bphigh")
	public Integer getBphigh() {
		return this.bphigh;
	}

	public void setBphigh(Integer bphigh) {
		this.bphigh = bphigh;
	}

	@Column(name = "bplow")
	public Integer getBplow() {
		return this.bplow;
	}

	public void setBplow(Integer bplow) {
		this.bplow = bplow;
	}

	@Column(name = "bpavg")
	public Integer getBpavg() {
		return this.bpavg;
	}

	public void setBpavg(Integer bpavg) {
		this.bpavg = bpavg;
	}

	@Column(name = "bprate")
	public Integer getBprate() {
		return this.bprate;
	}

	public void setBprate(Integer bprate) {
		this.bprate = bprate;
	}

	@Column(name = "temp1", precision = 12, scale = 0)
	public Float getTemp1() {
		return this.temp1;
	}

	public void setTemp1(Float temp1) {
		this.temp1 = temp1;
	}

	@Column(name = "temp2", precision = 12, scale = 0)
	public Float getTemp2() {
		return this.temp2;
	}

	public void setTemp2(Float temp2) {
		this.temp2 = temp2;
	}

	@Column(name = "memo", length = 100)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "rdate", length = 10)
	public Date getRdate() {
		return this.rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	@Column(name = "is_checked")
	public Short getIsChecked() {
		return this.isChecked;
	}

	public void setIsChecked(Short isChecked) {
		this.isChecked = isChecked;
	}

}