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
import com.mvc.dao.MstCustomerDao;
import com.mvc.dao.MstKaryawanDao;
import com.mvc.dao.MstKotaDao;
import com.mvc.dao.MstProvinsiDao;
import com.mvc.dao.MstSupplierDao;
import com.mvc.dto.MstBarangDto;
import com.mvc.dto.MstCustomerDto;
import com.mvc.dto.MstKaryawanDto;
import com.mvc.dto.MstKotaDto;
import com.mvc.dto.MstProvinsiDto;
import com.mvc.dto.MstSupplierDto;
import com.mvc.entity.MstBarang;
import com.mvc.entity.MstBarangPK;
import com.mvc.entity.MstCustomer;
import com.mvc.entity.MstCustomerPK;
import com.mvc.entity.MstKaryawan;
import com.mvc.entity.MstKaryawanPK;
import com.mvc.entity.MstKota;
import com.mvc.entity.MstKotaPK;
import com.mvc.entity.MstProvinsi;
import com.mvc.entity.MstProvinsiPK;
import com.mvc.entity.MstSupplier;
import com.mvc.entity.MstSupplierPK;
import com.mvc.service.DropDownSvc;

@Service
@Transactional
public class DropDownSvcImpl implements DropDownSvc {
	
	@Autowired
	MstBarangDao daoBar;
	@Autowired
	MstKaryawanDao daoKar;
	@Autowired
	MstCustomerDao daoCus;
	@Autowired
	MstKotaDao daoKot;
	@Autowired
	MstProvinsiDao daoProv;
	@Autowired
	MstSupplierDao daoSup;
	
	
	@Override
	public List<MstBarangDto> findAllBarang() {
		// TODO Auto-generated method stub
		List<MstBarangDto> dtos = new ArrayList<>();
		List<MstBarang> list = daoBar.findAll();
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
		MstBarang bar = daoBar.findOne(pk);
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
		daoBar.save(bar);
	}
	
	@Override
	public void deleteBarang(String kodeBarang){
		MstBarangPK pk = new MstBarangPK();
		pk.setKodeBarang(kodeBarang);
		daoBar.delete(pk);
	}
	
	@Override
	public Map<String, Object> listAllPageBarang(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 3;
		Pageable paging =
				new PageRequest(page - 1, perPage,
				new Sort(new Sort.Order(Direction.fromString("asc"),"namaBarang")));
		List<MstBarang> list = daoBar.search(cari, paging);
		List<MstBarangDto> dtos = new ArrayList<>();
		for(MstBarang bar : list){
			MstBarangDto dto = new MstBarangDto();
			dto.setKodeBarang(bar.getKodeBarang());
			dto.setKodeSupplier(bar.getKodeSupplier());
			dto.setNamaBarang(bar.getNamaBarang());
			dto.setStokBarang(bar.getStokBarang());
			dtos.add(dto);
		}
		int jumlahData = daoBar.countData(cari);
		int jumlahHalaman = 0;
		jumlahHalaman = (jumlahData / perPage);
		if(jumlahData % perPage > 0){
			jumlahHalaman++;
		}
		map.put("list", dtos);
		map.put("jumlah", jumlahHalaman);
		return map;
	}

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

