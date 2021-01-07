package com.mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.TrDetailPenjualan;
import com.mvc.entity.TrDetailPenjualanPK;

public interface TrDetailPenjualanDao extends JpaRepository<TrDetailPenjualan, TrDetailPenjualanPK>{
	@Query("select a,b.namaBarang "
			+ "from TrDetailPenjualan as a,MstBarang as b "
			+ "where a.kodeBarang = b.kodeBarang "
			+ "AND (a.noNota like %:noNota%) ")
	List<Object[]> findAllDetailPenjualanBySearch(@Param("noNota") String noNota);

	@Modifying
	@Query("DELETE TrDetailPenjualan as a where a.noNota = :noNota")
	public void deleteDetail(@Param("noNota") String noNota);
}
