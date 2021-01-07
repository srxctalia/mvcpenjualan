package com.mvc.dto;

import org.hibernate.validator.constraints.NotEmpty;



public class MstSupplierDto {
	@NotEmpty(message="Please fill kode supplier")
	private String kodeSupplier;
	@NotEmpty(message="Please fill alamat supplier")
	private String alamatSupplier;
	@NotEmpty(message="Please fill email supplier")
	private String emailSupplier;
	@NotEmpty(message="Please fill kota")
	private String kodeKota;
	private String namaKota;
	@NotEmpty(message="Please fill nama supplier")
	private String namaSupplier;
	@NotEmpty(message="Please fill no telp")
	private String telpSupplier;
	
	public String getKodeSupplier() {
		return kodeSupplier;
	}
	public void setKodeSupplier(String kodeSupplier) {
		this.kodeSupplier = kodeSupplier;
	}
	public String getAlamatSupplier() {
		return alamatSupplier;
	}
	public void setAlamatSupplier(String alamatSupplier) {
		this.alamatSupplier = alamatSupplier;
	}
	public String getEmailSupplier() {
		return emailSupplier;
	}
	public void setEmailSupplier(String emailSupplier) {
		this.emailSupplier = emailSupplier;
	}
	public String getKodeKota() {
		return kodeKota;
	}
	public void setKodeKota(String kodeKota) {
		this.kodeKota = kodeKota;
	}
	public String getNamaSupplier() {
		return namaSupplier;
	}
	public void setNamaSupplier(String namaSupplier) {
		this.namaSupplier = namaSupplier;
	}
	public String getTelpSupplier() {
		return telpSupplier;
	}
	public void setTelpSupplier(String telpSupplier) {
		this.telpSupplier = telpSupplier;
	}
	public String getNamaKota() {
		return namaKota;
	}
	public void setNamaKota(String namaKota) {
		this.namaKota = namaKota;
	}
	
	
}
