package com.mvc.service;

import java.util.List;
import java.util.Map;

import com.mvc.dto.MstBarangDto;

public interface MstBarangSvc {
	public List<MstBarangDto> findAllBarang();
	public MstBarangDto findOneBarang(String kodeBarang);
	public void saveBarang(MstBarangDto dto);
	public void deleteBarang(String kodeBarang);
	public Map<String, Object> listAllPageBarang(String cari, int page);
}
