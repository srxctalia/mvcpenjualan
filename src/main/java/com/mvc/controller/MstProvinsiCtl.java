package com.mvc.controller;

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

import com.mvc.dto.MstProvinsiDto;
import com.mvc.service.MstProvinsiSvc;

@Controller
@RequestMapping("provinsi")
public class MstProvinsiCtl {

	@Autowired
	private MstProvinsiSvc svc;
	String kondisi = "";
	
	@RequestMapping("add")
	public String add(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("login") == null){
			return "redirect:/user/login";
		} else {
			MstProvinsiDto dto = new MstProvinsiDto();
			model.addAttribute("dto", dto);
			return "addProvinsi";
		}
	}
	
	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("dto") MstProvinsiDto dto, BindingResult result){
		MstProvinsiDto findOne = svc.findOneProvinsi(dto.getKodeProvinsi());
		if (findOne == null){
			if(result.hasErrors()){
				return "addProvinsi";
			} else {
				svc.saveProvinsi(dto);
				return "redirect:/provinsi/pageProvinsi";	
			}
		} else {
			if(kondisi.equalsIgnoreCase("add")){
				dto.setKodeProvinsi("Kode Karyawan sudah ada! masukkan input lain.");
				return "addProvinsi";
			} else {
				if(result.hasErrors()){
					return "editProvinsi";
				} else {
					svc.saveProvinsi(dto);
					return "redirect:/provinsi/pageProvinsi";	
				}
			}
		}
	}
	
	@RequestMapping("findone/{kode}")
	public String detail(Model model, @PathVariable("kode") String kode){
		MstProvinsiDto dto = svc.findOneProvinsi(kode);
		model.addAttribute("dto", dto);
		kondisi = "detail";
		return "editProvinsi";
	}
	
	@RequestMapping("delete/{kode}")
	public String delete(@PathVariable("kode") String kode){
		svc.deleteProvinsi(kode);
		return "redirect:/provinsi/pageProvinsi";
	}
	
	@RequestMapping("pageProvinsi")
	public String listPendudukWithPaging(Model model, 
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("login") == null){
			return "redirect:/user/login";
		} else {
			String userID = (String) session.getAttribute("login");
			Map<String, Object> map = svc.listAllPageProvinsi(cari, page);
			List<MstProvinsiDto> list = (List<MstProvinsiDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");
			model.addAttribute("provinsi", list);
			model.addAttribute("total", totalHalaman);
			model.addAttribute("userID", userID);
			
			return "pageProvinsi";	
		}
	}
	
	
}
