package com.mvc.service;

import java.util.Map;

import com.mvc.dto.MstSupplierDto;

public interface MstSupplierSvc {
	public Map<String, Object> listAll(String cari, int page);
	public void save(MstSupplierDto dto);
	public void update(MstSupplierDto dto);
	public void delete(String kodeSupplier);
	public MstSupplierDto findOne(String kodeSupplier);
}
