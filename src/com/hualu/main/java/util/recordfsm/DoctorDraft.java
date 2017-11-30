package com.hualu.main.java.util.recordfsm;

import org.springframework.stereotype.Service;

@Service
public class DoctorDraft extends RecordState {

//	private int id;
//	
//	public DoctorDraft() {}
//
//	public DoctorDraft(int id) {
//		this.id = id;
//	}

	@Override
	public void execute(int taskid) {
		id = taskid;
	}
	
}
