package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.MstCustomer;
import com.mvc.entity.MstCustomerPK;

public interface MstCustomerDao extends JpaRepository<MstCustomer, MstCustomerPK>{
	@Query("select a, k.nama from MstCustomer a, MstKota k  "
			+ "where a.kodeKota = k.kodeKota AND (a.kodeCustomer like %:cari% or "
			+ "a.namaCustomer like %:cari% or k.namaKota like %:cari%)")
	public List<Object[]> search(@Param("cari") String cari, Pageable pageable);

	
	@Query("select count(a.kodeCustomer) from MstCustomer a, MStKota k "
			+ "where a.kodeKota = k.kodeKota AND (a.kodeCustomer like %:cari% or "
			+ "a.namaCustomer like %:cari% or k.namaKota like %:cari%)")
	public int countData(@Param("cari") String cari);
	
	@Query("select a, k.nama from MstCustomer a, MstKota k  "
			+ "where a.kodeKota = k.kodeKota AND a.kodeCustomer like %:kodeCustomer%")
	public Object[] findOneCustomer(@Param("kodeCustomer")String kodeCustomer);
}
