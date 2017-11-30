package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Signlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "signlog", catalog = "hualu")
public class Signlog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer operatorid;
	private Timestamp signintime;
	private Timestamp signouttime;
	private Integer onlinetime;
	private String ip;

	// Constructors

	/** default constructor */
	public Signlog() {
	}

	/** minimal constructor */
	public Signlog(Integer operatorid, Timestamp signintime) {
		this.operatorid = operatorid;
		this.signintime = signintime;
	}

	/** full constructor */
	public Signlog(Integer operatorid, Timestamp signintime,
			Timestamp signouttime, Integer onlinetime, String ip) {
		this.operatorid = operatorid;
		this.signintime = signintime;
		this.signouttime = signouttime;
		this.onlinetime = onlinetime;
		this.ip = ip;
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

	@Column(name = "operatorid", nullable = false)
	public Integer getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(Integer operatorid) {
		this.operatorid = operatorid;
	}

	@Column(name = "signintime", nullable = false, length = 19)
	public Timestamp getSignintime() {
		return this.signintime;
	}

	public void setSignintime(Timestamp signintime) {
		this.signintime = signintime;
	}

	@Column(name = "signouttime", length = 19)
	public Timestamp getSignouttime() {
		return this.signouttime;
	}

	public void setSignouttime(Timestamp signouttime) {
		this.signouttime = signouttime;
	}

	@Column(name = "onlinetime")
	public Integer getOnlinetime() {
		return this.onlinetime;
	}

	public void setOnlinetime(Integer onlinetime) {
		this.onlinetime = onlinetime;
	}

	@Column(name = "ip", length = 25)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}