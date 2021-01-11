package com.mvc.dto;

import org.hibernate.validator.constraints.NotEmpty;



public class MstProvinsiDto {
	@NotEmpty(message="Please fill kode provinsi")
	private String kodeProvinsi;
	@NotEmpty(message="Please fill nama provinsi")
	private String namaProvinsi;
	
	public String getKodeProvinsi() {
		return kodeProvinsi;
	}
	public void setKodeProvinsi(String kodeProvinsi) {
		this.kodeProvinsi = kodeProvinsi;
	}
	public String getNamaProvinsi() {
		return namaProvinsi;
	}
	public void setNamaProvinsi(String namaProvinsi) {
		this.namaProvinsi = namaProvinsi;
	}
	
	
}
