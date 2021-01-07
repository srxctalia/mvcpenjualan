package com.mvc.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MST_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="MST_CUSTOMER")
@NamedQuery(name="MstCustomer.findAll", query="SELECT m FROM MstCustomer m")
public class MstCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="KODE_CUSTOMER")
	private String kodeCustomer;

	@Column(name="ALAMAT_CUSTOMER")
	private String alamatCustomer;

	@Column(name="EMAIL_CUSTOMER")
	private String emailCustomer;

	@Column(name="JENIS_KELAMIN")
	private String jenisKelamin;

	@Column(name="KODE_KOTA")
	private String kodeKota;

	@Column(name="NAMA_CUSTOMER")
	private String namaCustomer;

	public MstCustomer() {
	}

	public String getKodeCustomer() {
		return this.kodeCustomer;
	}

	public void setKodeCustomer(String kodeCustomer) {
		this.kodeCustomer = kodeCustomer;
	}

	public String getAlamatCustomer() {
		return this.alamatCustomer;
	}

	public void setAlamatCustomer(String alamatCustomer) {
		this.alamatCustomer = alamatCustomer;
	}

	public String getEmailCustomer() {
		return this.emailCustomer;
	}

	public void setEmailCustomer(String emailCustomer) {
		this.emailCustomer = emailCustomer;
	}

	public String getJenisKelamin() {
		return this.jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getKodeKota() {
		return this.kodeKota;
	}

	public void setKodeKota(String kodeKota) {
		this.kodeKota = kodeKota;
	}

	public String getNamaCustomer() {
		return this.namaCustomer;
	}

	public void setNamaCustomer(String namaCustomer) {
		this.namaCustomer = namaCustomer;
	}

}