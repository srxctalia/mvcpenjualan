package com.mvc.service;

import java.util.List;
import java.util.Map;

import com.mvc.dto.MstSupplierDto;

public interface MstSupplierSvc {
	public List<MstSupplierDto> findAll();
	public void save(MstSupplierDto dto);
	public void update(MstSupplierDto dto);
	public void delete(String kodeSupplier);
	public MstSupplierDto findOne(String kodeSupplier);
	public Map<String, Object> listAll(String cari, int page);
}
