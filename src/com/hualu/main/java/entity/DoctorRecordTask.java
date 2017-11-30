package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DoctorRecordTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "doctor_record_task", catalog = "hualu")
public class DoctorRecordTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp createtime;
	private Integer doctorid;
	private Integer taskid;
	private String advice;
	private String rejectadvice;
	private Timestamp endtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public DoctorRecordTask() {
	}

	/** minimal constructor */
	public DoctorRecordTask(Timestamp createtime, Integer doctorid,
			Integer taskid, Integer status) {
		this.createtime = createtime;
		this.doctorid = doctorid;
		this.taskid = taskid;
		this.status = status;
	}

	/** full constructor */
	public DoctorRecordTask(Timestamp createtime, Integer doctorid,
			Integer taskid, String advice, String rejectadvice,
			Timestamp endtime, Integer status) {
		this.createtime = createtime;
		this.doctorid = doctorid;
		this.taskid = taskid;
		this.advice = advice;
		this.rejectadvice = rejectadvice;
		this.endtime = endtime;
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

	@Column(name = "createtime", nullable = false, length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "doctorid", nullable = false)
	public Integer getDoctorid() {
		return this.doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	@Column(name = "taskid", nullable = false)
	public Integer getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	@Column(name = "advice")
	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	@Column(name = "rejectadvice")
	public String getRejectadvice() {
		return this.rejectadvice;
	}

	public void setRejectadvice(String rejectadvice) {
		this.rejectadvice = rejectadvice;
	}

	@Column(name = "endtime", length = 19)
	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}