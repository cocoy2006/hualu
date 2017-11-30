package com.hualu.main.java.component;

import java.util.List;
import java.util.Set;

import com.hualu.main.java.entity.Device;
import com.hualu.main.java.entity.HealthTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.User;

public class UserComponent {

	private User user;
	private List<Device> devices;
	private List<RecordTask> rts;
	private List<HealthTask> hts;
	private Set<Operator> doctors;

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
	 * @return the devices
	 */
	public List<Device> getDevices() {
		return devices;
	}

	/**
	 * @param devices
	 *            the devices to set
	 */
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	/**
	 * @return the rts
	 */
	public List<RecordTask> getRts() {
		return rts;
	}

	/**
	 * @param rts
	 *            the rts to set
	 */
	public void setRts(List<RecordTask> rts) {
		this.rts = rts;
	}

	/**
	 * @return the hts
	 */
	public List<HealthTask> getHts() {
		return hts;
	}

	/**
	 * @param hts
	 *            the hts to set
	 */
	public void setHts(List<HealthTask> hts) {
		this.hts = hts;
	}

	/**
	 * @return the doctors
	 */
	public Set<Operator> getDoctors() {
		return doctors;
	}

	/**
	 * @param doctors
	 *            the doctors to set
	 */
	public void setDoctors(Set<Operator> doctors) {
		this.doctors = doctors;
	}

}