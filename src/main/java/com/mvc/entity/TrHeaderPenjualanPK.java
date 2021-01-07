package com.mvc.entity;

import java.io.Serializable;

public class TrHeaderPenjualanPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
	//input kolom PK kedalam sini.
	private String noNota;
	
	//getter&setter
	public String getNoNota() {
		return noNota;
	}
	public void setNoNota(String noNota) {
		this.noNota = noNota;
	}
}
