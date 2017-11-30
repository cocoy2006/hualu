package com.hualu.main.java.component;

import com.hualu.main.java.entity.DoctorHealthTask;
import com.hualu.main.java.entity.HealthTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.User;

public class HealthTaskComponent {

	private HealthTask healthTask;
	private DoctorHealthTask doctorHealthTask;
	private User user;
	private Operator nurse;
	private Operator doctor;

	/**
	 * @return the healthTask
	 */
	public HealthTask getHealthTask() {
		return healthTask;
	}

	/**
	 * @param healthTask
	 *            the healthTask to set
	 */
	public void setHealthTask(HealthTask healthTask) {
		this.healthTask = healthTask;
	}

	/**
	 * @return the doctorHealthTask
	 */
	public DoctorHealthTask getDoctorHealthTask() {
		return doctorHealthTask;
	}

	/**
	 * @param doctorHealthTask
	 *            the doctorHealthTask to set
	 */
	public void setDoctorHealthTask(DoctorHealthTask doctorHealthTask) {
		this.doctorHealthTask = doctorHealthTask;
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

}