package com.hualu.main.java.util.recordfsm;

import org.springframework.stereotype.Service;

import com.hualu.main.java.util.Status;

@Service
public class WaitingDoctor extends RecordState {

//	private int id;
//	
//	public WaitingDoctor() {}
//
//	public WaitingDoctor(int id) {
//		this.id = id;
//	}

	@Override
	public void execute(int taskid) {
		id = taskid;
//		saveDoctorRecordTask(operator.getId(), id);
		next();
	}
	
	private void next() {
		status = Status.RecordTask.DOCTOR_PROCESSING.getInt();
		setStatus(id);
	}
	
}
