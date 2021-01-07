package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.MstBarang;
import com.mvc.entity.MstBarangPK;


public interface MstBarangDao extends JpaRepository<MstBarang, MstBarangPK>{
	@Query("select a from MstBarang a "
			+ "where a.kodeBarang like %:cari% or a.namaBarang like %:cari% ")
	public List<MstBarang> search(@Param("cari") String cari, Pageable pageable);

	
	@Query("select count(a.kodeBarang) from MstBarang a "
			+ "where a.kodeBarang like %:cari%")
	public int countData(@Param("cari") String cari);
}
