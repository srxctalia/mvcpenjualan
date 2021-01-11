package com.mvc.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	public String listAll(
			Model model,
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session
				.getAttribute("loginUser");
		if (kar == null) {
			return "redirect:/karyawan/login";
		}
		
		session.removeAttribute("dtoH");

		if (kar.getLevel().equals("1")) {
			Map<String, Object> map = svcT.listAll(cari, page);
			List<TrHeaderPenjualanDto> list = (List<TrHeaderPenjualanDto>) map
					.get("list");
			int totalHalaman = (int) map.get("jumlah");

			model.addAttribute("transaksi", list);
			model.addAttribute("total", totalHalaman);
			model.addAttribute("usr", kar.getNamaKaryawan());
			return "transaksi";
		} else {
			Map<String, Object> map = svcT.listAll(kar.getKodeKaryawan(), page);
			List<TrHeaderPenjualanDto> list = (List<TrHeaderPenjualanDto>) map
					.get("list");
			int totalHalaman = (int) map.get("jumlah");

			model.addAttribute("transaksi", list);
			model.addAttribute("total", totalHalaman);
			model.addAttribute("usr", kar.getNamaKaryawan());
			return "transaksi";
		}
	}

	@RequestMapping("/add")
	public String saveHeader(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session
				.getAttribute("loginUser");
		if (kar == null) {
			return "redirect:/karyawan/login";
		}

		TrHeaderPenjualanDto dtoH = new TrHeaderPenjualanDto();

		List<MstCustomerDto> listCustomer = svcC.findAll();
		if (session.getAttribute("dtoH") != null) {
			dtoH = (TrHeaderPenjualanDto) session.getAttribute("dtoH");
		}
		if (session.getAttribute("listDetail") != null) {
			dtoH.setDetailTransaksi((List<TrDetailPenjualanDto>) session
					.getAttribute("listDetail"));
		}
	
		model.addAttribute("dtoH", dtoH);
		model.addAttribute("customer", listCustomer);
		int grandTotal=0;
		for (TrDetailPenjualanDto detail : dtoH.getDetailTransaksi()){
			grandTotal+=detail.getSubtotal();			
		}
		dtoH.setHargaTotal(grandTotal);
		dtoH.setTanggalTransaksi(Date.valueOf(LocalDate.now()));
		dtoH.setKodeKaryawan(kar.getKodeKaryawan());
		dtoH.setNamaKaryawan(kar.getNamaKaryawan());
		
		session.setAttribute("kondisi", "add");

		return "addTransaksi";
	}

	@RequestMapping("/addDetail")
	public String saveDetail(Model model,@ModelAttribute("dtoH") TrHeaderPenjualanDto dtoH, HttpServletRequest request) {
		HttpSession session = request.getSession();
		TrDetailPenjualanDto dtoDetail = new TrDetailPenjualanDto();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session
				.getAttribute("loginUser");
		if (kar == null) {
			return "redirect:/karyawan/login";
		}
		List<MstBarangDto> listBarang = svcB.findAllBarang();

		model.addAttribute("barang", listBarang);
		model.addAttribute("dtoD", dtoDetail);
		session.setAttribute("dtoH", dtoH);
		return "addTransaksiDetail";
	}

	@RequestMapping("/saveDetail")
	public String saveDetail(
			@Valid @ModelAttribute("dtoD") TrDetailPenjualanDto dtoD,
			BindingResult result, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (result.hasErrors()){
			return "addTransaksiDetail";
		}
		if (session.getAttribute("kondisi")== "add"){
			List<TrDetailPenjualanDto> listDetail = new ArrayList<TrDetailPenjualanDto>();
				if (svcT.findOneDetaiil(dtoD.getKodeDetail()) != null) {
					session.setAttribute("error", "KodeDetail sudah pernah dibuat");
					return "addTransaksiDetail";
				}
				if (session.getAttribute("listDetail") != null) {
					listDetail = (List<TrDetailPenjualanDto>) session
							.getAttribute("listDetail");
				}
				MstBarangDto br = svcB.findOneBarang(dtoD.getKodeBarang());
				dtoD.setNamaBarang(br.getNamaBarang());
				listDetail.add(dtoD);
				session.setAttribute("listDetail", listDetail);
				return "redirect:/transaksi/add";
		} else {
			if (svcT.findOneDetaiil(dtoD.getKodeDetail()) != null) {
				session.setAttribute("error", "KodeDetail sudah pernah dibuat");
				return "addTransaksiDetail";
			}
			List<TrDetailPenjualanDto> listDetail = (List<TrDetailPenjualanDto>) session.getAttribute("listDetail");
			MstBarangDto br = svcB.findOneBarang(dtoD.getKodeBarang());
			dtoD.setNamaBarang(br.getNamaBarang());
			listDetail.add(dtoD);
			session.setAttribute("listDetail", listDetail);
			return "redirect:/transaksi/edit/"+session.getAttribute("noNota");
		}
	}

	@RequestMapping("/save")
	public String saveHeader(
			@Valid @ModelAttribute("dtoH") TrHeaderPenjualanDto dtoH,
			BindingResult result, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (!result.hasErrors()) {
			if (svcT.findOneHeaderDetail(dtoH.getNoNota()) != null) {
				session.setAttribute("error", "No Nota sudah pernah dibuat");
				return "addTransaksi";
			}
			svcT.saveHeader(dtoH);
			return "redirect:/transaksi/all";
		}
		return "";
	}

	@RequestMapping("/edit/{noNota}")
	public String edit(@PathVariable("noNota") String noNota, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null) {
			return "redirect:/karyawan/login";
		}
		TrHeaderPenjualanDto dto = svcT.findOneHeaderDetail(noNota);
//		if (session.getAttribute("listDetail") != null) {
//			dto.setDetailTransaksi((List<TrDetailPenjualanDto>) session
//					.getAttribute("listDetail"));
//		}
		List<MstCustomerDto> listCustomer = svcC.findAll();
		
		session.setAttribute("listDetail", dto.getDetailTransaksi());
		model.addAttribute("customer", listCustomer);
		model.addAttribute("dto", dto);
		
		session.setAttribute("kondisi", "edit");
		session.setAttribute("noNota", noNota);
		return "editTransaksi";
	}

	@RequestMapping("/delete/{noNota}")
	public String deleteHeader(@PathVariable("noNota") String noNota) {
		svcT.deleteHeaderDetail(noNota);

		return "redirect:/transaksi/all";
	}

	@RequestMapping("deleteDetail/{kodeDetail}")
	public String deleteDetail(@PathVariable("kodeDetail") String kodeDetail, HttpServletRequest request) {
		// Jika kondisi ketika delete dari add Header maka yg dihapus adalah
		// detail yang berada di list
		HttpSession session = request.getSession();
		List<TrDetailPenjualanDto> list = (List<TrDetailPenjualanDto>) session.getAttribute("listDetail");
		
		if (session.getAttribute("kondisi") == "add"){
			for (TrDetailPenjualanDto detail : list){
				if (detail.getKodeDetail().equals(kodeDetail)){
					list.remove(detail);
				}
			}
			session.setAttribute("listDetail", list);
			return "redirect:/transaksi/add";
		}
		// Jika kondisi adalah delete dari edit Header maka detail yang dihapus
		// adalah detail yang berada di db		
		svcT.deleteDetail(kodeDetail);
		return "redirect:/transaksi/edit";
	}
}
