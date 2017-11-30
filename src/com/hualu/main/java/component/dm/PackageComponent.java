package com.hualu.main.java.component.dm;

import com.hualu.main.java.entity.Company;
import com.hualu.main.java.entity.DevicePackage;

public class PackageComponent {

	private DevicePackage devicePackage;
	private Company company;

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

}