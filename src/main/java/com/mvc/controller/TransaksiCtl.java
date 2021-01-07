package com.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.dto.TrDetailPenjualanDto;
import com.mvc.dto.TrHeaderPenjualanDto;
import com.mvc.service.MstBarangSvc;
import com.mvc.service.MstKaryawanSvc;
import com.mvc.service.TransaksiSvc;

@Controller
@RequestMapping("transaksi")
public class TransaksiCtl {

	@Autowired
	private TransaksiSvc svcT;
	
	@Autowired
	private MstKaryawanSvc svcK;
	
	@Autowired
	private MstBarangSvc svcB;
	
	@RequestMapping("/all")
	public String listAll(Model model,
			@RequestParam(value="cari", defaultValue="", required=false)String cari,
			@RequestParam(value="page", defaultValue="1", required=false)int page,
			HttpServletRequest request){
		
		
		HttpSession session = request.getSession();
		
		String username = (String) session.getAttribute("login");
		Map<String, Object> map = svcT.listAll(cari, page);
		List<TrHeaderPenjualanDto> list = (List<TrHeaderPenjualanDto>) map.get("list");
		int totalHalaman = (int) map.get("jumlah");
		model.addAttribute("transaksi", list);
		model.addAttribute("total", totalHalaman);
		model.addAttribute("username", username);
		return "transaksi";
	}
	
	@RequestMapping("/add")
	public String save(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null){
			return "redirect:/user/login";
		} 
		
		TrHeaderPenjualanDto dtoH = new TrHeaderPenjualanDto();
		TrDetailPenjualanDto dtoD = new TrDetailPenjualanDto();
		
		model.addAttribute("dtoH", dtoH);
		model.addAttribute("dtoD", dtoD);
		return "addTransaksi";
	}
	
	@RequestMapping("/edit/{noNota}")
	public String edit(@PathVariable("noNota")String noNota, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null){
			return "redirect:/user/login";
		} 
		
		TrHeaderPenjualanDto dto = svcT.findOneHeaderDetail(noNota);
		
		model.addAttribute("dto", dto);
		return "editTransaksi";
	}
	
	
	@RequestMapping("/delete/{noNota}")
	public String deleteHeader(@PathVariable("noNota")String noNota){
		svcT.deleteHeaderDetail(noNota);
		
		return "redirect:/transaksi/all";
	}
	
	public String deleteDetail(@PathVariable("kodeDetail")String kodeDetail){
		return "redirect:/";
	}
}
