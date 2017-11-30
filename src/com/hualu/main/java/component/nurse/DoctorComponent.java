package com.hualu.main.java.component.nurse;

import com.hualu.main.java.entity.Operator;

public class DoctorComponent {

	private Operator doctor;
	private int undoneHealthTaskNumber;

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
	 * @return the undoneHealthTaskNumber
	 */
	public int getUndoneHealthTaskNumber() {
		return undoneHealthTaskNumber;
	}

	/**
	 * @param undoneHealthTaskNumber
	 *            the undoneHealthTaskNumber to set
	 */
	public void setUndoneHealthTaskNumber(int undoneHealthTaskNumber) {
		this.undoneHealthTaskNumber = undoneHealthTaskNumber;
	}

}