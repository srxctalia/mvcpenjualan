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

import com.mvc.dao.MstKotaDao;
import com.mvc.dto.MstKotaDto;
import com.mvc.entity.MstKota;
import com.mvc.entity.MstKotaPK;
import com.mvc.service.MstKotaSvc;

@Service
@Transactional
public class MstKotaSvcImpl implements MstKotaSvc{

	@Autowired
	MstKotaDao dao;
	
	@Override
	public List<MstKotaDto> findAllKota() {
		// TODO Auto-generated method stub
		List<MstKotaDto> dtos = new ArrayList<>();
		List<MstKota> list = dao.findAll();
		for(MstKota kot : list){
			MstKotaDto dto = new MstKotaDto();
			
			dto.setKodeKota(kot.getKodeKota());
			dto.setNamaKota(kot.getNamaKota());
			dto.setKodeProvinsi(kot.getKodeProvinsi());
			
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public MstKotaDto findOneKota(String kodeKota) {
		// TODO Auto-generated method stub
		MstKotaPK pk = new MstKotaPK();
		pk.setKodeKota(kodeKota);
		MstKota kot = dao.findOne(pk);
		if(kot != null){
			MstKotaDto dto = new MstKotaDto();
			
			dto.setKodeKota(kot.getKodeKota());
			dto.setNamaKota(kot.getNamaKota());
			dto.setKodeProvinsi(kot.getKodeProvinsi());
			
			return dto;
		}
		return null;
	}
	
	@Override
	public void saveKota(MstKotaDto dto) {
		MstKota kot = new MstKota();
		kot.setKodeKota(dto.getKodeKota());
		kot.setKodeProvinsi(dto.getKodeProvinsi());
		kot.setNamaKota(dto.getNamaKota());
		dao.save(kot);
	}
	
	@Override
	public void deleteKota(String kodeKota){
		MstKotaPK pk = new MstKotaPK();
		pk.setKodeKota(kodeKota);
		dao.delete(pk);
	}
	
	@Override
	public Map<String, Object> listAllPageKota(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 3;
		Pageable paging =
				new PageRequest(page - 1, perPage,
				new Sort(new Sort.Order(Direction.fromString("asc"),"namaKota")));
		List<MstKota> list = dao.search(cari, paging);
		List<MstKotaDto> dtos = new ArrayList<>();
		for(MstKota kot : list){
			MstKotaDto dto = new MstKotaDto();
			dto.setKodeKota(kot.getKodeKota());
			dto.setNamaKota(kot.getNamaKota());
			dto.setKodeProvinsi(kot.getKodeProvinsi());
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
