package com.mvc.service;

import java.util.List;

import com.mvc.dto.TrDetailPenjualanDto;
import com.mvc.dto.TrHeaderPenjualanDto;



public interface TransaksiSvc {
	public List<TrDetailPenjualanDto> findAllDetail(String noNota);
	public void saveDetail(TrDetailPenjualanDto dto); //hanya disediakan, tidak dipakai.
	
	public List<TrHeaderPenjualanDto> findAllHeaderPenjualan();
	public List<TrHeaderPenjualanDto> findAllHeaderPenjualan(String kataKunci);
	public void saveHeader(TrHeaderPenjualanDto dto);
	public void deleteHeaderDetail(String noNota);
	public TrHeaderPenjualanDto findOneHeaderDetail(String noNota);
}
