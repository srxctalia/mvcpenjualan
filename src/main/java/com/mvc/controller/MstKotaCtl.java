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




import com.mvc.dto.MstKaryawanLoginDto;
import com.mvc.dto.MstKotaDto;
import com.mvc.dto.MstProvinsiDto;
import com.mvc.service.MstKotaSvc;
import com.mvc.service.MstProvinsiSvc;

@Controller
@RequestMapping("kota")
public class MstKotaCtl {
	@Autowired
	private MstKotaSvc svc;
	@Autowired
	private MstProvinsiSvc svcProv;
	String kondisi ="";
	
	@RequestMapping("page-kota")
	public String listBarangWithPaging(Model model,
			@RequestParam(value = "cari",defaultValue = "", required = false) String cari,
			@RequestParam(value = "page",defaultValue = "1", required = false) int page
			,HttpServletRequest request) {
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
			Map<String,Object> map = svc.listAllPageKota(cari, page);
			List<MstKotaDto> list = (List<MstKotaDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");
			model.addAttribute("kota", list);
			model.addAttribute("total",totalHalaman);
			if(cari.length() > 0){
				String out = String.format("Berikut Adalah Hasil Pencarian : %s", cari);
				model.addAttribute("keterangan",out);
			}
			if(list.isEmpty()){
				String out = String.format("Hasil pencarian '%s' tidak ditemukan. ", cari);
				model.addAttribute("penjelasan",out);
			}
			return "pageKota";
			}
		
	}
	
	@RequestMapping("add")
	public String add(Model model,HttpServletRequest request){
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
		List<MstProvinsiDto> prov = svcProv.findAllProvinsi();

		MstKotaDto dto = new MstKotaDto();
		model.addAttribute("dto",dto);
		model.addAttribute("provinsi", prov);
		model.addAttribute("kodeTerakhir", kodeTerakhir());
		kondisi = "add";
		return "addKota";
		}
		
	}
	
	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("dto") MstKotaDto dto, 
			BindingResult result, Model model,HttpServletRequest request){ 
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		model.addAttribute("usr", kar.getNamaKaryawan());
		MstKotaDto findOne = svc.findOneKota(dto.getKodeKota());
		List<MstProvinsiDto> prov = svcProv.findAllProvinsi();
		model.addAttribute("provinsi", prov);
		model.addAttribute("kodeTerakhir", kodeTerakhir());
		if(kar.getLevel().equals("1")){
			model.addAttribute("level", "Admin");
		}if(kar.getLevel().equals("2")){
			model.addAttribute("level", "Staff");
		}
		if(findOne == null){
			if(result.hasErrors()){
				return "addKota";
			}else{
				svc.saveKota(dto);
				return "redirect:/kota/page-kota"; 
			}
		}else{
			if(kondisi.equalsIgnoreCase("add")){
				model.addAttribute("valid", "Kode Kota Sudah Ada");
				model.addAttribute("stat", 1);
				return "addKota";
			}else{
				if(result.hasErrors()){
					return "editKota";
				}else{
					svc.saveKota(dto);
					return "redirect:/kota/page-kota"; 
				}
			}
		}

	}
	
	@RequestMapping("detail/{kodeKota}")
	public String detail(Model model, @PathVariable("kodeKota") String kodeKota
			,HttpServletRequest request){
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
		MstKotaDto dto = svc.findOneKota(kodeKota);
		List<MstProvinsiDto> prov = svcProv.findAllProvinsi();

		model.addAttribute("dto",dto);
		model.addAttribute("provinsi", prov);
		kondisi ="detail";
		return "editKota";
		}
	}
	
	@RequestMapping("delete/{kodeKota}")
	public String delete(@PathVariable("kodeKota") String kodeKota){
		svc.deleteKota(kodeKota);
		return "redirect:/kota/page-kota"; 
	}
	
	public String kodeTerakhir(){
		String out = ""; 
		List<MstKotaDto> kodeTerakhir = svc.findAllKota();
		if(kodeTerakhir.size() < 10){
			out = String.format(", Kode kota yang terdaftar terakhir K00%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 10 && kodeTerakhir.size() < 100){
			out = String.format(", Kode kota yang terdaftar terakhir K0%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 100){
			out = String.format(", Kode kota yang terdaftar terakhir K%d", kodeTerakhir.size());
		}return out;
	}
}
