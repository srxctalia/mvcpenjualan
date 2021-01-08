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

import com.mvc.dto.MstKaryawanDto;
import com.mvc.service.MstKaryawanSvc;

@Controller
@RequestMapping("karyawan")
public class MstKaryawanCtl {

	@Autowired
	private MstKaryawanSvc svc;
	String kondisi = "";
	
	@RequestMapping("add")
	public String add(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("login") == null){
			return "redirect:/user/login";
		} else {
			MstKaryawanDto dto = new MstKaryawanDto();
			model.addAttribute("dto", dto);
			return "addKaryawan";
		}
	}
	
	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("dto") MstKaryawanDto dto, BindingResult result){
		MstKaryawanDto findOne = svc.findOneKaryawan(dto.getKodeKaryawan());
		if (findOne == null){
			if(result.hasErrors()){
				return "addKaryawan";
			} else {
				svc.saveKaryawan(dto);
				return "redirect:/karyawan/pageKaryawan";	
			}
		} else {
			if(kondisi.equalsIgnoreCase("add")){
				dto.setKodeKaryawan("Kode Karyawan sudah ada! masukkan input lain.");
				return "addKaryawan";
			} else {
				if(result.hasErrors()){
					return "editKaryawan";
				} else {
					svc.saveKaryawan(dto);
					return "redirect:/karyawan/pageKaryawan";	
				}
			}
		}
	}
	
	@RequestMapping("findone/{kode}")
	public String detail(Model model, @PathVariable("kode") String kode){
		MstKaryawanDto dto = svc.findOneKaryawan(kode);
		model.addAttribute("dto", dto);
		kondisi = "detail";
		return "editKaryawan";
	}
	
	@RequestMapping("delete/{kode}")
	public String delete(@PathVariable("kode") String kode){
		svc.deleteKaryawan(kode);
		return "redirect:/karyawan/pageKaryawan";
	}
	
	@RequestMapping("pageKaryawan")
	public String listPendudukWithPaging(Model model, 
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("login") == null){
			return "redirect:/user/login";
		} else {
			String userID = (String) session.getAttribute("login");
			Map<String, Object> map = svc.listAllPageKaryawan(cari, page);
			List<MstKaryawanDto> list = (List<MstKaryawanDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");
			model.addAttribute("karyawan", list);
			model.addAttribute("total", totalHalaman);
			model.addAttribute("userID", userID);
			
			return "pageKaryawan";	
		}
	}
	
}
