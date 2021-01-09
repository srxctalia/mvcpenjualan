package com.mvc.service;

import java.util.List;
import java.util.Map;

import com.mvc.dto.MstProvinsiDto;

public interface MstProvinsiSvc {

	public List<MstProvinsiDto> findAllProvinsi();
	public MstProvinsiDto findOneProvinsi(String kodeProvinsi);
	public void saveProvinsi(MstProvinsiDto dto);
	public void deleteProvinsi(String kodeProvinsi);
	public Map<String, Object> listAllPageProvinsi(String cari, int page);
}
