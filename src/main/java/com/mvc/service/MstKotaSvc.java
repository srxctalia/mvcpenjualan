package com.mvc.service;

import java.util.List;
import java.util.Map;

import com.mvc.dto.MstKotaDto;

public interface MstKotaSvc {
	public List<MstKotaDto> findAllKota();
	public MstKotaDto findOneKota(String kodeKota);
	public void saveKota(MstKotaDto dto);
	public void deleteKota(String kodeKota);
	public Map<String, Object> listAllPageKota(String cari, int page);
}
