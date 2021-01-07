package com.mvc.service;

import java.util.Map;

import com.mvc.dto.MstCustomerDto;

public interface MstCustomerSvc {
	public void save(MstCustomerDto dto);
	public void update(MstCustomerDto dto);
	public void delete(String kodeCustomer);
	public MstCustomerDto findOne(String kodeCustomer);
	public Map<String, Object> listAll(String cari, int page);
}
