package com.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.dto.MstCustomerDto;
import com.mvc.dto.MstKaryawanLoginDto;
import com.mvc.dto.MstKotaDto;
import com.mvc.service.MstCustomerSvc;
import com.mvc.service.MstKotaSvc;

@Controller
@RequestMapping("customer")
public class MstCustomerCtl {
	
	@Autowired
	private MstCustomerSvc svc;
	
	@Autowired 
	private MstKotaSvc svcK;
	
	private String cekCustomer = "";
	
	@RequestMapping("/all")
	public String listAll(Model model,
			@RequestParam(value="cari", defaultValue="", required=false)String cari,
			@RequestParam(value="page", defaultValue="1", required=false)int page,
			HttpServletRequest request){
		
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		}
		String username = (String) session.getAttribute("login");
		Map<String, Object> map = svc.listAll(cari, page);
		List<MstCustomerDto> list = (List<MstCustomerDto>) map.get("list");
		int totalHalaman = (int) map.get("jumlah");
		
		model.addAttribute("customer", list);
		model.addAttribute("total", totalHalaman);
		model.addAttribute("username", kar.getNamaKaryawan());
		return "customer";
	}
	
	@RequestMapping("/add")
	public String save(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		}
		MstCustomerDto dto = new MstCustomerDto();
		List<MstKotaDto> kotas = svcK.findAllKota();
		
		cekCustomer = "add";
		model.addAttribute("dto", dto);
		model.addAttribute("kota", kotas);
		model.addAttribute("username", kar.getNamaKaryawan());
		return "addCustomer";
		
	}
	
	@RequestMapping("/edit/{kodeCustomer}")
	public String edit(Model model, @PathVariable("kodeCustomer")String kodeCustomer, HttpServletRequest request){
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		}
		MstCustomerDto dto = svc.findOne(kodeCustomer);
		List<MstKotaDto> kotas = svcK.findAllKota();
		
		cekCustomer = "edit";
		model.addAttribute("dto", dto);
		model.addAttribute("kota", kotas);
		model.addAttribute("username", kar.getNamaKaryawan());
		return "editCustomer";
		
	}
	
	@RequestMapping("/save")
	public String save(@Valid @ModelAttribute("dto") MstCustomerDto dto, BindingResult result, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null){
			return "redirect:/karyawan/login";
		} 
		if (svc.findOne(dto.getKodeCustomer()) == null){
			if (result.hasErrors()){
				return "addCustomer";
			}
			svc.save(dto);
			return "redirect:/customer/all";
		} else {
			if (cekCustomer.equalsIgnoreCase("edit")){
				if (!result.hasErrors()){
					svc.save(dto);
					return "redirect:/customer/all";				
				}
				return "addCustomer";
			}
			model.addAttribute("stat", 1);
			model.addAttribute("validasi", "kode customer sudah ada");
			return "addCustomer";
		}
	}
	
	@RequestMapping("/delete/{kodeCustomer}")
	public String delete(@PathVariable("kodeCustomer")String kodeCustomer, HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null){
			return "redirect:/karyawan/login";
		} 
		svc.delete(kodeCustomer);
		
		return "redirect:/customer/all";
	}

}
