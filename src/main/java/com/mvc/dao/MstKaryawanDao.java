package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.MstKaryawan;
import com.mvc.entity.MstKaryawanPK;

public interface MstKaryawanDao extends JpaRepository<MstKaryawan, MstKaryawanPK>{
	@Query("select a from MstKaryawan a "
			+ "where a.kodeKaryawan like %:cari%")
	public List<MstKaryawan> search(@Param("cari") String cari, Pageable pageable);

	@Query("select count(a.kodeKaryawan) from MstKaryawan a "
			+ "where a.kodeKaryawan like %:cari%")
	public int countData(@Param("cari") String cari);
}
