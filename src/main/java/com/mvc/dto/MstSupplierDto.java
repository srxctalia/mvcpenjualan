package com.mvc.dto;

import org.hibernate.validator.constraints.NotEmpty;


public class MstSupplierDto {
	@NotEmpty(message="please fill kode supplier")
	private String kodeSupplier;
	@NotEmpty(message="please fill alamat supplier")
	private String alamatSupplier;
	@NotEmpty(message="please fill email supplier")
	private String emailSupplier;
	@NotEmpty(message="please fill kota supplier")
	private String kodeKota;

	private String namaKota;

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

	private String namaSupplier;

	private String telpSupplier;

}
