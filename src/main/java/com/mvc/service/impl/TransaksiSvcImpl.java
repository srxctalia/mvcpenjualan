package com.mvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
		List<Object[]> list = daoD.search(noNota);
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
	public void saveHeader(TrHeaderPenjualanDto dto) {
		// TODO Auto-generated method stub
		TrHeaderPenjualan th = new TrHeaderPenjualan();

		th.setGlobalDiskon(dto.getGlobalDiskon());
		th.setHargaTotal(dto.getHargaTotal());
		th.setKodeCustomer(dto.getKodeCustomer());
		th.setKodeKaryawan(dto.getKodeKaryawan());
		th.setNoNota(dto.getNoNota());
		th.setTanggalTransaksi(dto.getTanggalTransaksi());
		
		daoH.save(th);
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
		Object[] o = daoH.findOnePenjualan(noNota);
		
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
		return null;
	}

	@Override
	public Map<String, Object> listAll(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 1;
		Pageable paging = new PageRequest(page-1, perPage, new Sort(new Sort.Order(Direction.fromString("asc"), "noNota")));
		List<Object[]> list = daoH.search(cari, paging);
		List<TrHeaderPenjualanDto> dtos = new ArrayList<>();
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
		
		int jumlahData = daoH.countData(cari);
		int jumlahHalaman = 0;
		jumlahHalaman = jumlahData/perPage;
		if (jumlahData % perPage > 0){
			jumlahHalaman++;
		}
		map.put("list", dtos);
		map.put("jumlah", jumlahHalaman);
		return map;
	}

}
