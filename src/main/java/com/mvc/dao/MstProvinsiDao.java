package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.mvc.entity.MstProvinsi;
import com.mvc.entity.MstProvinsiPK;

public interface MstProvinsiDao extends JpaRepository<MstProvinsi,MstProvinsiPK>{
	@Query("select a from MstProvinsi a "
			+ "where a.namaProvinsi like %:cari%")
	public List<MstProvinsi> search(@Param("cari") String cari, Pageable pageable);
	
	@Query("select count(a.kodeProvinsi) from MstProvinsi a "
			+ "where a.kodeProvinsi like %:cari%")
	public int countData(@Param("cari") String cari);
}
