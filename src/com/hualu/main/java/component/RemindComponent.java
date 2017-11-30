package com.hualu.main.java.component;

import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Remind;
import com.hualu.main.java.entity.User;

public class RemindComponent {

	private Remind remind;
	private Operator operator;
	private User user;

	/**
	 * @return the remind
	 */
	public Remind getRemind() {
		return remind;
	}

	/**
	 * @param remind
	 *            the remind to set
	 */
	public void setRemind(Remind remind) {
		this.remind = remind;
	}

	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
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
