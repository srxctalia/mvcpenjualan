package com.mvc.controller;

import java.util.List;
import java.util.Map;

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
import com.mvc.service.MstBarangSvc;

@Controller
@RequestMapping("barang")
public class MstBarangCtl {
	@Autowired
	private MstBarangSvc svc;
	String kondisi ="";
	
	@RequestMapping("page-barang")
	public String listBarangWithPaging(Model model,
			@RequestParam(value = "cari",defaultValue = "", required = false) String cari,
			@RequestParam(value = "page",defaultValue = "1", required = false) int page) {
			Map<String,Object> map = svc.listAllPageBarang(cari, page);
			List<MstBarangDto> list = (List<MstBarangDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");
			model.addAttribute("barang", list);
			model.addAttribute("total",totalHalaman);
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
	
	@RequestMapping("add")
	public String add(Model model){

		MstBarangDto dto = new MstBarangDto();
		model.addAttribute("dto",dto);
		kondisi = "add";
		return "addBarang";
		
	}
	
	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("dto") MstBarangDto dto, 
			BindingResult result, Model model){ 
		MstBarangDto findOne = svc.findOneBarang(dto.getKodeBarang());
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
	public String detail(Model model, @PathVariable("kodeBarang") String kodeBarang){
		MstBarangDto dto = svc.findOneBarang(kodeBarang);
		model.addAttribute("dto",dto);
		kondisi="detail";
		return "editBarang";
	}
	
	@RequestMapping("delete/{kodeBarang}")
	public String delete(@PathVariable("kodeBarang") String kodeBarang){
		svc.deleteBarang(kodeBarang);
		return "redirect:/barang/page-barang"; 
	}
	

}
