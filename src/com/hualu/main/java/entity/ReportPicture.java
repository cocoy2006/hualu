package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReportPicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "report_picture", catalog = "hualu")
public class ReportPicture implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Timestamp createtime;
	private Timestamp boundtime;
	private Integer type;
	private String url;

	// Constructors

	/** default constructor */
	public ReportPicture() {
	}

	/** minimal constructor */
	public ReportPicture(Integer userid, Timestamp createtime,
			Timestamp boundtime, Integer type) {
		this.userid = userid;
		this.createtime = createtime;
		this.boundtime = boundtime;
		this.type = type;
	}

	/** full constructor */
	public ReportPicture(Integer userid, Timestamp createtime,
			Timestamp boundtime, Integer type, String url) {
		this.userid = userid;
		this.createtime = createtime;
		this.boundtime = boundtime;
		this.type = type;
		this.url = url;
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

	@Column(name = "createtime", nullable = false, length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "boundtime", nullable = false, length = 19)
	public Timestamp getBoundtime() {
		return this.boundtime;
	}

	public void setBoundtime(Timestamp boundtime) {
		this.boundtime = boundtime;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}