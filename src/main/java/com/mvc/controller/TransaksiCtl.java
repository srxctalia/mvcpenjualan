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

import com.mvc.dto.MstBarangDto;
import com.mvc.dto.MstCustomerDto;
import com.mvc.dto.MstKaryawanDto;
import com.mvc.dto.MstKaryawanLoginDto;
import com.mvc.dto.TrDetailPenjualanDto;
import com.mvc.dto.TrHeaderPenjualanDto;
import com.mvc.service.MstBarangSvc;
import com.mvc.service.MstCustomerSvc;
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
	private MstCustomerSvc svcC;
	
	@Autowired
	private MstBarangSvc svcB;
	
	@RequestMapping("/all")
	public String listAll(Model model,
			@RequestParam(value="cari", defaultValue="", required=false)String cari,
			@RequestParam(value="page", defaultValue="1", required=false)int page,
			HttpServletRequest request){
		
		
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		}
		
		if (kar.getLevel().equals("1")){
			Map<String, Object> map = svcT.listAll(cari, page);
			List<TrHeaderPenjualanDto> list = (List<TrHeaderPenjualanDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");
			model.addAttribute("transaksi", list);
			model.addAttribute("total", totalHalaman);
			model.addAttribute("usr", kar.getNamaKaryawan());
			return "transaksi";
		} else {			
			Map<String, Object> map = svcT.listAll(kar.getKodeKaryawan(), page);
			List<TrHeaderPenjualanDto> list = (List<TrHeaderPenjualanDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");
			model.addAttribute("transaksi", list);
			model.addAttribute("total", totalHalaman);
			model.addAttribute("usr", kar.getNamaKaryawan());
			return "transaksi";			
		}
	}
	
	@RequestMapping("/add")
	public String saveHeader(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		}
		
		TrHeaderPenjualanDto dtoH = new TrHeaderPenjualanDto();

		List<MstCustomerDto> listCustomer = svcC.findAll();
		List<TrDetailPenjualanDto> listDetail = dtoH.getDetailTransaksi();
		
		dtoH.setKodeKaryawan(kar.getKodeKaryawan());
		dtoH.setNamaKaryawan(kar.getNamaKaryawan());
		
		session.setAttribute("dtoH", dtoH);
		session.setAttribute("listDetail", listDetail);
		model.addAttribute("dtoH", dtoH);
		model.addAttribute("listDetail", listDetail);
		model.addAttribute("customer", listCustomer);
		return "addTransaksi";
	}
	
	@RequestMapping("/addDetail")
	public String saveDetail(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		TrDetailPenjualanDto dtoDetail = new TrDetailPenjualanDto();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		}
		List<MstBarangDto> listBarang = svcB.findAllBarang();
		
		model.addAttribute("barang", listBarang);
		model.addAttribute("dtoD", dtoDetail);
		
		return "addTransaksiDetail";
	}
	
	@RequestMapping("/saveDetail")
	public String saveDetail(@Valid @ModelAttribute("dtoD") TrDetailPenjualanDto dtoD, 
			BindingResult result, Model model, HttpServletRequest request ){
		HttpSession session = request.getSession();
		if (!result.hasErrors()){
			if (svcT.findOneDetaiil(dtoD.getKodeDetail())!= null){
				session.setAttribute("error", "KodeDetail sudah pernah dibuat");
				return "addTransaksiDetail";
			} 
			List<TrDetailPenjualanDto> listDetail = (List<TrDetailPenjualanDto>)session.getAttribute("listDetail"); 
			listDetail.add(dtoD);
			return "redirect:/transaksi/addTransaksi";
		}
		return "addTransaksiDetail";
	}
	
	@RequestMapping("/save")
	public String saveHeader(@Valid @ModelAttribute("dtoH") TrHeaderPenjualanDto dtoH,
			BindingResult result, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if (!result.hasErrors()){
			if (svcT.findOneHeaderDetail(dtoH.getNoNota())!= null){
				session.setAttribute("error", "No Nota sudah pernah dibuat");
				return "addTransaksi";
			}
			svcT.saveHeader(dtoH);
			return "redirect:/transaksi/all";
		}
		return "";
	}
	
	@RequestMapping("/edit/{noNota}")
	public String edit(@PathVariable("noNota")String noNota, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null){
			return "redirect:/karyawan/login";
		}
		
		TrHeaderPenjualanDto dto = svcT.findOneHeaderDetail(noNota);
		List<TrDetailPenjualanDto> listDetail = dto.getDetailTransaksi();
		
		model.addAttribute("dto", dto);
		model.addAttribute("listDetail", listDetail);
		return "editTransaksi";
	}
	
	
	@RequestMapping("/delete/{noNota}")
	public String deleteHeader(@PathVariable("noNota")String noNota){
		svcT.deleteHeaderDetail(noNota);
		
		return "redirect:/transaksi/all";
	}
	
	@RequestMapping("deleteDetail/{kodeDetail}")
	public String deleteDetail(@PathVariable("kodeDetail")String kodeDetail){
		// Jika kondisi ketika delete dari add Header maka yg dihapus adalah detail yang berada di list
		
		// Jika kondisi adalah delete dari edit Header maka detail yang dihapus adalah detail yang berada di db
		
		svcT.deleteDetail(kodeDetail);
		
		return "redirect:/";
	}
}
