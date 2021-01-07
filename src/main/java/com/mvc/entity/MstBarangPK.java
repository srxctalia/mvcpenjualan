package com.mvc.entity;

import java.io.Serializable;

public class MstBarangPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//input kolom PK kedalam sini.
	private String kodeBarang;
	
	//getter&setter
	public String getKodeBarang() {
		return kodeBarang;
	}
	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}
	
}
