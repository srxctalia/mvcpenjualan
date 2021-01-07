package com.mvc.entity;

import java.io.Serializable;

public class MstKotaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//input kolom PK kedalam sini.
	private String kodeKota;
	
	//getter&setter
	public String getKodeKota() {
		return kodeKota;
	}
	public void setKodeKota(String kodeKota) {
		this.kodeKota = kodeKota;
	}
	
}
