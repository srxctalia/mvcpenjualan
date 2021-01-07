package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.MstSupplier;
import com.mvc.entity.MstSupplierPK;

public interface MstSupplierDao extends JpaRepository<MstSupplier,MstSupplierPK>{
	@Query("select a from MstSupplier a "
			+ "where a.kodeSupplier like %:cari%")
	public List<MstSupplier> search(@Param("cari") String cari, Pageable pageable);

	
	@Query("select count(a.kodeSupplier) from MstSupplier a "
			+ "where a.kodeSupplier like %:cari%")
	public int countData(@Param("cari") String cari);
}
