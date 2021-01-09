package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class DashboardCtl {

	@RequestMapping("main")
	public String delete(Model model){
		model.addAttribute("test", "Ini Test");
		return "dashboard"; 
	}
	
}
