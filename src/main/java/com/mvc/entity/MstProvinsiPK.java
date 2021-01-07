package com.mvc.entity;

import java.io.Serializable;

public class MstProvinsiPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//input kolom PK kedalam sini.
	private String kodeProvinsi;
	
	//getter&setter
	public String getKodeProvinsi() {
		return kodeProvinsi;
	}
	public void setKodeProvinsi(String kodeProvinsi) {
		this.kodeProvinsi = kodeProvinsi;
	}
	
}
