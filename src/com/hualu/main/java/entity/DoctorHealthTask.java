package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DoctorHealthTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "doctor_health_task", catalog = "hualu")
public class DoctorHealthTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer taskid;
	private Integer doctorid;
	private Timestamp createtime;
	private String advice;
	private Timestamp endtime;
	private Integer status;
	private Integer isdelete;

	// Constructors

	/** default constructor */
	public DoctorHealthTask() {
	}

	/** minimal constructor */
	public DoctorHealthTask(Integer taskid, Integer doctorid,
			Timestamp createtime, Integer status) {
		this.taskid = taskid;
		this.doctorid = doctorid;
		this.createtime = createtime;
		this.status = status;
	}

	/** full constructor */
	public DoctorHealthTask(Integer taskid, Integer doctorid,
			Timestamp createtime, String advice, Timestamp endtime,
			Integer status, Integer isdelete) {
		this.taskid = taskid;
		this.doctorid = doctorid;
		this.createtime = createtime;
		this.advice = advice;
		this.endtime = endtime;
		this.status = status;
		this.isdelete = isdelete;
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

	@Column(name = "taskid", nullable = false)
	public Integer getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	@Column(name = "doctorid", nullable = false)
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

	@Column(name = "advice")
	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	@Column(name = "endtime", length = 19)
	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "isdelete")
	public Integer getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

}