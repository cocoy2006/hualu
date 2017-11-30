package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Device entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device", catalog = "hualu")
public class Device implements java.io.Serializable {

	// Fields

	private Integer did;
	private String dsn;
	private String sim;
	private String ssid;
	private String passwd;
	private Integer dstatus;
	private Integer packid;
	private Timestamp ddatetime;

	// Constructors

	/** default constructor */
	public Device() {
	}

	/** minimal constructor */
	public Device(String dsn, Integer dstatus, Integer packid) {
		this.dsn = dsn;
		this.dstatus = dstatus;
		this.packid = packid;
	}

	/** full constructor */
	public Device(String dsn, Integer dstatus, Integer packid,
			Timestamp ddatetime) {
		this.dsn = dsn;
		this.dstatus = dstatus;
		this.packid = packid;
		this.ddatetime = ddatetime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "did", unique = true, nullable = false)
	public Integer getDid() {
		return this.did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	@Column(name = "dsn", nullable = false, length = 40)
	public String getDsn() {
		return this.dsn;
	}

	public void setDsn(String dsn) {
		this.dsn = dsn;
	}
	
	@Column(name = "sim", length = 40)
	public String getSim() {
		return this.sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}
	
	@Column(name = "ssid", length = 40)
	public String getSsid() {
		return this.ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	
	@Column(name = "passwd", length = 40)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "dstatus", nullable = false)
	public Integer getDstatus() {
		return this.dstatus;
	}

	public void setDstatus(Integer dstatus) {
		this.dstatus = dstatus;
	}

	@Column(name = "packid", nullable = false)
	public Integer getPackid() {
		return this.packid;
	}

	public void setPackid(Integer packid) {
		this.packid = packid;
	}

	@Column(name = "ddatetime", length = 19)
	public Timestamp getDdatetime() {
		return this.ddatetime;
	}

	public void setDdatetime(Timestamp ddatetime) {
		this.ddatetime = ddatetime;
	}

}