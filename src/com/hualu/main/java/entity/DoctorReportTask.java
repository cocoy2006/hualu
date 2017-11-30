package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DoctorReportTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "doctor_report_task", catalog = "hualu")
public class DoctorReportTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer taskid;
	private Integer doctorid;
	private String advice;
	private Timestamp endtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public DoctorReportTask() {
	}

	/** minimal constructor */
	public DoctorReportTask(Integer taskid, Integer doctorid, Integer status) {
		this.taskid = taskid;
		this.doctorid = doctorid;
		this.status = status;
	}

	/** full constructor */
	public DoctorReportTask(Integer taskid, Integer doctorid, String advice,
			Timestamp endtime, Integer status) {
		this.taskid = taskid;
		this.doctorid = doctorid;
		this.advice = advice;
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

}