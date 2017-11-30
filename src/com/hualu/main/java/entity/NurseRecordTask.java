package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NurseRecordTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nurse_record_task", catalog = "hualu")
public class NurseRecordTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp createtime;
	private Integer nurseid;
	private Integer taskid;
	private String advice;
	private String recalladvice;
	private Timestamp endtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public NurseRecordTask() {
	}

	/** minimal constructor */
	public NurseRecordTask(Timestamp createtime, Integer nurseid,
			Integer taskid, Integer status) {
		this.createtime = createtime;
		this.nurseid = nurseid;
		this.taskid = taskid;
		this.status = status;
	}

	/** full constructor */
	public NurseRecordTask(Timestamp createtime, Integer nurseid,
			Integer taskid, String advice, String recalladvice,
			Timestamp endtime, Integer status) {
		this.createtime = createtime;
		this.nurseid = nurseid;
		this.taskid = taskid;
		this.advice = advice;
		this.recalladvice = recalladvice;
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

	@Column(name = "nurseid", nullable = false)
	public Integer getNurseid() {
		return this.nurseid;
	}

	public void setNurseid(Integer nurseid) {
		this.nurseid = nurseid;
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

	@Column(name = "recalladvice")
	public String getRecalladvice() {
		return this.recalladvice;
	}

	public void setRecalladvice(String recalladvice) {
		this.recalladvice = recalladvice;
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