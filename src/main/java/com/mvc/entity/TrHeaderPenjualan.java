package com.mvc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TR_HEADER_PENJUALAN database table.
 * 
 */
@Entity
@Table(name="TR_HEADER_PENJUALAN")
@NamedQuery(name="TrHeaderPenjualan.findAll", query="SELECT t FROM TrHeaderPenjualan t")
public class TrHeaderPenjualan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NO_NOTA")
	private String noNota;

	@Column(name="GLOBAL_DISKON")
	private int globalDiskon;

	@Column(name="HARGA_TOTAL")
	private int hargaTotal;

	@Column(name="KODE_CUSTOMER")
	private String kodeCustomer;

	@Column(name="KODE_KARYAWAN")
	private String kodeKaryawan;

	@Column(name="TANGGAL_TRANSAKSI")
	private Timestamp tanggalTransaksi;

	public TrHeaderPenjualan() {
	}

	public String getNoNota() {
		return this.noNota;
	}

	public void setNoNota(String noNota) {
		this.noNota = noNota;
	}

	public int getGlobalDiskon() {
		return this.globalDiskon;
	}

	public void setGlobalDiskon(int globalDiskon) {
		this.globalDiskon = globalDiskon;
	}

	public int getHargaTotal() {
		return this.hargaTotal;
	}

	public void setHargaTotal(int hargaTotal) {
		this.hargaTotal = hargaTotal;
	}

	public String getKodeCustomer() {
		return this.kodeCustomer;
	}

	public void setKodeCustomer(String kodeCustomer) {
		this.kodeCustomer = kodeCustomer;
	}

	public String getKodeKaryawan() {
		return this.kodeKaryawan;
	}

	public void setKodeKaryawan(String kodeKaryawan) {
		this.kodeKaryawan = kodeKaryawan;
	}

	public Timestamp getTanggalTransaksi() {
		return this.tanggalTransaksi;
	}

	public void setTanggalTransaksi(Timestamp tanggalTransaksi) {
		this.tanggalTransaksi = tanggalTransaksi;
	}

}