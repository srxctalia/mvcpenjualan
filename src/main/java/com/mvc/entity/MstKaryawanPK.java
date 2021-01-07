package com.mvc.entity;

import java.io.Serializable;

public class MstKaryawanPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//input kolom PK kedalam sini.
	private String kodeKaryawan;
	
	//getter&setter
	public String getkodeKaryawan() {
		return kodeKaryawan;
	}
	public void setkodeKaryawan(String kodeKaryawan) {
		this.kodeKaryawan= kodeKaryawan;
	}
	
}