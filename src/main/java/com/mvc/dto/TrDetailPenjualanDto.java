package com.mvc.dto;

public class TrDetailPenjualanDto {
	private String kodeDetail;
	private int diskon;
	private int hargaSatuan;
	private String kodeBarang;
	private String noNota;
	private int qty;
	private int subtotal;
	private String namaBarang; //karena di UI diminta nama barang.
	
	public String getKodeDetail() {
		return kodeDetail;
	}
	public void setKodeDetail(String kodeDetail) {
		this.kodeDetail = kodeDetail;
	}
	public int getDiskon() {
		return diskon;
	}
	public void setDiskon(int diskon) {
		this.diskon = diskon;
	}
	public int getHargaSatuan() {
		return hargaSatuan;
	}
	public void setHargaSatuan(int hargaSatuan) {
		this.hargaSatuan = hargaSatuan;
	}
	public String getKodeBarang() {
		return kodeBarang;
	}
	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}
	public String getNoNota() {
		return noNota;
	}
	public void setNoNota(String noNota) {
		this.noNota = noNota;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	public String getNamaBarang() {
		return namaBarang;
	}
	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}
}
