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

import com.mvc.dao.MstBarangDao;
import com.mvc.dto.MstBarangDto;
import com.mvc.entity.MstBarang;
import com.mvc.entity.MstBarangPK;
import com.mvc.service.MstBarangSvc;

@Service
@Transactional
public class MstBarangSvcImpl implements MstBarangSvc{
	@Autowired
	MstBarangDao dao;
	
	@Override
	public List<MstBarangDto> findAllBarang() {
		// TODO Auto-generated method stub
		List<MstBarangDto> dtos = new ArrayList<>();
		List<MstBarang> list = dao.findAll();
		for(MstBarang bar : list){
			MstBarangDto dto = new MstBarangDto();
			
			dto.setKodeBarang(bar.getKodeBarang());
			dto.setKodeSupplier(bar.getKodeSupplier());
			dto.setNamaBarang(bar.getNamaBarang());
			dto.setStokBarang(bar.getStokBarang());
			
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public MstBarangDto findOneBarang(String kodeBarang) {
		// TODO Auto-generated method stub
		MstBarangPK pk = new MstBarangPK();
		pk.setKodeBarang(kodeBarang);
		MstBarang bar = dao.findOne(pk);
		if(bar != null){
			MstBarangDto dto = new MstBarangDto();
			
			dto.setKodeBarang(bar.getKodeBarang());
			dto.setKodeSupplier(bar.getKodeSupplier());
			dto.setNamaBarang(bar.getNamaBarang());
			dto.setStokBarang(bar.getStokBarang());
			
			return dto;
		}
		return null;
	}
	
	@Override
	public void saveBarang(MstBarangDto dto) {
		MstBarang bar = new MstBarang();
		bar.setKodeBarang(dto.getKodeBarang());
		bar.setKodeSupplier(dto.getKodeSupplier());
		bar.setNamaBarang(dto.getNamaBarang());
		bar.setStokBarang(dto.getStokBarang());
		dao.save(bar);
	}
	
	@Override
	public void deleteBarang(String kodeBarang){
		MstBarangPK pk = new MstBarangPK();
		pk.setKodeBarang(kodeBarang);
		dao.delete(pk);
	}
	
	@Override
	public Map<String, Object> listAllPageBarang(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 3;
		Pageable paging =
				new PageRequest(page - 1, perPage,
				new Sort(new Sort.Order(Direction.fromString("asc"),"namaBarang")));
		List<Object[]> list = dao.search(cari, paging);
		List<MstBarangDto> dtos = new ArrayList<>();
		for(Object[] o : list){
			MstBarang bar = (MstBarang) o[0];
			String namaSupplier = (String) o[1];
			MstBarangDto dto = new MstBarangDto();
			dto.setKodeBarang(bar.getKodeBarang());
			dto.setKodeSupplier(bar.getKodeSupplier());
			dto.setNamaBarang(bar.getNamaBarang());
			dto.setStokBarang(bar.getStokBarang());
			dto.setNamaSupplier(namaSupplier);
			dtos.add(dto);
		}
		
		int jumlahData = dao.countData(cari);
		int jumlahHalaman = 0;
		jumlahHalaman = (jumlahData / perPage);
		if(jumlahData % perPage > 0){
			jumlahHalaman++;
		}
		map.put("list", dtos);
		map.put("jumlah", jumlahHalaman);
		return map;
	}
	
}
