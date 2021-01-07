package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the MST_PROVINSI database table.
 * 
 */
@Entity
@Table(name="MST_PROVINSI")
@NamedQuery(name="MstProvinsi.findAll", query="SELECT m FROM MstProvinsi m")
@IdClass(MstProvinsiPK.class)
public class MstProvinsi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="KODE_PROVINSI")
	private String kodeProvinsi;

	@Column(name="NAMA_PROVINSI")
	private String namaProvinsi;

	public MstProvinsi() {
	}

	public String getKodeProvinsi() {
		return this.kodeProvinsi;
	}

	public void setKodeProvinsi(String kodeProvinsi) {
		this.kodeProvinsi = kodeProvinsi;
	}

	public String getNamaProvinsi() {
		return this.namaProvinsi;
	}

	public void setNamaProvinsi(String namaProvinsi) {
		this.namaProvinsi = namaProvinsi;
	}

}