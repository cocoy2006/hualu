package com.hualu.main.java.component;

import com.hualu.main.java.entity.DoctorRecordTask;
import com.hualu.main.java.entity.ManagerRecordTask;
import com.hualu.main.java.entity.NurseRecordTask;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.entity.User;

public class RecordTaskComponent {

	private RecordTask recordTask;
	private NurseRecordTask nurseRecordTask;
	private DoctorRecordTask doctorRecordTask;
	private ManagerRecordTask managerRecordTask;
	private User user;
	private Operator nurse;
	private Operator doctor;
	private Operator manager;
	/**
	 * @return the recordTask
	 */
	public RecordTask getRecordTask() {
		return recordTask;
	}
	/**
	 * @param recordTask the recordTask to set
	 */
	public void setRecordTask(RecordTask recordTask) {
		this.recordTask = recordTask;
	}
	/**
	 * @return the nurseRecordTask
	 */
	public NurseRecordTask getNurseRecordTask() {
		return nurseRecordTask;
	}
	/**
	 * @param nurseRecordTask the nurseRecordTask to set
	 */
	public void setNurseRecordTask(NurseRecordTask nurseRecordTask) {
		this.nurseRecordTask = nurseRecordTask;
	}
	/**
	 * @return the doctorRecordTask
	 */
	public DoctorRecordTask getDoctorRecordTask() {
		return doctorRecordTask;
	}
	/**
	 * @param doctorRecordTask the doctorRecordTask to set
	 */
	public void setDoctorRecordTask(DoctorRecordTask doctorRecordTask) {
		this.doctorRecordTask = doctorRecordTask;
	}
	/**
	 * @return the managerRecordTask
	 */
	public ManagerRecordTask getManagerRecordTask() {
		return managerRecordTask;
	}
	/**
	 * @param managerRecordTask the managerRecordTask to set
	 */
	public void setManagerRecordTask(ManagerRecordTask managerRecordTask) {
		this.managerRecordTask = managerRecordTask;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
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
	 * @param nurse the nurse to set
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
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Operator doctor) {
		this.doctor = doctor;
	}
	/**
	 * @return the manager
	 */
	public Operator getManager() {
		return manager;
	}
	/**
	 * @param manager the manager to set
	 */
	public void setManager(Operator manager) {
		this.manager = manager;
	}

}