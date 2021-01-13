package com.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;






import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;*/
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.dto.MstBarangDto;
import com.mvc.dto.MstKaryawanLoginDto;
import com.mvc.dto.MstSupplierDto;
import com.mvc.service.MstBarangSvc;
import com.mvc.service.MstSupplierSvc;

@Controller
@RequestMapping("barang")
public class MstBarangCtl {
	@Autowired
	private MstBarangSvc svc;
	@Autowired
	private MstSupplierSvc svcSup;
	String kondisi ="";
	
	@RequestMapping("page-barang")
	public String listBarangWithPaging(Model model,
			@RequestParam(value = "cari",defaultValue = "", required = false) String cari,
			@RequestParam(value = "page",defaultValue = "1", required = false) int page,
			HttpServletRequest request) {
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
			Map<String,Object> map = svc.listAllPageBarang(cari, page);
			List<MstBarangDto> list = (List<MstBarangDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");
			model.addAttribute("barang", list);
			model.addAttribute("total",totalHalaman);
			model.addAttribute("usr", kar.getNamaKaryawan());
			if(cari.length() > 0){
				String out = String.format("Berikut Adalah Hasil Pencarian : %s", cari);
				model.addAttribute("keterangan",out);
			}
			if(list.isEmpty()){
				String out = String.format("Hasil pencarian '%s' tidak ditemukan. ", cari);
				model.addAttribute("penjelasan",out);
			}
			return "pageBarang";
			}
	}
	
	@RequestMapping("add")
	public String add(Model model,
			HttpServletRequest request){
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
		MstBarangDto dto = new MstBarangDto();
		List<MstSupplierDto> sup = svcSup.findAll();

		model.addAttribute("dto",dto);
		model.addAttribute("supplier", sup);
		model.addAttribute("kodeTerakhir", kodeTerakhir());
		model.addAttribute("username", kar.getNamaKaryawan());
		kondisi = "add";
		return "addBarang";
		}
	}
	
	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("dto") MstBarangDto dto, 
			BindingResult result, Model model,HttpServletRequest request){ 
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		List<MstSupplierDto> sup = svcSup.findAll();
		MstBarangDto findOne = svc.findOneBarang(dto.getKodeBarang());
		model.addAttribute("kodeTerakhir", kodeTerakhir());
		model.addAttribute("supplier", sup);
		model.addAttribute("username", kar.getNamaKaryawan());
		if(kar.getLevel().equals("1")){
			model.addAttribute("level", "Admin");
		}if(kar.getLevel().equals("2")){
			model.addAttribute("level", "Staff");
		}	
		if(findOne == null){
			if(result.hasErrors()){
				return "addBarang";
			}else{
				svc.saveBarang(dto);
				return "redirect:/barang/page-barang"; 
			}
		}else{
			if(kondisi.equalsIgnoreCase("add")){
				model.addAttribute("valid", "Kode Barang Sudah Ada");
				model.addAttribute("stat", 1);
				return "addBarang";
			}else{
				if(result.hasErrors()){
					return "editBarang";
				}else{
					svc.saveBarang(dto);
					return "redirect:/barang/page-barang"; 
				}
			}
		}

	}
	
	@RequestMapping("detail/{kodeBarang}")
	public String detail(Model model, @PathVariable("kodeBarang") String kodeBarang,
			HttpServletRequest request){
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
		MstBarangDto dto = svc.findOneBarang(kodeBarang);
		List<MstSupplierDto> sup = svcSup.findAll();

		model.addAttribute("dto",dto);
		model.addAttribute("supplier", sup);
		model.addAttribute("username", kar.getNamaKaryawan());
		kondisi="detail";
		return "editBarang";
		}
	}
	
	@RequestMapping("delete/{kodeBarang}")
	public String delete(@PathVariable("kodeBarang") String kodeBarang){
		svc.deleteBarang(kodeBarang);
		return "redirect:/barang/page-barang"; 
	}
	
	public String kodeTerakhir(){
		String out = ""; 
		List<MstBarangDto> kodeTerakhir = svc.findAllBarang();
		if(kodeTerakhir.size() < 10){
			out = String.format(", Kode barang yang terdaftar terakhir B00%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 10 && kodeTerakhir.size() < 100){
			out = String.format(", Kode barang yang terdaftar terakhir B0%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 100){
			out = String.format(", Kode barang yang terdaftar terakhir B%d", kodeTerakhir.size());
		}return out;
	}
	

}
