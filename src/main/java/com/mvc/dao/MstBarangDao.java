package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.MstBarang;
import com.mvc.entity.MstBarangPK;


public interface MstBarangDao extends JpaRepository<MstBarang, MstBarangPK>{
	@Query("select a,b.namaSupplier from MstBarang a, MstSupplier b "
			+ "where a.kodeSupplier = b.kodeSupplier "
			+ "AND (a.kodeBarang like %:cari% or a.namaBarang like %:cari% "
			+ "or b.namaSupplier like %:cari%)")
	public List<Object[]> search(@Param("cari") String cari, Pageable pageable);

	
	@Query("select count(a.kodeBarang) from MstBarang a "
			+ "where a.kodeBarang like %:cari%")
	public int countData(@Param("cari") String cari);
}
