package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.MstSupplier;
import com.mvc.entity.MstSupplierPK;

public interface MstSupplierDao extends JpaRepository<MstSupplier, MstSupplierPK> {

	@Query("select a, k.nama from MstSupplier a, MstKota k  "
			+ "where a.kodeKota = k.kodeKota AND (a.kodeSupplier like %:cari% or "
			+ "a.namaSupplier like %:cari% or k.namaKota like %:cari%)")
	public List<Object[]> search(@Param("cari") String cari, Pageable pageable);

	
	@Query("select count(a.kodeSupplier) from MstSupplier a, MStKota k "
			+ "where a.kodeKota = k.kodeKota AND (a.kodeSupplier like %:cari% or "
			+ "a.namaSupplier like %:cari% or k.namaKota like %:cari%)")
	public int countData(@Param("cari") String cari);
	
	@Query("select a, k.nama from MstSupplier a, MstKota k  "
			+ "where a.kodeKota = k.kodeKota AND a.kodeSupplier like %:kodeSupplier%")
	public Object[] findOneSupplier(@Param("kodeSupplier")String kodeSupplier);
}
