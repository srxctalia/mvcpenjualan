package com.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.TrDetailPenjualanDao;
import com.mvc.dao.TrHeaderPenjualanDao;
import com.mvc.dto.TrDetailPenjualanDto;
import com.mvc.dto.TrHeaderPenjualanDto;
import com.mvc.entity.TrDetailPenjualan;
import com.mvc.entity.TrHeaderPenjualan;
import com.mvc.entity.TrHeaderPenjualanPK;
import com.mvc.service.TransaksiSvc;

@Service
@Transactional
public class TransaksiSvcImpl implements TransaksiSvc {

	@Autowired
	TrDetailPenjualanDao daoD;
	@Autowired
	TrHeaderPenjualanDao daoH;
	
	@Override
	public List<TrDetailPenjualanDto> findAllDetail(String noNota) {
		// TODO Auto-generated method stub
		List<TrDetailPenjualanDto> dtos = new ArrayList<>();
		List<Object[]> list = daoD.findAllDetailPenjualanBySearch(noNota);
		for(Object[] o : list){
			TrDetailPenjualanDto dto = new TrDetailPenjualanDto();
			TrDetailPenjualan td = (TrDetailPenjualan) o[0];
			
			dto.setDiskon(td.getDiskon());
			dto.setHargaSatuan(td.getHargaSatuan());
			dto.setKodeBarang(td.getKodeBarang());
			dto.setKodeDetail(td.getKodeDetail());
			dto.setNoNota(td.getNoNota());
			dto.setQty(td.getQty());
			dto.setSubtotal(td.getSubtotal());
			dto.setNamaBarang((String) o[1]);
			
			dtos.add(dto);
		}
		return dtos;
	}
	
	@Override
	public void saveDetail(TrDetailPenjualanDto dto) {
		// TODO Auto-generated method stub

		TrDetailPenjualan td = new TrDetailPenjualan();
		
		td.setDiskon(dto.getDiskon());
		td.setHargaSatuan(dto.getHargaSatuan());
		td.setKodeBarang(dto.getKodeBarang());
		td.setKodeDetail(dto.getKodeDetail());
		td.setNoNota(dto.getNoNota());
		td.setQty(dto.getQty());
		td.setSubtotal(dto.getSubtotal());
		
		daoD.save(td);
	}

	@Override
	public List<TrHeaderPenjualanDto> findAllHeaderPenjualan() {
		// TODO Auto-generated method stub
		List<TrHeaderPenjualanDto> dtos = new ArrayList<>();
		List<Object[]> list = daoH.findAllPenjualanWithName();
		for(Object[] o : list){
			TrHeaderPenjualanDto dto = new TrHeaderPenjualanDto();
			TrHeaderPenjualan th = (TrHeaderPenjualan) o[0];
			
			dto.setGlobalDiskon(th.getGlobalDiskon());
			dto.setHargaTotal(th.getHargaTotal());
			dto.setKodeCustomer(th.getKodeCustomer());
			dto.setKodeKaryawan(th.getKodeKaryawan());
			dto.setNoNota(th.getNoNota());
			dto.setTanggalTransaksi(th.getTanggalTransaksi());
			dto.setNamaCustomer((String) o[2]);
			dto.setNamaKaryawan((String) o[1]);
			
			
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<TrHeaderPenjualanDto> findAllHeaderPenjualan(String kataKunci) {
		// TODO Auto-generated method stub
		List<TrHeaderPenjualanDto> dtos = new ArrayList<>();
		List<Object[]> list = daoH.findAllPenjualanBySearch(kataKunci);
		for(Object[] o : list){
			TrHeaderPenjualanDto dto = new TrHeaderPenjualanDto();
			TrHeaderPenjualan th = (TrHeaderPenjualan) o[0];
			
			dto.setGlobalDiskon(th.getGlobalDiskon());
			dto.setHargaTotal(th.getHargaTotal());
			dto.setKodeCustomer(th.getKodeCustomer());
			dto.setKodeKaryawan(th.getKodeKaryawan());
			dto.setNoNota(th.getNoNota());
			dto.setTanggalTransaksi(th.getTanggalTransaksi());
			dto.setNamaCustomer((String) o[2]);
			dto.setNamaKaryawan((String) o[1]);
			
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void saveHeader(TrHeaderPenjualanDto dto) {
		// TODO Auto-generated method stub
		TrHeaderPenjualan th = new TrHeaderPenjualan();

		th.setGlobalDiskon(dto.getGlobalDiskon());
		th.setHargaTotal(dto.getHargaTotal());
		th.setKodeCustomer(dto.getKodeCustomer());
		th.setKodeKaryawan(dto.getKodeKaryawan());
		th.setNoNota(dto.getNoNota());
		th.setTanggalTransaksi(dto.getTanggalTransaksi());
		
		for(TrDetailPenjualanDto tdp : dto.getDetailTransaksi()){
			TrDetailPenjualan td = new TrDetailPenjualan();
			td.setDiskon(tdp.getDiskon());
			td.setHargaSatuan(tdp.getHargaSatuan());
			td.setKodeBarang(tdp.getKodeBarang());
			td.setKodeDetail(tdp.getKodeDetail());
			td.setNoNota(tdp.getNoNota());
			td.setQty(tdp.getQty());
			td.setSubtotal(tdp.getSubtotal());
			daoD.save(td);
		}
		daoH.save(th);
	}

	@Override
	public void deleteHeaderDetail(String noNota) {
		// TODO Auto-generated method stub
		TrHeaderPenjualanPK pk = new TrHeaderPenjualanPK();
		pk.setNoNota(noNota);
		TrHeaderPenjualanDto dto = new TrHeaderPenjualanDto();
		dto.setNoNota(noNota);
		daoD.deleteDetail(noNota);
		daoH.delete(pk);
	}

	@Override
	public TrHeaderPenjualanDto findOneHeaderDetail(String noNota) {
		// TODO Auto-generated method stub
		TrHeaderPenjualanPK pk = new TrHeaderPenjualanPK();
		pk.setNoNota(noNota);
		List<Object[]> list = daoH.findOnePenjualanBySearch(noNota);
		
		for(Object[] o : list){
			TrHeaderPenjualanDto dto = new TrHeaderPenjualanDto();
			TrHeaderPenjualan th = (TrHeaderPenjualan) o[0];
			if(th != null){
				dto.setGlobalDiskon(th.getGlobalDiskon());
				dto.setHargaTotal(th.getHargaTotal());
				dto.setKodeCustomer(th.getKodeCustomer());
				dto.setKodeKaryawan(th.getKodeKaryawan());
				dto.setNoNota(th.getNoNota());
				dto.setTanggalTransaksi(th.getTanggalTransaksi());
				dto.setNamaCustomer((String) o[2]);
				dto.setNamaKaryawan((String) o[1]);
	
				dto.setDetailTransaksi(findAllDetail(noNota));
				return dto;
			}
		}
		return null;
	}

}
