package com.hualu.main.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DevicePackage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device_package", catalog = "hualu")
public class DevicePackage implements java.io.Serializable {

	// Fields

	private Integer did;
	private String dmodel;
	private String dname;
	private String dip;
	private String dinfo;
	private String dpic;
	private Integer comid;

	// Constructors

	/** default constructor */
	public DevicePackage() {
	}

	/** minimal constructor */
	public DevicePackage(Integer comid) {
		this.comid = comid;
	}

	/** full constructor */
	public DevicePackage(String dmodel, String dname, String dip, String dinfo,
			String dpic, Integer comid) {
		this.dmodel = dmodel;
		this.dname = dname;
		this.dip = dip;
		this.dinfo = dinfo;
		this.dpic = dpic;
		this.comid = comid;
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

	@Column(name = "dmodel", length = 20)
	public String getDmodel() {
		return this.dmodel;
	}

	public void setDmodel(String dmodel) {
		this.dmodel = dmodel;
	}

	@Column(name = "dname", length = 40)
	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	@Column(name = "dip", length = 20)
	public String getDip() {
		return this.dip;
	}

	public void setDip(String dip) {
		this.dip = dip;
	}

	@Column(name = "dinfo", length = 400)
	public String getDinfo() {
		return this.dinfo;
	}

	public void setDinfo(String dinfo) {
		this.dinfo = dinfo;
	}

	@Column(name = "dpic", length = 80)
	public String getDpic() {
		return this.dpic;
	}

	public void setDpic(String dpic) {
		this.dpic = dpic;
	}

	@Column(name = "comid", nullable = false)
	public Integer getComid() {
		return this.comid;
	}

	public void setComid(Integer comid) {
		this.comid = comid;
	}

}