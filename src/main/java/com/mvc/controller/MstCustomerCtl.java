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
		
		String username = (String) session.getAttribute("login");
		Map<String, Object> map = svc.listAll(cari, page);
		List<MstCustomerDto> list = (List<MstCustomerDto>) map.get("list");
		int totalHalaman = (int) map.get("jumlah");
		model.addAttribute("customer", list);
		model.addAttribute("total", totalHalaman);
		model.addAttribute("username", username);
		return "customer";
	}
	
	@RequestMapping("/add")
	public String save(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
/*		if (session.getAttribute("login") == null){
			return "redirect:/user/login";
		} */
		MstCustomerDto dto = new MstCustomerDto();
		List<MstKotaDto> kotas = svcK.findAllKota();
		Map<String, String> listkota = new HashMap<>();
		for (MstKotaDto kota : kotas ){
			listkota.put(kota.getKodeKota(), kota.getNamaKota());
		}
		
		cekCustomer = "add";
		model.addAttribute("dto", dto);
		model.addAttribute("kota", listkota);
		return "addCustomer";
		
	}
	
	@RequestMapping("/edit")
	public String edit(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null){
			return "redirect:/user/login";
		} 
		MstCustomerDto dto = new MstCustomerDto();
		
		cekCustomer = "edit";
		model.addAttribute("dto", dto);
		return "editCustomer";
		
	}
	
	@RequestMapping("/save")
	public String save(@Valid @ModelAttribute("dto") MstCustomerDto dto, BindingResult result, Model model){
		
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
			model.addAttribute("validasi", "kode customer sudah ada");
			return "addCustomer";
		}
	}
	
	@RequestMapping("/delete/{kodeCustomer}")
	public String delete(@PathVariable("kodeCustomer")String kodeCustomer){
		svc.delete(kodeCustomer);
		
		return "redirect:/customer/all";
	}

}
