package com.hualu.main.java.util.converter;

import com.hualu.main.java.entity.User;
import com.hualu.main.java.form.UserForm;

public class FormToEntityConverter {

	public static User user(UserForm uf) {
		User user = new User();
		if(uf.getId() != null) {
			user.setId(BasicConverter.StringToInteger(uf.getId()));
		}
		if(uf.getCreatetime() != null) {
			user.setCreatetime(BasicConverter.StringToTimestamp(uf.getCreatetime()));
		}
		if(uf.getGender() != null) {
			user.setGender(BasicConverter.StringToInteger(uf.getGender()));
		}
		return user;
	}
	
}
