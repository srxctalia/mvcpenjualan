package com.mvc.entity;

import java.io.Serializable;

public class MstSupplierPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//input kolom PK kedalam sini.
	private String kodeSupplier;
	
	//getter&setter
	public String getKodeSupplier() {
		return kodeSupplier;
	}
	public void setKodeSupplier(String kodeSupplier) {
		this.kodeSupplier= kodeSupplier;
	}
	
}
