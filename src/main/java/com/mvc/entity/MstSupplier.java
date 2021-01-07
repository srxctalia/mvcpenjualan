package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the MST_SUPPLIER database table.
 * 
 */
@Entity
@Table(name="MST_SUPPLIER")
@NamedQuery(name="MstSupplier.findAll", query="SELECT m FROM MstSupplier m")
@IdClass(MstSupplierPK.class)
public class MstSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="KODE_SUPPLIER")
	private String kodeSupplier;

	@Column(name="ALAMAT_SUPPLIER")
	private String alamatSupplier;

	@Column(name="EMAIL_SUPPLIER")
	private String emailSupplier;

	@Column(name="KODE_KOTA")
	private String kodeKota;

	@Column(name="NAMA_SUPPLIER")
	private String namaSupplier;

	@Column(name="TELP_SUPPLIER")
	private String telpSupplier;

	public MstSupplier() {
	}

	public String getKodeSupplier() {
		return this.kodeSupplier;
	}

	public void setKodeSupplier(String kodeSupplier) {
		this.kodeSupplier = kodeSupplier;
	}

	public String getAlamatSupplier() {
		return this.alamatSupplier;
	}

	public void setAlamatSupplier(String alamatSupplier) {
		this.alamatSupplier = alamatSupplier;
	}

	public String getEmailSupplier() {
		return this.emailSupplier;
	}

	public void setEmailSupplier(String emailSupplier) {
		this.emailSupplier = emailSupplier;
	}

	public String getKodeKota() {
		return this.kodeKota;
	}

	public void setKodeKota(String kodeKota) {
		this.kodeKota = kodeKota;
	}

	public String getNamaSupplier() {
		return this.namaSupplier;
	}

	public void setNamaSupplier(String namaSupplier) {
		this.namaSupplier = namaSupplier;
	}

	public String getTelpSupplier() {
		return this.telpSupplier;
	}

	public void setTelpSupplier(String telpSupplier) {
		this.telpSupplier = telpSupplier;
	}

}