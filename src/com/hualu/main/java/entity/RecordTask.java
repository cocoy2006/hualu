package com.hualu.main.java.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RecordTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "record_task", catalog = "hualu")
public class RecordTask implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer currentid;
	private Timestamp starttime;
	private Timestamp endtime;
	private Timestamp piecestarttime;
	private Timestamp pieceendtime;
	private String ids;
	private String pieceids;
	private String ecgwave1ids;
	private Float heartrate;
	private Integer heartrateresult;
	private String heartrateids;
	private Float breathrate;
	private Integer breathrateresult;
	private String breathrateids;
	private Float spo2value;
	private Integer spo2valueresult;
	private String spo2valueids;
	private Float pluserate;
	private Integer pluserateresult;
	private String pluserateids;
	private Float bphigh;
	private Float bplow;
	private Float bpavg;
	private Integer bpresult;
	private String bpids;
	private Float bprate;
	private Integer bprateresult;
	private String bprateids;
	private Float temp1;
	private Integer temp1result;
	private String temp1ids;
	private Integer hour;
	private Integer status;
	private Integer isdelete;

	// Constructors

	/** default constructor */
	public RecordTask() {
	}

	/** minimal constructor */
	public RecordTask(Integer userid) {
		this.userid = userid;
	}

	/** full constructor */
	public RecordTask(Integer userid, Integer currentid, Timestamp starttime,
			Timestamp endtime, Timestamp piecestarttime,
			Timestamp pieceendtime, String ids, String pieceids,
			String ecgwave1ids, Float heartrate, Integer heartrateresult,
			String heartrateids, Float breathrate, Integer breathrateresult,
			String breathrateids, Float spo2value, Integer spo2valueresult,
			String spo2valueids, Float pluserate, Integer pluserateresult,
			String pluserateids, Float bphigh, Float bplow, Float bpavg,
			Integer bpresult, String bpids, Float bprate, Integer bprateresult,
			String bprateids, Float temp1, Integer temp1result,
			String temp1ids, Integer hour, Integer status, Integer isdelete) {
		this.userid = userid;
		this.currentid = currentid;
		this.starttime = starttime;
		this.endtime = endtime;
		this.piecestarttime = piecestarttime;
		this.pieceendtime = pieceendtime;
		this.ids = ids;
		this.pieceids = pieceids;
		this.ecgwave1ids = ecgwave1ids;
		this.heartrate = heartrate;
		this.heartrateresult = heartrateresult;
		this.heartrateids = heartrateids;
		this.breathrate = breathrate;
		this.breathrateresult = breathrateresult;
		this.breathrateids = breathrateids;
		this.spo2value = spo2value;
		this.spo2valueresult = spo2valueresult;
		this.spo2valueids = spo2valueids;
		this.pluserate = pluserate;
		this.pluserateresult = pluserateresult;
		this.pluserateids = pluserateids;
		this.bphigh = bphigh;
		this.bplow = bplow;
		this.bpavg = bpavg;
		this.bpresult = bpresult;
		this.bpids = bpids;
		this.bprate = bprate;
		this.bprateresult = bprateresult;
		this.bprateids = bprateids;
		this.temp1 = temp1;
		this.temp1result = temp1result;
		this.temp1ids = temp1ids;
		this.hour = hour;
		this.status = status;
		this.isdelete = isdelete;
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

	@Column(name = "currentid")
	public Integer getCurrentid() {
		return this.currentid;
	}

	public void setCurrentid(Integer currentid) {
		this.currentid = currentid;
	}

	@Column(name = "starttime", length = 19)
	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	@Column(name = "endtime", length = 19)
	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	@Column(name = "piecestarttime", length = 19)
	public Timestamp getPiecestarttime() {
		return this.piecestarttime;
	}

	public void setPiecestarttime(Timestamp piecestarttime) {
		this.piecestarttime = piecestarttime;
	}

	@Column(name = "pieceendtime", length = 19)
	public Timestamp getPieceendtime() {
		return this.pieceendtime;
	}

	public void setPieceendtime(Timestamp pieceendtime) {
		this.pieceendtime = pieceendtime;
	}

	@Column(name = "ids")
	public String getIds() {
		return this.ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Column(name = "pieceids")
	public String getPieceids() {
		return this.pieceids;
	}

	public void setPieceids(String pieceids) {
		this.pieceids = pieceids;
	}

	@Column(name = "ecgwave1ids")
	public String getEcgwave1ids() {
		return this.ecgwave1ids;
	}

	public void setEcgwave1ids(String ecgwave1ids) {
		this.ecgwave1ids = ecgwave1ids;
	}

	@Column(name = "heartrate", precision = 12, scale = 0)
	public Float getHeartrate() {
		return this.heartrate;
	}

	public void setHeartrate(Float heartrate) {
		this.heartrate = heartrate;
	}

	@Column(name = "heartrateresult")
	public Integer getHeartrateresult() {
		return this.heartrateresult;
	}

	public void setHeartrateresult(Integer heartrateresult) {
		this.heartrateresult = heartrateresult;
	}

	@Column(name = "heartrateids")
	public String getHeartrateids() {
		return this.heartrateids;
	}

	public void setHeartrateids(String heartrateids) {
		this.heartrateids = heartrateids;
	}

	@Column(name = "breathrate", precision = 12, scale = 0)
	public Float getBreathrate() {
		return this.breathrate;
	}

	public void setBreathrate(Float breathrate) {
		this.breathrate = breathrate;
	}

	@Column(name = "breathrateresult")
	public Integer getBreathrateresult() {
		return this.breathrateresult;
	}

	public void setBreathrateresult(Integer breathrateresult) {
		this.breathrateresult = breathrateresult;
	}

	@Column(name = "breathrateids")
	public String getBreathrateids() {
		return this.breathrateids;
	}

	public void setBreathrateids(String breathrateids) {
		this.breathrateids = breathrateids;
	}

	@Column(name = "spo2value", precision = 12, scale = 0)
	public Float getSpo2value() {
		return this.spo2value;
	}

	public void setSpo2value(Float spo2value) {
		this.spo2value = spo2value;
	}

	@Column(name = "spo2valueresult")
	public Integer getSpo2valueresult() {
		return this.spo2valueresult;
	}

	public void setSpo2valueresult(Integer spo2valueresult) {
		this.spo2valueresult = spo2valueresult;
	}

	@Column(name = "spo2valueids")
	public String getSpo2valueids() {
		return this.spo2valueids;
	}

	public void setSpo2valueids(String spo2valueids) {
		this.spo2valueids = spo2valueids;
	}

	@Column(name = "pluserate", precision = 12, scale = 0)
	public Float getPluserate() {
		return this.pluserate;
	}

	public void setPluserate(Float pluserate) {
		this.pluserate = pluserate;
	}

	@Column(name = "pluserateresult")
	public Integer getPluserateresult() {
		return this.pluserateresult;
	}

	public void setPluserateresult(Integer pluserateresult) {
		this.pluserateresult = pluserateresult;
	}

	@Column(name = "pluserateids")
	public String getPluserateids() {
		return this.pluserateids;
	}

	public void setPluserateids(String pluserateids) {
		this.pluserateids = pluserateids;
	}

	@Column(name = "bphigh", precision = 12, scale = 0)
	public Float getBphigh() {
		return this.bphigh;
	}

	public void setBphigh(Float bphigh) {
		this.bphigh = bphigh;
	}

	@Column(name = "bplow", precision = 12, scale = 0)
	public Float getBplow() {
		return this.bplow;
	}

	public void setBplow(Float bplow) {
		this.bplow = bplow;
	}

	@Column(name = "bpavg", precision = 12, scale = 0)
	public Float getBpavg() {
		return this.bpavg;
	}

	public void setBpavg(Float bpavg) {
		this.bpavg = bpavg;
	}

	@Column(name = "bpresult")
	public Integer getBpresult() {
		return this.bpresult;
	}

	public void setBpresult(Integer bpresult) {
		this.bpresult = bpresult;
	}

	@Column(name = "bpids")
	public String getBpids() {
		return this.bpids;
	}

	public void setBpids(String bpids) {
		this.bpids = bpids;
	}

	@Column(name = "bprate", precision = 12, scale = 0)
	public Float getBprate() {
		return this.bprate;
	}

	public void setBprate(Float bprate) {
		this.bprate = bprate;
	}

	@Column(name = "bprateresult")
	public Integer getBprateresult() {
		return this.bprateresult;
	}

	public void setBprateresult(Integer bprateresult) {
		this.bprateresult = bprateresult;
	}

	@Column(name = "bprateids")
	public String getBprateids() {
		return this.bprateids;
	}

	public void setBprateids(String bprateids) {
		this.bprateids = bprateids;
	}

	@Column(name = "temp1", precision = 12, scale = 0)
	public Float getTemp1() {
		return this.temp1;
	}

	public void setTemp1(Float temp1) {
		this.temp1 = temp1;
	}

	@Column(name = "temp1result")
	public Integer getTemp1result() {
		return this.temp1result;
	}

	public void setTemp1result(Integer temp1result) {
		this.temp1result = temp1result;
	}

	@Column(name = "temp1ids")
	public String getTemp1ids() {
		return this.temp1ids;
	}

	public void setTemp1ids(String temp1ids) {
		this.temp1ids = temp1ids;
	}

	@Column(name = "hour")
	public Integer getHour() {
		return this.hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "isdelete")
	public Integer getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

}