package com.mvc.dto;

import org.hibernate.validator.constraints.NotEmpty;



public class MstKotaDto {
	@NotEmpty(message="Please fill kode kota")
	private String kodeKota;
	
	@NotEmpty(message="Please fill provinsi")
	private String kodeProvinsi;
	
	@NotEmpty(message="Please fill nama kota")
	private String namaKota;
	
	private String namaProvinsi;
	
	
	public String getNamaProvinsi() {
		return namaProvinsi;
	}
	public void setNamaProvinsi(String namaProvinsi) {
		this.namaProvinsi = namaProvinsi;
	}
	public String getKodeKota() {
		return kodeKota;
	}
	public void setKodeKota(String kodeKota) {
		this.kodeKota = kodeKota;
	}
	public String getKodeProvinsi() {
		return kodeProvinsi;
	}
	public void setKodeProvinsi(String kodeProvinsi) {
		this.kodeProvinsi = kodeProvinsi;
	}
	public String getNamaKota() {
		return namaKota;
	}
	public void setNamaKota(String namaKota) {
		this.namaKota = namaKota;
	}
	
	
}
