package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.MstCustomer;
import com.mvc.entity.MstCustomerPK;

public interface MstCustomerDao extends JpaRepository<MstCustomer, MstCustomerPK>{
	@Query("select a from MstCustomer a "
			+ "where a.kodeCustomer like %:cari%")
	public List<MstCustomer> search(@Param("cari") String cari, Pageable pageable);

	
	@Query("select count(a.kodeCustomer) from MstCustomer a "
			+ "where a.kodeCustomer like %:cari%")
	public int countData(@Param("cari") String cari);
}
