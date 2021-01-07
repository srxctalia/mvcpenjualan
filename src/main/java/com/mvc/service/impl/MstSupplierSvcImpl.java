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

import com.mvc.dao.MstSupplierDao;
import com.mvc.dto.MstSupplierDto;
import com.mvc.entity.MstSupplier;
import com.mvc.entity.MstSupplierPK;
import com.mvc.service.MstSupplierSvc;

@Service
@Transactional
public class MstSupplierSvcImpl implements MstSupplierSvc {

	@Autowired
	private MstSupplierDao dao;
	
	@Override
	public void save(MstSupplierDto dto) {
		// TODO Auto-generated method stub
		MstSupplier cs = new MstSupplier();
		cs.setKodeSupplier(dto.getKodeSupplier());
		cs.setNamaSupplier(dto.getNamaSupplier());
		cs.setEmailSupplier(dto.getEmailSupplier());
		cs.setAlamatSupplier(dto.getAlamatSupplier());
		cs.setKodeKota(dto.getKodeKota());
		cs.setTelpSupplier(dto.getTelpSupplier());
		dao.save(cs);

	}

	@Override
	public void update(MstSupplierDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String kodeSupplier) {
		// TODO Auto-generated method stub
		MstSupplierPK pk = new MstSupplierPK();
		pk.setKodeSupplier(kodeSupplier);
		dao.delete(pk);
	}

	@Override
	public MstSupplierDto findOne(String kodeSupplier) {
		// TODO Auto-generated method stub
		Object[] o = dao.findOne(kodeSupplier);
		if (o != null){
			MstSupplier cs = (MstSupplier) o[0];
			MstSupplierDto dto = new MstSupplierDto();
			dto.setKodeSupplier(cs.getKodeSupplier());
			dto.setNamaSupplier(cs.getNamaSupplier());
			dto.setTelpSupplier(cs.getTelpSupplier());
			dto.setEmailSupplier(cs.getEmailSupplier());
			dto.setAlamatSupplier(cs.getAlamatSupplier());
			dto.setKodeKota(cs.getKodeKota());
			dto.setNamaKota((String)o[1]);
			return dto;
		}
		return null;
	}

	@Override
	public Map<String, Object> listAll(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>(); 
		int perPage = 1;
		Pageable paging = new PageRequest(page-1, perPage, new Sort(new Sort.Order(Direction.fromString("asc"), "namaSupplier")));
		List<Object[]> list = dao.search(cari, paging);
		List<MstSupplierDto> dtos = new ArrayList<>();
		for (Object[] o : list){
			MstSupplier cs = (MstSupplier) o[0];
			MstSupplierDto dto = new MstSupplierDto();
			dto.setKodeSupplier(cs.getKodeSupplier());
			dto.setNamaSupplier(cs.getNamaSupplier());
			dto.setTelpSupplier(cs.getTelpSupplier());
			dto.setEmailSupplier(cs.getEmailSupplier());
			dto.setAlamatSupplier(cs.getAlamatSupplier());
			dto.setKodeKota(cs.getKodeKota());
			dto.setNamaKota((String)o[1]);
			dtos.add(dto);
		}
		
		int jumlahData = dao.countData(cari);
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
