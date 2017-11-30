package com.hualu.main.java.component;

import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Signlog;

public class SignlogComponent {

	private Signlog signlog;
	private Operator operator;

	/**
	 * @return the signlog
	 */
	public Signlog getSignlog() {
		return signlog;
	}

	/**
	 * @param signlog
	 *            the signlog to set
	 */
	public void setSignlog(Signlog signlog) {
		this.signlog = signlog;
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

}
