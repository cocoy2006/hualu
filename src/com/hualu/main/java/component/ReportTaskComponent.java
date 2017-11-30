package com.hualu.main.java.component;

import java.util.List;

import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.RecordDaily;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.ReportPicture;
import com.hualu.main.java.entity.ReportTask;
import com.hualu.main.java.entity.User;

public class ReportTaskComponent {

	private ReportTask reportTask;
	private List<RecordTask> rtList;
	private List<RecordDaily> rdList;
	private User user;
	private Operator doctor;
	private List<ReportPicture> rpList;
	private int[] bpCount;

	/**
	 * @return the reportTask
	 */
	public ReportTask getReportTask() {
		return reportTask;
	}

	/**
	 * @param reportTask
	 *            the reportTask to set
	 */
	public void setReportTask(ReportTask reportTask) {
		this.reportTask = reportTask;
	}

	/**
	 * @return the rtList
	 */
	public List<RecordTask> getRtList() {
		return rtList;
	}

	/**
	 * @param rtList
	 *            the rtList to set
	 */
	public void setRtList(List<RecordTask> rtList) {
		this.rtList = rtList;
	}

	/**
	 * @return the rdList
	 */
	public List<RecordDaily> getRdList() {
		return rdList;
	}

	/**
	 * @param rdList
	 *            the rdList to set
	 */
	public void setRdList(List<RecordDaily> rdList) {
		this.rdList = rdList;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the doctor
	 */
	public Operator getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 *            the doctor to set
	 */
	public void setDoctor(Operator doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return the rpList
	 */
	public List<ReportPicture> getRpList() {
		return rpList;
	}

	/**
	 * @param rpList
	 *            the rpList to set
	 */
	public void setRpList(List<ReportPicture> rpList) {
		this.rpList = rpList;
	}

	/**
	 * @return the bpCount
	 */
	public int[] getBpCount() {
		return bpCount;
	}

	/**
	 * @param bpCount
	 *            the bpCount to set
	 */
	public void setBpCount(int[] bpCount) {
		this.bpCount = bpCount;
	}

}