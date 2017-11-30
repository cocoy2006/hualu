package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ManagerRecordTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manager_record_task", catalog = "hualu")
public class ManagerRecordTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp createtime;
	private Integer managerid;
	private Integer taskid;
	private Timestamp endtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public ManagerRecordTask() {
	}

	/** minimal constructor */
	public ManagerRecordTask(Timestamp createtime, Integer managerid,
			Integer taskid) {
		this.createtime = createtime;
		this.managerid = managerid;
		this.taskid = taskid;
	}

	/** full constructor */
	public ManagerRecordTask(Timestamp createtime, Integer managerid,
			Integer taskid, Timestamp endtime, Integer status) {
		this.createtime = createtime;
		this.managerid = managerid;
		this.taskid = taskid;
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

	@Column(name = "managerid", nullable = false)
	public Integer getManagerid() {
		return this.managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

	@Column(name = "taskid", nullable = false)
	public Integer getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
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