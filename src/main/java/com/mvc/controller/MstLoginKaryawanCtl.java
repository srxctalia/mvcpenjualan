package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.dto.MstKaryawanDto;
import com.mvc.service.DropDownSvc;

@Controller
@RequestMapping("user")
public class MstLoginKaryawanCtl {
	@Autowired
	private DropDownSvc svc;
	
	@RequestMapping("login")
	public String login(Model model){
		MstKaryawanDto dto = new MstKaryawanDto();
		model.addAttribute("dto",dto);
		return "loginMenu";
	}
	
	@RequestMapping("check")
	public String check(@Valid @ModelAttribute("dto") MstKaryawanDto dto, 
			BindingResult result, Model model,HttpServletRequest request){
		HttpSession session = request.getSession(); 
		MstKaryawanDto findOne = svc.findOneKaryawan(dto.getKodeKaryawan());
		if(findOne == null){
			model.addAttribute("validasi","User ID & Password Salah");
			return "loginMenu";
		}else{
			if(findOne.getPassword().equals(dto.getPassword())){
				session.setAttribute("login",dto.getNamaKaryawan());
//				session.setMaxInactiveInterval(5);
				return "redirect:/penduduk/pagependuduk"; //belum dibuat
			}else{
				model.addAttribute("validasi","User ID & Password Salah");
				return "loginMenu";
			}
		}
	}
	
}

