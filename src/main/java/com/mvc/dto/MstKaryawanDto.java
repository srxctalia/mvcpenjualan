package com.mvc.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class MstKaryawanDto {
	@NotEmpty(message="Please fill kode karyawan")
	private String kodeKaryawan;
	@NotEmpty(message="Please fill nama karyawan")
	private String namaKaryawan;
	@NotEmpty(message="Please fill password")
	private String password;
	@NotEmpty(message="Please fill username")
	private String username;
	@NotEmpty(message="Please fill level karyawan")
	private String level;
	
	public String getKodeKaryawan() {
		return kodeKaryawan;
	}
	public void setKodeKaryawan(String kodeKaryawan) {
		this.kodeKaryawan = kodeKaryawan;
	}
	public String getNamaKaryawan() {
		return namaKaryawan;
	}
	public void setNamaKaryawan(String namaKaryawan) {
		this.namaKaryawan = namaKaryawan;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
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
