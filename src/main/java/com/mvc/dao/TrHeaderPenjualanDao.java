package com.mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.TrHeaderPenjualan;
import com.mvc.entity.TrHeaderPenjualanPK;

public interface TrHeaderPenjualanDao extends JpaRepository<TrHeaderPenjualan, TrHeaderPenjualanPK>{
	@Query("select a,b.namaKaryawan,c.namaCustomer "
			+ "from TrHeaderPenjualan as a,MstKaryawan as b,MstCustomer as c "
			+ "where a.kodeCustomer = c.kodeCustomer AND a.kodeKaryawan = b.kodeKaryawan ")
	List<Object[]> findAllPenjualanWithName();
	
	@Query("select a,b.namaKaryawan,c.namaCustomer "
			+ "from TrHeaderPenjualan as a,MstKaryawan as b,MstCustomer as c "
			+ "where a.kodeCustomer = c.kodeCustomer AND a.kodeKaryawan = b.kodeKaryawan "
			+ "AND (a.noNota like %:search% or c.namaCustomer like %:search% or b.namaKaryawan like %:search%)")
	List<Object[]> findAllPenjualanBySearch(@Param("search") String cari);
	
	@Query("select a,b.namaKaryawan,c.namaCustomer "
			+ "from TrHeaderPenjualan as a,MstKaryawan as b,MstCustomer as c "
			+ "where a.kodeCustomer = c.kodeCustomer AND a.kodeKaryawan = b.kodeKaryawan "
			+ "AND (a.noNota like %:noNota%)")
	List<Object[]> findOnePenjualanBySearch(@Param("noNota") String noNota);
}
