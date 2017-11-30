package com.hualu.main.java.component;

import com.hualu.main.java.entity.Company;
import com.hualu.main.java.entity.Device;
import com.hualu.main.java.entity.DevicePackage;
import com.hualu.main.java.entity.HealthTask;
import com.hualu.main.java.entity.Operationlog;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.Remind;
import com.hualu.main.java.entity.ReportTask;
import com.hualu.main.java.entity.User;

public class OperationlogComponent {

	private Operationlog operationlog;
	private Operator operator;
	private RecordTask rt;
	private ReportTask reportTask;
	private HealthTask ht;
	private Remind remindTask;
	private User user;
	private Device device;
	private Company company;
	private DevicePackage pack;

	/**
	 * @return the operationlog
	 */
	public Operationlog getOperationlog() {
		return operationlog;
	}

	/**
	 * @param operationlog
	 *            the operationlog to set
	 */
	public void setOperationlog(Operationlog operationlog) {
		this.operationlog = operationlog;
	}

	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * @return the rt
	 */
	public RecordTask getRt() {
		return rt;
	}

	/**
	 * @param rt
	 *            the rt to set
	 */
	public void setRt(RecordTask rt) {
		this.rt = rt;
	}

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
	 * @return the ht
	 */
	public HealthTask getHt() {
		return ht;
	}

	/**
	 * @param ht
	 *            the ht to set
	 */
	public void setHt(HealthTask ht) {
		this.ht = ht;
	}

	/**
	 * @return the remindTask
	 */
	public Remind getRemindTask() {
		return remindTask;
	}

	/**
	 * @param remindTask
	 *            the remindTask to set
	 */
	public void setRemindTask(Remind remindTask) {
		this.remindTask = remindTask;
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
	 * @return the device
	 */
	public Device getDevice() {
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * @return the pack
	 */
	public DevicePackage getPack() {
		return pack;
	}

	/**
	 * @param pack
	 *            the pack to set
	 */
	public void setPack(DevicePackage pack) {
		this.pack = pack;
	}

}
