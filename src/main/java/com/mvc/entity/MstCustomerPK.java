package com.mvc.entity;

import java.io.Serializable;

public class MstCustomerPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//input kolom PK kedalam sini.
	private String kodeCustomer;
	
	//getter&setter
	public String getKodeCostumer() {
		return kodeCustomer;
	}
	public void setKodeCostumer(String kodeCustomer) {
		this.kodeCustomer = kodeCustomer;
	}
	
}
