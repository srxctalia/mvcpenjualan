package com.mvc.service;

import java.util.List;
import java.util.Map;

import com.mvc.dto.TrDetailPenjualanDto;
import com.mvc.dto.TrHeaderPenjualanDto;



public interface TransaksiSvc {
	public Map<String, Object> listAll(String cari, int page);
	public List<TrDetailPenjualanDto> findAllDetail(String noNota);
	public void saveDetail(TrDetailPenjualanDto dto); //hanya disediakan, tidak dipakai.
	public void saveHeader(TrHeaderPenjualanDto dto);
	public void deleteHeaderDetail(String noNota);
	public TrHeaderPenjualanDto findOneHeaderDetail(String noNota);
}
