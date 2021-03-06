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
import com.mvc.dto.MstCustomerDto;
import com.mvc.entity.MstCustomer;
import com.mvc.entity.MstCustomerPK;
import com.mvc.service.MstCustomerSvc;


@Service
@Transactional
public class MstCustomerSvcImpl implements MstCustomerSvc {

	@Autowired
	private MstCustomerDao dao;
	
	@Override
	public Map<String, Object> listAll(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>(); 
		int perPage = 3;
		Pageable paging = new PageRequest(page-1, perPage, new Sort(new Sort.Order(Direction.fromString("asc"), "namaCustomer")));
		List<Object[]> list = dao.search(cari, paging);
		List<MstCustomerDto> dtos = new ArrayList<>();
		for (Object[] o : list){
			MstCustomerDto dto = new MstCustomerDto();
			MstCustomer c = (MstCustomer) o[0];
			dto.setKodeCustomer(c.getKodeCustomer());
			dto.setNamaCustomer(c.getNamaCustomer());
			dto.setKodeKota(c.getKodeKota());
			dto.setJenisKelamin(c.getJenisKelamin());
			dto.setEmailCustomer(c.getEmailCustomer());
			dto.setAlamatCustomer(c.getAlamatCustomer());
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
	
	@Override
	public void save(MstCustomerDto dto) {
		// TODO Auto-generated method stub
		MstCustomer cs = new MstCustomer();
		cs.setKodeCustomer(dto.getKodeCustomer());
		cs.setNamaCustomer(dto.getNamaCustomer());
		cs.setJenisKelamin(dto.getJenisKelamin());
		cs.setEmailCustomer(dto.getEmailCustomer());
		cs.setAlamatCustomer(dto.getAlamatCustomer());
		cs.setKodeKota(dto.getKodeKota());
		dao.save(cs);

	}

	@Override
	public void update(MstCustomerDto dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String kodeCustomer) {
		// TODO Auto-generated method stub
		MstCustomerPK pk = new MstCustomerPK();
		pk.setKodeCostumer(kodeCustomer);
		dao.delete(pk);
	}

	@Override
	public MstCustomerDto findOne(String kodeCustomer) {
		// TODO Auto-generated method stub
		MstCustomerPK pk = new MstCustomerPK();
		pk.setKodeCostumer(kodeCustomer);
		MstCustomer cs = dao.findOne(pk);
		if (cs != null){
			MstCustomerDto dto = new MstCustomerDto();
			dto.setKodeCustomer(cs.getKodeCustomer());
			dto.setNamaCustomer(cs.getNamaCustomer());
			dto.setJenisKelamin(cs.getJenisKelamin());
			dto.setEmailCustomer(cs.getEmailCustomer());
			dto.setAlamatCustomer(cs.getAlamatCustomer());
			dto.setKodeKota(cs.getKodeKota());
			return dto;
		}
		return null;
	}

	@Override
	public List<MstCustomerDto> findAll() {
		// TODO Auto-generated method stub
		List<MstCustomer> cs = dao.findAll();
		List<MstCustomerDto> dtos = new ArrayList<>();
		for (MstCustomer c : cs){
			MstCustomerDto dto = new MstCustomerDto();
			dto.setKodeCustomer(c.getKodeCustomer());
			dto.setNamaCustomer(c.getNamaCustomer());
			dto.setJenisKelamin(c.getJenisKelamin());
			dto.setAlamatCustomer(c.getAlamatCustomer());
			dto.setKodeKota(c.getKodeKota());
			dtos.add(dto);
		}
		return dtos;
	}

}