	@Override
	public List<MstCustomerDto> findAllCustomer() {
		// TODO Auto-generated method stub
		List<MstCustomerDto> dtos = new ArrayList<>();
		List<MstCustomer> list = daoCus.findAll();
		for(MstCustomer cus : list){
			MstCustomerDto dto = new MstCustomerDto();
			
			dto.setAlamatCustomer(cus.getAlamatCustomer());
			dto.setEmailCustomer(cus.getEmailCustomer());
			dto.setJenisKelamin(cus.getJenisKelamin());
			dto.setKodeCustomer(cus.getKodeCustomer());
			dto.setKodeKota(cus.getKodeKota());
			dto.setNamaCustomer(cus.getNamaCustomer());
			
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public MstCustomerDto findOneCustomer(String kodeCustomer) {
		// TODO Auto-generated method stub
		MstCustomerPK pk = new MstCustomerPK();
		pk.setKodeCostumer(kodeCustomer);
		MstCustomer cus = daoCus.findOne(pk);
		if(cus != null){
			MstCustomerDto dto = new MstCustomerDto();
			
			dto.setAlamatCustomer(cus.getAlamatCustomer());
			dto.setEmailCustomer(cus.getEmailCustomer());
			dto.setJenisKelamin(cus.getJenisKelamin());
			dto.setKodeCustomer(cus.getKodeCustomer());
			dto.setKodeKota(cus.getKodeKota());
			dto.setNamaCustomer(cus.getNamaCustomer());
			
			return dto;
		}
		return null;
	}
	
	@Override
	public void saveCustomer(MstCustomerDto dto) {
		MstCustomer cus = new MstCustomer();
		cus.setKodeCustomer(dto.getKodeCustomer());
		cus.setNamaCustomer(dto.getNamaCustomer());
		cus.setAlamatCustomer(dto.getAlamatCustomer());
		cus.setEmailCustomer(dto.getEmailCustomer());
		cus.setJenisKelamin(dto.getJenisKelamin());
		cus.setKodeKota(dto.getKodeKota());
		daoCus.save(cus);
	}
	
	@Override
	public void deleteCustomer(String kodeCustomer){
		MstCustomerPK pk = new MstCustomerPK();
		pk.setKodeCostumer(kodeCustomer);
		daoCus.delete(pk);
	}
	
	@Override
	public Map<String, Object> listAllPageCustomer(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 3;
		Pageable paging =
				new PageRequest(page - 1, perPage,
				new Sort(new Sort.Order(Direction.fromString("asc"),"namaCustomer")));
		List<MstCustomer> list = daoCus.search(cari, paging);
		List<MstCustomerDto> dtos = new ArrayList<>();
		for(MstCustomer cus : list){
			MstCustomerDto dto = new MstCustomerDto();
			dto.setAlamatCustomer(cus.getAlamatCustomer());
			dto.setEmailCustomer(cus.getEmailCustomer());
			dto.setJenisKelamin(cus.getJenisKelamin());
			dto.setKodeCustomer(cus.getKodeCustomer());
			dto.setKodeKota(cus.getKodeKota());
			dto.setNamaCustomer(cus.getNamaCustomer());
			dtos.add(dto);
		}
		int jumlahData = daoCus.countData(cari);
		int jumlahHalaman = 0;
		jumlahHalaman = (jumlahData / perPage);
		if(jumlahData % perPage > 0){
			jumlahHalaman++;
		}
		map.put("list", dtos);
		map.put("jumlah", jumlahHalaman);
		return map;
	}
	
	@Override
	public List<MstKotaDto> findAllKota() {
		// TODO Auto-generated method stub
		List<MstKotaDto> dtos = new ArrayList<>();
		List<MstKota> list = daoKot.findAll();
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
		MstKota kot = daoKot.findOne(pk);
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
		daoKot.save(kot);
	}
	
	@Override
	public void deleteKota(String kodeKota){
		MstKotaPK pk = new MstKotaPK();
		pk.setKodeKota(kodeKota);
		daoKot.delete(pk);
	}
	
	@Override
	public Map<String, Object> listAllPageKota(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 3;
		Pageable paging =
				new PageRequest(page - 1, perPage,
				new Sort(new Sort.Order(Direction.fromString("asc"),"namaKota")));
		List<MstKota> list = daoKot.search(cari, paging);
		List<MstKotaDto> dtos = new ArrayList<>();
		for(MstKota kot : list){
			MstKotaDto dto = new MstKotaDto();
			dto.setKodeKota(kot.getKodeKota());
			dto.setNamaKota(kot.getNamaKota());
			dto.setKodeProvinsi(kot.getKodeProvinsi());
			dtos.add(dto);
		}
		int jumlahData = daoKot.countData(cari);
		int jumlahHalaman = 0;
		jumlahHalaman = (jumlahData / perPage);
		if(jumlahData % perPage > 0){
			jumlahHalaman++;
		}
		map.put("list", dtos);
		map.put("jumlah", jumlahHalaman);
		return map;
	}
	
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
	
	@Override
	public List<MstSupplierDto> findAllSupplier() {
		// TODO Auto-generated method stub
		List<MstSupplierDto> dtos = new ArrayList<>();
		List<MstSupplier> list = daoSup.findAll();
		for(MstSupplier sup : list){
			MstSupplierDto dto = new MstSupplierDto();
			
			dto.setAlamatSupplier(sup.getAlamatSupplier());
			dto.setEmailSupplier(sup.getEmailSupplier());
			dto.setKodeKota(sup.getKodeKota());
			dto.setKodeSupplier(sup.getKodeSupplier());
			dto.setNamaSupplier(sup.getNamaSupplier());
			dto.setTelpSupplier(sup.getTelpSupplier());
			
			
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public MstSupplierDto findOneSupplier(String kodeSupplier) {
		// TODO Auto-generated method stub
		MstSupplierPK pk = new MstSupplierPK();
		pk.setKodeSupplier(kodeSupplier);
		MstSupplier sup = daoSup.findOne(pk);
		if(sup != null){
			MstSupplierDto dto = new MstSupplierDto();
			
			dto.setAlamatSupplier(sup.getAlamatSupplier());
			dto.setEmailSupplier(sup.getEmailSupplier());
			dto.setKodeKota(sup.getKodeKota());
			dto.setKodeSupplier(sup.getKodeSupplier());
			dto.setNamaSupplier(sup.getNamaSupplier());
			dto.setTelpSupplier(sup.getTelpSupplier());
			
			return dto;
		}
		return null;
	}
	
	@Override
	public void saveSupplier(MstSupplierDto dto) {
		MstSupplier sup = new MstSupplier();
		sup.setAlamatSupplier(dto.getAlamatSupplier());
		sup.setEmailSupplier(dto.getEmailSupplier());
		sup.setKodeKota(dto.getKodeKota());
		sup.setKodeSupplier(dto.getKodeSupplier());
		sup.setNamaSupplier(dto.getNamaSupplier());
		sup.setTelpSupplier(dto.getTelpSupplier());
		daoSup.save(sup);
	}
	
	@Override
	public void deleteSupplier(String kodeSupplier){
		MstSupplierPK pk = new MstSupplierPK();
		pk.setKodeSupplier(kodeSupplier);
		daoSup.delete(pk);
	}
	
	@Override
	public Map<String, Object> listAllPageSupplier(String cari, int page) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int perPage = 3;
		Pageable paging =
				new PageRequest(page - 1, perPage,
				new Sort(new Sort.Order(Direction.fromString("asc"),"namaSupplier")));
		List<MstSupplier> list = daoSup.search(cari, paging);
		List<MstSupplierDto> dtos = new ArrayList<>();
		for(MstSupplier sup : list){
			MstSupplierDto dto = new MstSupplierDto();
			dto.setAlamatSupplier(sup.getAlamatSupplier());
			dto.setEmailSupplier(sup.getEmailSupplier());
			dto.setKodeKota(sup.getKodeKota());
			dto.setKodeSupplier(sup.getKodeSupplier());
			dto.setNamaSupplier(sup.getNamaSupplier());
			dto.setTelpSupplier(sup.getTelpSupplier());
			dtos.add(dto);
		}
		int jumlahData = daoSup.countData(cari);
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
