package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the MST_BARANG database table.
 * 
 */
@Entity
@Table(name="MST_BARANG")
@NamedQuery(name="MstBarang.findAll", query="SELECT m FROM MstBarang m")
@IdClass(MstBarangPK.class)
public class MstBarang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="KODE_BARANG")
	private String kodeBarang;

	@Column(name="KODE_SUPPLIER")
	private String kodeSupplier;

	@Column(name="NAMA_BARANG")
	private String namaBarang;

	@Column(name="STOK_BARANG")
	private int stokBarang;

	public MstBarang() {
	}

	public String getKodeBarang() {
		return this.kodeBarang;
	}

	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}

	public String getKodeSupplier() {
		return this.kodeSupplier;
	}

	public void setKodeSupplier(String kodeSupplier) {
		this.kodeSupplier = kodeSupplier;
	}

	public String getNamaBarang() {
		return this.namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public int getStokBarang() {
		return this.stokBarang;
	}

	public void setStokBarang(int stokBarang) {
		this.stokBarang = stokBarang;
	}

}