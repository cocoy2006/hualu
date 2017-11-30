package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Operationlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "operationlog", catalog = "hualu")
public class Operationlog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer operatorid;
	private Integer operatorrole;
	private Integer operationtype;
	private Integer targetid;
	private Integer targettype;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Operationlog() {
	}

	/** full constructor */
	public Operationlog(Integer operatorid, Integer operatorrole,
			Integer operationtype, Integer targetid, Integer targettype,
			Timestamp time) {
		this.operatorid = operatorid;
		this.operatorrole = operatorrole;
		this.operationtype = operationtype;
		this.targetid = targetid;
		this.targettype = targettype;
		this.time = time;
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

	@Column(name = "operatorrole", nullable = false)
	public Integer getOperatorrole() {
		return this.operatorrole;
	}

	public void setOperatorrole(Integer operatorrole) {
		this.operatorrole = operatorrole;
	}

	@Column(name = "operationtype", nullable = false)
	public Integer getOperationtype() {
		return this.operationtype;
	}

	public void setOperationtype(Integer operationtype) {
		this.operationtype = operationtype;
	}

	@Column(name = "targetid")
	public Integer getTargetid() {
		return this.targetid;
	}

	public void setTargetid(Integer targetid) {
		this.targetid = targetid;
	}

	@Column(name = "targettype", nullable = false)
	public Integer getTargettype() {
		return this.targettype;
	}

	public void setTargettype(Integer targettype) {
		this.targettype = targettype;
	}

	@Column(name = "time", nullable = false, length = 19)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}