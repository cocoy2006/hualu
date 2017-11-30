package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DeviceReceive entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "device_receive", catalog = "hualu")
public class DeviceReceive implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer deviceid;
	private Integer userid;
	private Integer nurseid;
	private Timestamp occupationtime;
	private String address;
	private Timestamp boundtime;
	private String memo;
	private Integer status;

	// Constructors

	/** default constructor */
	public DeviceReceive() {
	}

	/** minimal constructor */
	public DeviceReceive(Integer deviceid, Integer nurseid,
			Timestamp occupationtime, Integer status) {
		this.deviceid = deviceid;
		this.nurseid = nurseid;
		this.occupationtime = occupationtime;
		this.status = status;
	}

	/** full constructor */
	public DeviceReceive(Integer deviceid, Integer userid, Integer nurseid,
			Timestamp occupationtime, String address, Timestamp boundtime,
			String memo, Integer status) {
		this.deviceid = deviceid;
		this.userid = userid;
		this.nurseid = nurseid;
		this.occupationtime = occupationtime;
		this.address = address;
		this.boundtime = boundtime;
		this.memo = memo;
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

	@Column(name = "deviceid", nullable = false)
	public Integer getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}

	@Column(name = "userid")
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

	@Column(name = "occupationtime", nullable = false, length = 19)
	public Timestamp getOccupationtime() {
		return this.occupationtime;
	}

	public void setOccupationtime(Timestamp occupationtime) {
		this.occupationtime = occupationtime;
	}

	@Column(name = "address", length = 40)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "boundtime", length = 19)
	public Timestamp getBoundtime() {
		return this.boundtime;
	}

	public void setBoundtime(Timestamp boundtime) {
		this.boundtime = boundtime;
	}

	@Column(name = "memo", length = 10)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}