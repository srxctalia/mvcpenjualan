package com.mvc.entity;

import java.io.Serializable;

public class TrDetailPenjualanPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//input kolom PK kedalam sini.
	private String kodeDetail;
	
	//getter&setter
	public String getKodeDetail() {
		return kodeDetail;
	}
	public void setKodeDetail(String kodeDetail) {
		this.kodeDetail = kodeDetail;
	}
}
