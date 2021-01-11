package com.mvc.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class TrDetailPenjualanDto {
	@NotEmpty(message="please fill kode detail")
	private String kodeDetail;
	@NotEmpty(message="please fill diskon")
	private Integer diskon;
	@NotEmpty(message="please fill harga barang")
	private Integer hargaSatuan;
	private String kodeBarang;
	private String noNota;
	@Min(1)
	private Integer qty;
	@NotEmpty
	private Integer subtotal;
	private String namaBarang; //karena di UI diminta nama barang.
	
	public String getKodeDetail() {
		return kodeDetail;
	}
	public void setKodeDetail(String kodeDetail) {
		this.kodeDetail = kodeDetail;
	}
	public Integer getDiskon() {
		return diskon;
	}
	public void setDiskon(Integer diskon) {
		this.diskon = diskon;
	}
	public Integer getHargaSatuan() {
		return hargaSatuan;
	}
	public void setHargaSatuan(Integer hargaSatuan) {
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
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}
	public String getNamaBarang() {
		return namaBarang;
	}
	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}
}
