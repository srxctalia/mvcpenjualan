package com.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.dto.MstKaryawanDto;

@Controller
@RequestMapping("user")
public class MstLoginKaryawanCtl {
/*	@Autowired
	private DropDownSvc svc;
	
	@RequestMapping("login")
	public String login(Model model){
		MstKaryawanDto dto = new MstKaryawanDto();
		model.addAttribute("dto",dto);
		return "loginMenu";
	}
	
	@RequestMapping("check")
	public String check(@Valid @ModelAttribute("dto") MstKaryawanDto dto, 
			BindingResult result, Model model){
		MstKaryawanDto findOne = svc.findOneKaryawanByUsername(dto.getUsername());
		if(findOne == null){
			model.addAttribute("validasi","User ID & Password Salah ga kedetect");
			return "loginMenu";
		}else{
			if(findOne.getPassword().equals(dto.getPassword())){
				return "redirect:/barang/page-barang";
			}else{
				model.addAttribute("validasi","User ID & Password Salah kedectect");
				return "loginMenu";
			}
		}
	}*/
	
}

