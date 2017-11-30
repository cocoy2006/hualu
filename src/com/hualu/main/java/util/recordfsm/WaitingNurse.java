package com.hualu.main.java.util.recordfsm;

import org.springframework.stereotype.Service;

import com.hualu.main.java.util.Status;

@Service
public class WaitingNurse extends RecordState {

	@Override
	public void execute(int taskid) {
		id = taskid;
		next();
	}
	
	private void next() {
		status = Status.RecordTask.NURSE_PROCESSING.getInt();
		setStatus(id);
	}
	
}
