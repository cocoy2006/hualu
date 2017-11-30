package com.hualu.main.java.entity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "hualu")
public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String loginname;
	private String passwd;
	private String name;
	private String dstr;
	private Timestamp createtime;
	private Integer gender;
	private Date birthday;
	private Integer age;
	private String photourl;
	private Integer userlevel;
	private Integer usergroup;
	private String phone;
	private String email;
	private String homeaddress;
	private Date duetime1;
	private Date duetime2;
	private Integer nurseid;
	private Integer doctorid;
	private String ssnum;
	private String companyname;
	private String companyaddress;
	private String wechatid;
	private String wechatname;
	private Integer status;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String loginname, String passwd, String name,
			Timestamp createtime) {
		this.loginname = loginname;
		this.passwd = passwd;
		this.name = name;
		this.createtime = createtime;
	}

	/** full constructor */
	public User(String loginname, String passwd, String name, String dstr,
			Timestamp createtime, Integer gender, Date birthday, Integer age,
			String photourl, Integer userlevel, Integer usergroup,
			String email, String homeaddress, Date duetime1, Date duetime2,
			Integer nurseid, Integer doctorid, String ssnum,
			String companyname, String companyaddress, String wechatid,
			String wechatname, Integer status) {
		this.loginname = loginname;
		this.passwd = passwd;
		this.name = name;
		this.dstr = dstr;
		this.createtime = createtime;
		this.gender = gender;
		this.birthday = birthday;
		this.age = age;
		this.photourl = photourl;
		this.userlevel = userlevel;
		this.usergroup = usergroup;
		this.email = email;
		this.homeaddress = homeaddress;
		this.duetime1 = duetime1;
		this.duetime2 = duetime2;
		this.nurseid = nurseid;
		this.doctorid = doctorid;
		this.ssnum = ssnum;
		this.companyname = companyname;
		this.companyaddress = companyaddress;
		this.wechatid = wechatid;
		this.wechatname = wechatname;
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

	@Column(name = "loginname", nullable = false, length = 20)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "passwd", nullable = false, length = 20)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "dstr", length = 20)
	public String getDstr() {
		return this.dstr;
	}

	public void setDstr(String dstr) {
		this.dstr = dstr;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "gender")
	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "photourl", length = 200)
	public String getPhotourl() {
		return this.photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	@Column(name = "userlevel")
	public Integer getUserlevel() {
		return this.userlevel;
	}

	public void setUserlevel(Integer userlevel) {
		this.userlevel = userlevel;
	}

	@Column(name = "usergroup")
	public Integer getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(Integer usergroup) {
		this.usergroup = usergroup;
	}
	
	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "homeaddress", length = 200)
	public String getHomeaddress() {
		return this.homeaddress;
	}

	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "duetime1", length = 10)
	public Date getDuetime1() {
		return this.duetime1;
	}

	public void setDuetime1(Date duetime1) {
		this.duetime1 = duetime1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "duetime2", length = 10)
	public Date getDuetime2() {
		return this.duetime2;
	}

	public void setDuetime2(Date duetime2) {
		this.duetime2 = duetime2;
	}

	@Column(name = "nurseid")
	public Integer getNurseid() {
		return this.nurseid;
	}

	public void setNurseid(Integer nurseid) {
		this.nurseid = nurseid;
	}

	@Column(name = "doctorid")
	public Integer getDoctorid() {
		return this.doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	@Column(name = "ssnum", length = 20)
	public String getSsnum() {
		return this.ssnum;
	}

	public void setSsnum(String ssnum) {
		this.ssnum = ssnum;
	}

	@Column(name = "companyname", length = 200)
	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	@Column(name = "companyaddress", length = 200)
	public String getCompanyaddress() {
		return this.companyaddress;
	}

	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}

	@Column(name = "wechatid", length = 40)
	public String getWechatid() {
		return this.wechatid;
	}

	public void setWechatid(String wechatid) {
		this.wechatid = wechatid;
	}

	@Column(name = "wechatname", length = 40)
	public String getWechatname() {
		return this.wechatname;
	}

	public void setWechatname(String wechatname) {
		this.wechatname = wechatname;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}