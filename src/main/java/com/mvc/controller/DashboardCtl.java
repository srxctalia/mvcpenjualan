package com.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.dto.MstBarangDto;
import com.mvc.dto.MstCustomerDto;
import com.mvc.dto.MstSupplierDto;
import com.mvc.dto.TrHeaderPenjualanDto;
import com.mvc.service.MstBarangSvc;
import com.mvc.service.MstCustomerSvc;
import com.mvc.service.MstSupplierSvc;
import com.mvc.service.TransaksiSvc;

@Controller
@RequestMapping("dashboard")
public class DashboardCtl {
	@Autowired
	private MstBarangSvc svcb;
	@Autowired
	private MstCustomerSvc svcc;
	@Autowired
	private MstSupplierSvc svcs;
	@Autowired
	private TransaksiSvc svct;
	
	@RequestMapping("main")
	public String main(Model model){
		model.addAttribute("banyakbarang", banyakBarang());
		model.addAttribute("banyakcustomer", banyakCustomer());
		model.addAttribute("banyaksupplier", banyakSupplier());
		model.addAttribute("banyaktransaksi", banyakTransaksi());
		return "dashboard"; 
	}
	
	public String banyakBarang(){
		List<MstBarangDto> brg = svcb.findAllBarang();
		return String.format("%d Unit",brg.size());
	}
	
	public String banyakCustomer(){
		List<MstCustomerDto> cus = svcc.findAll();
		return String.format("%d Profile", cus.size());
	}
	
	public String banyakSupplier(){
		List<MstSupplierDto> sup = svcs.findAll();
		return String.format("%d Penjual", sup.size());
	}
	
	public String banyakTransaksi(){
		List<TrHeaderPenjualanDto> th = svct.findAllHeader();
		return String.format("%d Kali Transaksi",th.size());
	}
}
