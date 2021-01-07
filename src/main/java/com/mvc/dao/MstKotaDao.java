package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.MstKota;
import com.mvc.entity.MstKotaPK;

public interface MstKotaDao extends JpaRepository<MstKota,MstKotaPK>{
	@Query("select a from MstKota a "
			+ "where a.kodeKota like %:cari% or a.namaKota like %:cari%")
	public List<MstKota> search(@Param("cari") String cari, Pageable pageable);

	
	@Query("select count(a.kodeKota) from MstKota a "
			+ "where a.kodeKota like %:cari%")
	public int countData(@Param("cari") String cari);
}
