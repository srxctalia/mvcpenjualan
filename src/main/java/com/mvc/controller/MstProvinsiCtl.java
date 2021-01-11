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
import com.mvc.dto.MstKaryawanLoginDto;
import com.mvc.dto.MstProvinsiDto;
import com.mvc.service.MstProvinsiSvc;

@Controller
@RequestMapping("provinsi")
public class MstProvinsiCtl {

	@Autowired
	private MstProvinsiSvc svc;
	String kondisi = "";
	
//	@RequestMapping("add")
//	public String add(Model model, HttpServletRequest request){
//		HttpSession session = request.getSession();
//		if(session.getAttribute("login") == null){
//			return "redirect:/user/login";
//		} else {
//			MstProvinsiDto dto = new MstProvinsiDto();
//			model.addAttribute("dto", dto);
//			return "addProvinsi";
//		}
//	}
	
	@RequestMapping("add")
	public String add(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		} else {
		model.addAttribute("usr", kar.getNamaKaryawan());
			MstProvinsiDto dto = new MstProvinsiDto();
			model.addAttribute("dto", dto);
			model.addAttribute("kodeTerakhir", kodeTerakhir());
			kondisi = "add";
			return "addProvinsi";
		}
	}
	
	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("dto") MstProvinsiDto dto, BindingResult result,
			Model model){
		MstProvinsiDto findOne = svc.findOneProvinsi(dto.getKodeProvinsi().toUpperCase());
		if (findOne == null){
			if(result.hasErrors()){
				return "addProvinsi";
			} else {
				svc.saveProvinsi(dto);
				return "redirect:/provinsi/pageprovinsi";	
			}
		} else {
			if(kondisi.equalsIgnoreCase("add")){
				model.addAttribute("valid", "Kode Provinsi Sudah Ada");
				model.addAttribute("stat", 1);
				return "addProvinsi";
			} else {
				if(result.hasErrors()){
					return "editProvinsi";
				} else {
					svc.saveProvinsi(dto);
					return "redirect:/provinsi/pageprovinsi";	
				}
			}
		}
	}
	
	@RequestMapping("findone/{kodeProvinsi}")
	public String detail(Model model, @PathVariable("kodeProvinsi") String kodeProvinsi,HttpServletRequest request){
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		} else {
		model.addAttribute("usr", kar.getNamaKaryawan());
		MstProvinsiDto dto = svc.findOneProvinsi(kodeProvinsi);
		model.addAttribute("dto", dto);
		kondisi = "detail";
		return "editProvinsi";
		}
	}
	
	@RequestMapping("delete/{kodeProvinsi}")
	public String delete(@PathVariable("kodeProvinsi") String kodeProvinsi){
		svc.deleteProvinsi(kodeProvinsi);
		return "redirect:/provinsi/pageprovinsi";
	}
	
//	@RequestMapping("pageProvinsi")
//	public String listPendudukWithPaging(Model model, 
//			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
//			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
//			HttpServletRequest request){
//		HttpSession session = request.getSession();
//		if(session.getAttribute("login") == null){
//			return "redirect:/user/login";
//		} else {
//			String userID = (String) session.getAttribute("login");
//			Map<String, Object> map = svc.listAllPageProvinsi(cari, page);
//			List<MstProvinsiDto> list = (List<MstProvinsiDto>) map.get("list");
//			int totalHalaman = (int) map.get("jumlah");
//			model.addAttribute("provinsi", list);
//			model.addAttribute("total", totalHalaman);
//			model.addAttribute("userID", userID);
//			
//			return "pageProvinsi";	
//		}
//	}
	
	@RequestMapping("pageprovinsi")
	public String listPendudukWithPaging(Model model, 
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page
			,HttpServletRequest request){
			HttpSession session = request.getSession();
			MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
			if (kar == null){
				return "redirect:/karyawan/login";
			} else {
			model.addAttribute("usr", kar.getNamaKaryawan());
			Map<String, Object> map = svc.listAllPageProvinsi(cari, page);
			List<MstProvinsiDto> list = (List<MstProvinsiDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");
			model.addAttribute("provinsi", list);
			model.addAttribute("total", totalHalaman);
			
			if(cari.length() > 0){
				String out = String.format("Berikut Adalah Hasil Pencarian : %s", cari);
				model.addAttribute("keterangan",out);
			}
			if(list.isEmpty()){
				String out = String.format("Hasil pencarian '%s' tidak ditemukan. ", cari);
				model.addAttribute("penjelasan",out);
			}
			
			return "pageProvinsi";
		}
	}
	
	public String kodeTerakhir(){
		String out = ""; 
		List<MstProvinsiDto> kodeTerakhir = svc.findAllProvinsi();
		if(kodeTerakhir.size() < 10){
			out = String.format(", Kode Karyawan yang terdaftar terakhir P00%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 10 && kodeTerakhir.size() < 100){
			out = String.format(", Kode Karyawan yang terdaftar terakhir P0%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 100){
			out = String.format(", Kode Karyawan yang terdaftar terakhir P%d", kodeTerakhir.size());
		}return out;
	}
}
