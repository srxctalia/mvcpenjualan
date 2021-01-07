package com.mvc.controller;

import java.util.List;
import java.util.Map;

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
import com.mvc.service.MstKotaSvc;

@Controller
@RequestMapping("kota")
public class MstKotaCtl {
	@Autowired
	private MstKotaSvc svc;
	String kondisi ="";
	
	@RequestMapping("page-kota")
	public String listBarangWithPaging(Model model,
			@RequestParam(value = "cari",defaultValue = "", required = false) String cari,
			@RequestParam(value = "page",defaultValue = "1", required = false) int page) {
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
	
	@RequestMapping("add")
	public String add(Model model){

		MstKotaDto dto = new MstKotaDto();
		model.addAttribute("dto",dto);
		kondisi = "add";
		return "addKota";
		
	}
	
	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("dto") MstKotaDto dto, 
			BindingResult result, Model model){ 
		MstKotaDto findOne = svc.findOneKota(dto.getKodeKota());
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
	public String detail(Model model, @PathVariable("kodeKota") String kodeKota){
		MstKotaDto dto = svc.findOneKota(kodeKota);
		model.addAttribute("dto",dto);
		kondisi ="detail";
		return "editKota";
	}
	
	@RequestMapping("delete/{kodeKota}")
	public String delete(@PathVariable("kodeKota") String kodeKota){
		svc.deleteKota(kodeKota);
		return "redirect:/kota/page-kota"; 
	}
}
