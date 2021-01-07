package com.mvc.dto;

public class MstBarangDto {
	private String kodeBarang;
	private String kodeSupplier;
	private String namaBarang;
	private int stokBarang;
	
	private String namaSupplier;
	
	public String getNamaSupplier() {
		return namaSupplier;
	}
	public void setNamaSupplier(String namaSupplier) {
		this.namaSupplier = namaSupplier;
	}
	public String getKodeBarang() {
		return kodeBarang;
	}
	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}
	public String getKodeSupplier() {
		return kodeSupplier;
	}
	public void setKodeSupplier(String kodeSupplier) {
		this.kodeSupplier = kodeSupplier;
	}
	public String getNamaBarang() {
		return namaBarang;
	}
	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}
	public int getStokBarang() {
		return stokBarang;
	}
	public void setStokBarang(int stokBarang) {
		this.stokBarang = stokBarang;
	}
}
