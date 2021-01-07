package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the TR_DETAIL_PENJUALAN database table.
 * 
 */
@Entity
@Table(name="TR_DETAIL_PENJUALAN")
@NamedQuery(name="TrDetailPenjualan.findAll", query="SELECT t FROM TrDetailPenjualan t")
@IdClass(TrDetailPenjualanPK.class)
public class TrDetailPenjualan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="KODE_DETAIL")
	private String kodeDetail;

	@Column(name="DISKON")
	private int diskon;

	@Column(name="HARGA_SATUAN")
	private int hargaSatuan;

	@Column(name="KODE_BARANG")
	private String kodeBarang;

	@Column(name="NO_NOTA")
	private String noNota;

	@Column(name="QTY")
	private int qty;

	@Column(name="SUBTOTAL")
	private int subtotal;

	public TrDetailPenjualan() {
	}

	public String getKodeDetail() {
		return this.kodeDetail;
	}

	public void setKodeDetail(String kodeDetail) {
		this.kodeDetail = kodeDetail;
	}

	public int getDiskon() {
		return this.diskon;
	}

	public void setDiskon(int diskon) {
		this.diskon = diskon;
	}

	public int getHargaSatuan() {
		return this.hargaSatuan;
	}

	public void setHargaSatuan(int hargaSatuan) {
		this.hargaSatuan = hargaSatuan;
	}

	public String getKodeBarang() {
		return this.kodeBarang;
	}

	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}

	public String getNoNota() {
		return this.noNota;
	}

	public void setNoNota(String noNota) {
		this.noNota = noNota;
	}

	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

}