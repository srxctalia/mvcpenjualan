package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the MST_KARYAWAN database table.
 * 
 */
@Entity
@Table(name="MST_KARYAWAN")
@NamedQuery(name="MstKaryawan.findAll", query="SELECT m FROM MstKaryawan m")
@IdClass(MstKaryawanPK.class)
public class MstKaryawan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="KODE_KARYAWAN")
	private String kodeKaryawan;

	@Column(name="NAMA_KARYAWAN")
	private String namaKaryawan;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="USERNAME")
	private String username;

	@Column(name="LEVEL")
	private String level;
	
	public MstKaryawan() {
	}

	public String getKodeKaryawan() {
		return this.kodeKaryawan;
	}

	public void setKodeKaryawan(String kodeKaryawan) {
		this.kodeKaryawan = kodeKaryawan;
	}

	public String getNamaKaryawan() {
		return this.namaKaryawan;
	}

	public void setNamaKaryawan(String namaKaryawan) {
		this.namaKaryawan = namaKaryawan;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
}