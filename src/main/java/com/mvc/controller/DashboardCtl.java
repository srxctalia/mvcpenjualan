package com.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.dto.MstBarangDto;
import com.mvc.dto.MstCustomerDto;
import com.mvc.dto.MstKaryawanLoginDto;
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
	public String main(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		} else {
			if(kar.getLevel().equals("1")){
				model.addAttribute("level", "Admin");
			}if(kar.getLevel().equals("2")){
				model.addAttribute("level", "Staff");
			}	
			model.addAttribute("usr", kar.getNamaKaryawan());
			model.addAttribute("banyakbarang", banyakBarang());
			model.addAttribute("banyakcustomer", banyakCustomer());
			model.addAttribute("banyaksupplier", banyakSupplier());
			model.addAttribute("banyaktransaksi", banyakTransaksi());
			return "dashboard";
		}
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
