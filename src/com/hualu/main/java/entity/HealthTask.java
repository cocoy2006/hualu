package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HealthTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "health_task", catalog = "hualu")
public class HealthTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer nurseid;
	private Integer doctorid;
	private Timestamp createtime;
	private String disname;
	private String disscript;
	private String donetreat;
	private String desireaid;
	private String cloudrecordid;
	private Integer status;

	// Constructors

	/** default constructor */
	public HealthTask() {
	}

	/** minimal constructor */
	public HealthTask(Integer userid, Integer nurseid, Timestamp createtime,
			Integer status) {
		this.userid = userid;
		this.nurseid = nurseid;
		this.createtime = createtime;
		this.status = status;
	}

	/** full constructor */
	public HealthTask(Integer userid, Integer nurseid, Integer doctorid,
			Timestamp createtime, String disname, String disscript,
			String donetreat, String desireaid, String cloudrecordid,
			Integer status) {
		this.userid = userid;
		this.nurseid = nurseid;
		this.doctorid = doctorid;
		this.createtime = createtime;
		this.disname = disname;
		this.disscript = disscript;
		this.donetreat = donetreat;
		this.desireaid = desireaid;
		this.cloudrecordid = cloudrecordid;
		this.status = status;
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

	@Column(name = "nurseid", nullable = false)
	public Integer getNurseid() {
		return this.nurseid;
	}

	public void setNurseid(Integer nurseid) {
		this.nurseid = nurseid;
	}

	@Column(name = "doctorid")
	public Integer getDoctorid() {
		return this.doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "disname", length = 200)
	public String getDisname() {
		return this.disname;
	}

	public void setDisname(String disname) {
		this.disname = disname;
	}

	@Column(name = "disscript", length = 200)
	public String getDisscript() {
		return this.disscript;
	}

	public void setDisscript(String disscript) {
		this.disscript = disscript;
	}

	@Column(name = "donetreat", length = 200)
	public String getDonetreat() {
		return this.donetreat;
	}

	public void setDonetreat(String donetreat) {
		this.donetreat = donetreat;
	}

	@Column(name = "desireaid", length = 200)
	public String getDesireaid() {
		return this.desireaid;
	}

	public void setDesireaid(String desireaid) {
		this.desireaid = desireaid;
	}

	@Column(name = "cloudrecordid", length = 200)
	public String getCloudrecordid() {
		return this.cloudrecordid;
	}

	public void setCloudrecordid(String cloudrecordid) {
		this.cloudrecordid = cloudrecordid;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}