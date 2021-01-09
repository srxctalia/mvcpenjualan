package com.mvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mvc.dao.MstKaryawanDao;
import com.mvc.dto.MstKaryawanDto;
import com.mvc.entity.MstKaryawan;
import com.mvc.entity.MstKaryawanPK;
import com.mvc.service.MstKaryawanSvc;

@Service
@Transactional
public class MstKaryawanSvcImpl implements MstKaryawanSvc{

	@Autowired
	MstKaryawanDao daoKar;

	@Override
	public List<MstKaryawanDto> findAllKaryawan() {
		// TODO Auto-generated method stub
		List<MstKaryawanDto> dtos = new ArrayList<>();
		List<MstKaryawan> list = daoKar.findAll();
		for(MstKaryawan kar : list){
			MstKaryawanDto dto = new MstKaryawanDto();
			
			dto.setKodeKaryawan(kar.getKodeKaryawan());
			dto.setNamaKaryawan(kar.getNamaKaryawan());
			dto.setPassword(kar.getPassword());
			dto.setUsername(kar.getUsername());
			
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public MstKaryawanDto findOneKaryawan(String kodeKaryawan) {
		// TODO Auto-generated method stub
		MstKaryawanPK pk = new MstKaryawanPK();
		pk.setkodeKaryawan(kodeKaryawan);
		MstKaryawan kar = daoKar.findOne(pk);
		if(kar!= null){
			MstKaryawanDto dto = new MstKaryawanDto();
			
			dto.setKodeKaryawan(kar.getKodeKaryawan());
			dto.setNamaKaryawan(kar.getNamaKaryawan());
			dto.setPassword(kar.getPassword());
			dto.setUsername(kar.getUsername());
			
			return dto;
		}
		return null;
	}
	
	@Override
	public void saveKaryawan(MstKaryawanDto dto) {
		MstKaryawan kar = new MstKaryawan();
		kar.setKodeKaryawan(dto.getKodeKaryawan());
		kar.setNamaKaryawan(dto.getNamaKaryawan());
		kar.setUsername(dto.getUsername());
		kar.setPassword(dto.getPassword());
		daoKar.save(kar);
	}
	
	@Override
	public void deleteKaryawan(String kodeKaryawan){
		MstKaryawanPK pk = new MstKaryawanPK();
		pk.setkodeKaryawan(kodeKaryawan);
		daoKar.delete(pk);
	}
	
	@Override
	public Map<String, Object> listAllPageKaryawan(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 3;
		Pageable paging =
				new PageRequest(page - 1, perPage,
				new Sort(new Sort.Order(Direction.fromString("asc"),"namaKaryawan")));
		List<MstKaryawan> list = daoKar.search(cari, paging);
		List<MstKaryawanDto> dtos = new ArrayList<>();
		for(MstKaryawan kar : list){
			MstKaryawanDto dto = new MstKaryawanDto();
			dto.setKodeKaryawan(kar.getKodeKaryawan());
			dto.setNamaKaryawan(kar.getNamaKaryawan());
			dto.setPassword(kar.getPassword());
			dto.setUsername(kar.getUsername());
			dtos.add(dto);
		}
		int jumlahData = daoKar.countData(cari);
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
