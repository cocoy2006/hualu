package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Operator entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "operator", catalog = "hualu")
public class Operator implements java.io.Serializable {

	// Fields

	private Integer id;
	private String loginname;
	private String name;
	private Integer genda;
	private String uphotourl;
	private String hospital;
	private String depart;
	private String title;
	private Integer status;
	private Integer isonline;
	private Timestamp createtime;
	private String mobile;
	private String memo;
	private String wechatId;
	private String wechatName;
	private Integer role;
	private String passwd;

	// Constructors

	/** default constructor */
	public Operator() {
	}

	/** minimal constructor */
	public Operator(String loginname, String name, Timestamp createtime,
			Integer role, String passwd) {
		this.loginname = loginname;
		this.name = name;
		this.createtime = createtime;
		this.role = role;
		this.passwd = passwd;
	}

	/** full constructor */
	public Operator(String loginname, String name, Integer genda,
			String uphotourl, String hospital, String depart, String title,
			Integer status, Integer isonline, Timestamp createtime,
			String mobile, String memo, String wechatId, String wechatName,
			Integer role, String passwd) {
		this.loginname = loginname;
		this.name = name;
		this.genda = genda;
		this.uphotourl = uphotourl;
		this.hospital = hospital;
		this.depart = depart;
		this.title = title;
		this.status = status;
		this.isonline = isonline;
		this.createtime = createtime;
		this.mobile = mobile;
		this.memo = memo;
		this.wechatId = wechatId;
		this.wechatName = wechatName;
		this.role = role;
		this.passwd = passwd;
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

	@Column(name = "loginname", nullable = false, length = 20)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "genda")
	public Integer getGenda() {
		return this.genda;
	}

	public void setGenda(Integer genda) {
		this.genda = genda;
	}

	@Column(name = "uphotourl", length = 200)
	public String getUphotourl() {
		return this.uphotourl;
	}

	public void setUphotourl(String uphotourl) {
		this.uphotourl = uphotourl;
	}

	@Column(name = "hospital", length = 40)
	public String getHospital() {
		return this.hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	@Column(name = "depart", length = 40)
	public String getDepart() {
		return this.depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	@Column(name = "title", length = 40)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "isonline")
	public Integer getIsonline() {
		return this.isonline;
	}

	public void setIsonline(Integer isonline) {
		this.isonline = isonline;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "memo", length = 100)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "wechat_id", length = 40)
	public String getWechatId() {
		return this.wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	@Column(name = "wechat_name", length = 40)
	public String getWechatName() {
		return this.wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	@Column(name = "role", nullable = false)
	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Column(name = "passwd", nullable = false, length = 40)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}