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

import com.mvc.dto.MstKotaDto;
import com.mvc.dto.MstSupplierDto;
import com.mvc.service.MstKotaSvc;
import com.mvc.service.MstSupplierSvc;

@Controller
@RequestMapping("supplier")
public class MstSupplierCtl {
	
	@Autowired
	private MstSupplierSvc svc;
	
	@Autowired
	private MstKotaSvc svcK;
	
private String cekSupplier = "";
	
	@RequestMapping("/all")
	public String listAll(Model model,
			@RequestParam(value="cari", defaultValue="", required=false)String cari,
			@RequestParam(value="page", defaultValue="1", required=false)int page,
			HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		String username = (String) session.getAttribute("login");
		Map<String, Object> map = svc.listAll(cari, page);
		List<MstSupplierDto> list = (List<MstSupplierDto>) map.get("list");
		int totalHalaman = (int) map.get("jumlah");
		model.addAttribute("supplier", list);
		model.addAttribute("total", totalHalaman);
		model.addAttribute("username", username);
		return "supplier";
	}
	
	@RequestMapping("/add")
	public String save(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
//		if (session.getAttribute("login") == null){
//			return "redirect:/user/login";
//		} 
		MstSupplierDto dto = new MstSupplierDto();
		List<MstKotaDto> kotas = svcK.findAllKota();
		Map<String, String> listkota = new HashMap<>();
		for (MstKotaDto kota : kotas ){
			listkota.put(kota.getKodeKota(), kota.getNamaKota());
		}
		
		cekSupplier = "add";
		model.addAttribute("dto", dto);
		model.addAttribute("kota", listkota);
		return "addSupplier";
		
	}
	
	@RequestMapping("/edit")
	public String edit(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
//		if (session.getAttribute("login") == null){
//			return "redirect:/user/login";
//		} 
		MstSupplierDto dto = new MstSupplierDto();
		
		cekSupplier = "edit";
		model.addAttribute("dto", dto);
		return "editSupplier";
		
	}
	
	@RequestMapping("/save")
	public String save(@Valid @ModelAttribute("dto") MstSupplierDto dto, BindingResult result, Model model){
		
		if (svc.findOne(dto.getKodeSupplier()) == null){
			if (result.hasErrors()){
				return "addSupplier";
			}
			svc.save(dto);
			return "redirect:/supplier/all";
		} else {
			if (cekSupplier.equalsIgnoreCase("edit")){
				if (!result.hasErrors()){
					svc.save(dto);
					return "redirect:/supplier/all";				
				}
				return "addSupplier";
			}
			model.addAttribute("validasi", "kode supplier sudah ada");
			return "addSupplier";
		}
	}
	
	@RequestMapping("/delete/{kodeSupplier}")
	public String delete(@PathVariable("kodeSupplier")String kodeSupplier){
		svc.delete(kodeSupplier);
		
		return "redirect:/supplier/all";
	}

}
