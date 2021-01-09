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

import com.mvc.dao.MstProvinsiDao;
import com.mvc.dto.MstProvinsiDto;
import com.mvc.entity.MstProvinsi;
import com.mvc.entity.MstProvinsiPK;
import com.mvc.service.MstProvinsiSvc;

@Service
@Transactional
public class MstProvinsiSvcImpl implements MstProvinsiSvc{

	@Autowired
	MstProvinsiDao daoProv;
	
	@Override
	public List<MstProvinsiDto> findAllProvinsi() {
		// TODO Auto-generated method stub
		List<MstProvinsiDto> dtos = new ArrayList<>();
		List<MstProvinsi> list = daoProv.findAll();
		for(MstProvinsi prov : list){
			MstProvinsiDto dto = new MstProvinsiDto();
			
			dto.setKodeProvinsi(prov.getKodeProvinsi());
			dto.setNamaProvinsi(prov.getNamaProvinsi());
			
			dtos.add(dto);
		}
		return dtos;
	}
	

	@Override
	public MstProvinsiDto findOneProvinsi(String kodeProvinsi) {
		// TODO Auto-generated method stub
		MstProvinsiPK pk = new MstProvinsiPK();
		pk.setKodeProvinsi(kodeProvinsi);
		MstProvinsi prov = daoProv.findOne(pk);
		if(prov != null){
			MstProvinsiDto dto = new MstProvinsiDto();
			
			dto.setKodeProvinsi(prov.getKodeProvinsi());
			dto.setNamaProvinsi(prov.getNamaProvinsi());
			
			return dto;
		}
		return null;
	}
	
	@Override
	public void saveProvinsi(MstProvinsiDto dto) {
		MstProvinsi prov = new MstProvinsi();
		prov.setKodeProvinsi(dto.getKodeProvinsi());
		prov.setNamaProvinsi(dto.getNamaProvinsi());
		daoProv.save(prov);
	}
	
	@Override
	public void deleteProvinsi(String kodeProvinsi){
		MstProvinsiPK pk = new MstProvinsiPK();
		pk.setKodeProvinsi(kodeProvinsi);
		daoProv.delete(pk);
	}
	
	@Override
	public Map<String, Object> listAllPageProvinsi(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 3;
		Pageable paging =
				new PageRequest(page - 1, perPage,
				new Sort(new Sort.Order(Direction.fromString("asc"),"namaProvinsi")));
		List<MstProvinsi> list = daoProv.search(cari, paging);
		List<MstProvinsiDto> dtos = new ArrayList<>();
		for(MstProvinsi prov : list){
			MstProvinsiDto dto = new MstProvinsiDto();
			dto.setKodeProvinsi(prov.getKodeProvinsi());
			dto.setNamaProvinsi(prov.getNamaProvinsi());
			dtos.add(dto);
		}
		int jumlahData = daoProv.countData(cari);
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