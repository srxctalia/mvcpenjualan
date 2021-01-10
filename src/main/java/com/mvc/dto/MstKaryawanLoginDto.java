package com.mvc.dto;

import org.hibernate.validator.constraints.NotEmpty;


public class MstKaryawanLoginDto {
	private String kodeKaryawan;
	private String namaKaryawan;
	@NotEmpty(message="password must be filled")
	private String password;
	@NotEmpty(message="username must be filled")
	private String username;
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
