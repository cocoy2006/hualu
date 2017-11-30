package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Plan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "plan", catalog = "hualu")
public class Plan implements java.io.Serializable, Cloneable {

	// Fields

	private Integer id;
	private Integer fromid;
	private Integer toid;
	private Integer type;
	private Timestamp createtime;
	private Timestamp starttime;
	private Integer period;
	private Timestamp endtime;
	private Integer cycle;
	private String way;
	private String content;
	private Integer status;
	private Integer sent;

	// Constructors

	/** default constructor */
	public Plan() {
	}

	/** minimal constructor */
	public Plan(Integer fromid, Integer toid, Integer type, Integer period,
			Integer cycle, String way, Integer status, Integer sent) {
		this.fromid = fromid;
		this.toid = toid;
		this.type = type;
		this.period = period;
		this.cycle = cycle;
		this.way = way;
		this.status = status;
		this.sent = sent;
	}

	/** full constructor */
	public Plan(Integer fromid, Integer toid, Integer type,
			Timestamp createtime, Timestamp starttime, Integer period,
			Timestamp endtime, Integer cycle, String way, String content,
			Integer status, Integer sent) {
		this.fromid = fromid;
		this.toid = toid;
		this.type = type;
		this.createtime = createtime;
		this.starttime = starttime;
		this.period = period;
		this.endtime = endtime;
		this.cycle = cycle;
		this.way = way;
		this.content = content;
		this.status = status;
		this.sent = sent;
	}
	
	public Plan clone() {
		try {
			return (Plan) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
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

	@Column(name = "fromid", nullable = false)
	public Integer getFromid() {
		return this.fromid;
	}

	public void setFromid(Integer fromid) {
		this.fromid = fromid;
	}

	@Column(name = "toid", nullable = false)
	public Integer getToid() {
		return this.toid;
	}

	public void setToid(Integer toid) {
		this.toid = toid;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "createtime", length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "starttime", length = 19)
	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	@Column(name = "period", nullable = false)
	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	@Column(name = "endtime", length = 19)
	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	@Column(name = "cycle", nullable = false)
	public Integer getCycle() {
		return this.cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	@Column(name = "way", nullable = false, length = 20)
	public String getWay() {
		return this.way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "sent", nullable = false)
	public Integer getSent() {
		return this.sent;
	}

	public void setSent(Integer sent) {
		this.sent = sent;
	}

}