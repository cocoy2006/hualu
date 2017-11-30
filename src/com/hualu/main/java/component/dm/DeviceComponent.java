package com.hualu.main.java.component.dm;

import com.hualu.main.java.entity.Company;
import com.hualu.main.java.entity.Device;
import com.hualu.main.java.entity.DevicePackage;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.User;

public class DeviceComponent {

	private Device device;
	private DevicePackage devicePackage;
	private Company company;
	private Operator nurse;
	private User user;

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
	 * @return the devicePackage
	 */
	public DevicePackage getDevicePackage() {
		return devicePackage;
	}

	/**
	 * @param devicePackage
	 *            the devicePackage to set
	 */
	public void setDevicePackage(DevicePackage devicePackage) {
		this.devicePackage = devicePackage;
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
	 * @return the nurse
	 */
	public Operator getNurse() {
		return nurse;
	}

	/**
	 * @param nurse
	 *            the nurse to set
	 */
	public void setNurse(Operator nurse) {
		this.nurse = nurse;
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

}