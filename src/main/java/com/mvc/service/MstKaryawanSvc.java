package com.mvc.service;

import java.util.List;
import java.util.Map;

import com.mvc.dto.MstKaryawanDto;

public interface MstKaryawanSvc {

	public List<MstKaryawanDto> findAllKaryawan();
	public MstKaryawanDto findOneKaryawan(String kodeKaryawan);
	public void saveKaryawan(MstKaryawanDto dto);
	public void deleteKaryawan(String kodeKaryawan);
	public Map<String, Object> listAllPageKaryawan(String cari, int page);
	public MstKaryawanDto login(String username);
}
