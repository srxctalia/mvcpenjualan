package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.TrHeaderPenjualan;
import com.mvc.entity.TrHeaderPenjualanPK;

public interface TrHeaderPenjualanDao extends JpaRepository<TrHeaderPenjualan, TrHeaderPenjualanPK>{
	@Query("select a,b.namaKaryawan,c.namaCustomer "
			+ "from TrHeaderPenjualan as a,MstKaryawan as b,MstCustomer as c "
			+ "where a.kodeCustomer = c.kodeCustomer AND a.kodeKaryawan = b.kodeKaryawan "
			+ "AND (a.noNota like %:search% or c.namaCustomer like %:search% or b.namaKaryawan like %:search% or a.kodeKaryawan like %:search%) "
			+ "AND CONVERT(DATE, a.tanggalTransaksi) = CONVERT(DATE, GETDATE())")
	public List<Object[]> search(@Param("search") String cari, Pageable pageable);
	
	@Query("select a,b.namaKaryawan,c.namaCustomer "
			+ "from TrHeaderPenjualan as a,MstKaryawan as b,MstCustomer as c "
			+ "where a.kodeCustomer = c.kodeCustomer AND a.kodeKaryawan = b.kodeKaryawan "
			+ "AND (a.noNota like %:noNota%)")
	public Object[] findOnePenjualan(@Param("noNota") String noNota);
	
	@Query("select count(a.noNota) from TrHeaderPenjualan a "
			+ "where a.noNota like %:cari%")
	public int countData(@Param("cari") String cari);
	
	@Query("select a,b.namaKaryawan,c.namaCustomer "
			+ "from TrHeaderPenjualan as a,MstKaryawan as b,MstCustomer as c "
			+ "where a.kodeCustomer = c.kodeCustomer AND a.kodeKaryawan = b.kodeKaryawan ")
	public List<Object[]> findAllHeader();
}
