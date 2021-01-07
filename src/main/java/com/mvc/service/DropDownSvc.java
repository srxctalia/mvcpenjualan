package com.mvc.service;

import java.util.List;
import java.util.Map;

import com.mvc.dto.MstBarangDto;
import com.mvc.dto.MstCustomerDto;
import com.mvc.dto.MstKaryawanDto;
import com.mvc.dto.MstKotaDto;
import com.mvc.dto.MstProvinsiDto;
import com.mvc.dto.MstSupplierDto;



public interface DropDownSvc {
	//BARANG
	public List<MstBarangDto> findAllBarang();
	public MstBarangDto findOneBarang(String kodeBarang);
	public void saveBarang(MstBarangDto dto);
	public void deleteBarang(String kodeBarang);
	public Map<String, Object> listAllPageBarang(String cari, int page);
	//KARYAWAN
	public List<MstKaryawanDto> findAllKaryawan();
	public MstKaryawanDto findOneKaryawan(String kodeKaryawan);
	public void saveKaryawan(MstKaryawanDto dto);
	public void deleteKaryawan(String kodeKaryawan);
	public Map<String, Object> listAllPageKaryawan(String cari, int page);
	//CUSTOMER
	public List<MstCustomerDto> findAllCustomer();
	public MstCustomerDto findOneCustomer(String kodeCustomer);
	public void saveCustomer(MstCustomerDto dto);
	public void deleteCustomer(String kodeCustomer);
	public Map<String, Object> listAllPageCustomer(String cari, int page);
	//KOTA
	public List<MstKotaDto> findAllKota();
	public MstKotaDto findOneKota(String kodeKota);
	public void saveKota(MstKotaDto dto);
	public void deleteKota(String kodeKota);
	public Map<String, Object> listAllPageKota(String cari, int page);
	//PROVINSI
	public List<MstProvinsiDto> findAllProvinsi();
	public MstProvinsiDto findOneProvinsi(String kodeProvinsi);
	public void saveProvinsi(MstProvinsiDto dto);
	public void deleteProvinsi(String kodeProvinsi);
	public Map<String, Object> listAllPageProvinsi(String cari, int page);
	//SUPPLIER
	public List<MstSupplierDto> findAllSupplier();
	public MstSupplierDto findOneSupplier(String kodeSupplier);
	public void saveSupplier(MstSupplierDto dto);
	public void deleteSupplier(String kodeSupplier);
	public Map<String, Object> listAllPageSupplier(String cari, int page);
}
