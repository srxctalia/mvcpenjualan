package com.mvc.entity;

import java.io.Serializable;

public class MstCustomerPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String kodeCustomer;

	public String getKodeCustomer() {
		return kodeCustomer;
	}

	public void setKodeCustomer(String kodeCustomer) {
		this.kodeCustomer = kodeCustomer;
	}
}
