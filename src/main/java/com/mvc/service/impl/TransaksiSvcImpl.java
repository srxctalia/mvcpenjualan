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

import com.mvc.dao.MstCustomerDao;
import com.mvc.dao.TrDetailPenjualanDao;
import com.mvc.dao.TrHeaderPenjualanDao;
import com.mvc.dto.TrDetailPenjualanDto;
import com.mvc.dto.TrHeaderPenjualanDto;
import com.mvc.entity.TrDetailPenjualan;
import com.mvc.entity.TrDetailPenjualanPK;
import com.mvc.entity.TrHeaderPenjualan;
import com.mvc.entity.TrHeaderPenjualanPK;
import com.mvc.service.MstCustomerSvc;
import com.mvc.service.MstKaryawanSvc;
import com.mvc.service.TransaksiSvc;

@Service
@Transactional
public class TransaksiSvcImpl implements TransaksiSvc {

	@Autowired
	TrDetailPenjualanDao daoD;
	@Autowired
	TrHeaderPenjualanDao daoH;
	@Autowired
	MstCustomerSvc svcC;
	@Autowired
	MstKaryawanSvc svcK;
	
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
			td.setNoNota(dto.getNoNota());
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
		TrHeaderPenjualanPK pk = new TrHeaderPenjualanPK();
		pk.setNoNota(noNota);
		TrHeaderPenjualan th = daoH.findOne(pk);
		
		if(th != null){
			TrHeaderPenjualanDto dto = new TrHeaderPenjualanDto();
			dto.setGlobalDiskon(th.getGlobalDiskon());
			dto.setHargaTotal(th.getHargaTotal());
			dto.setKodeCustomer(th.getKodeCustomer());
			dto.setKodeKaryawan(th.getKodeKaryawan());
			dto.setNoNota(th.getNoNota());
			dto.setTanggalTransaksi(th.getTanggalTransaksi());
			dto.setNamaCustomer(svcC.findOne(th.getKodeCustomer()).getNamaCustomer());
			dto.setNamaKaryawan(svcK.findOneKaryawan(th.getKodeKaryawan()).getNamaKaryawan());

			dto.setDetailTransaksi(findAllDetail(noNota));
			return dto;
		}	
		return null;
	}

	@Override
	public Map<String, Object> listAll(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 3;
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

	@Override
	public TrDetailPenjualanDto findOneDetaiil(String kodeDetail) {
		// TODO Auto-generated method stub
		TrDetailPenjualanPK pk = new TrDetailPenjualanPK();
		pk.setKodeDetail(kodeDetail);
		TrDetailPenjualan td = daoD.findOne(pk);
		if (td != null){
			TrDetailPenjualanDto dto = new TrDetailPenjualanDto();
			dto.setKodeDetail(td.getKodeDetail());
			dto.setKodeBarang(td.getKodeBarang());
			dto.setHargaSatuan(td.getHargaSatuan());
			dto.setQty(td.getQty());
			dto.setDiskon(td.getDiskon());
			dto.setSubtotal(td.getSubtotal());
			dto.setNoNota(td.getNoNota());
			return dto;
		}
		return null;
	}

	@Override
	public List<TrHeaderPenjualanDto> findAllHeader() {
		// TODO Auto-generated method stub
		List<TrHeaderPenjualanDto> dtos = new ArrayList<>();
		List<Object[]> list = daoH.findAllHeader();
		for (Object[] o : list){
			TrHeaderPenjualanDto dto = new TrHeaderPenjualanDto();
			TrHeaderPenjualan thp = (TrHeaderPenjualan)o[0];
			
			dto.setNoNota(thp.getNoNota());
			dto.setTanggalTransaksi(thp.getTanggalTransaksi());
			dto.setKodeCustomer(thp.getKodeCustomer());
			dto.setNamaCustomer((String)o[1]);
			dto.setHargaTotal(thp.getHargaTotal());
			dto.setGlobalDiskon(thp.getGlobalDiskon());
			dto.setKodeKaryawan(thp.getKodeKaryawan());
			dto.setNamaKaryawan((String) o[2]);
			
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void deleteDetail(String kodeDetail) {
		// TODO Auto-generated method stub
		TrDetailPenjualanPK pk = new TrDetailPenjualanPK();
		pk.setKodeDetail(kodeDetail);
		daoD.delete(pk);
	}

	@Override
	public void deleteAllDetailByNoNota(String noNota) {
		// TODO Auto-generated method stub
		List<TrDetailPenjualanDto> listDetail = findAllDetail(noNota);
		for (TrDetailPenjualanDto detail : listDetail){
			daoD.deleteDetail(detail.getNoNota());
		}
		
	}

}
