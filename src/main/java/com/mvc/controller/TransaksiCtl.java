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
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null) {
			return "redirect:/karyawan/login";
		}
		if (kar.getLevel().equals("1")) {
			Map<String, Object> map = svcT.listAll(cari, page);
			List<TrHeaderPenjualanDto> list = (List<TrHeaderPenjualanDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");

			if(cari.length() > 0){
				String out = String.format("Berikut Adalah Hasil Pencarian : %s", cari);
				model.addAttribute("keterangan",out);
			}
			if(list.isEmpty()){
				String out = String.format("Hasil pencarian '%s' tidak ditemukan. ", cari);
				model.addAttribute("penjelasan",out);
			}
			
			session.removeAttribute("dtoH");
			session.removeAttribute("listDetail");
			session.removeAttribute("kondisi");
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
//		try {
			session.removeAttribute("listDetail");
			model.addAttribute("usr", kar.getNamaKaryawan());
			TrHeaderPenjualanDto dtoH = new TrHeaderPenjualanDto();
			
			List<MstCustomerDto> listCustomer = svcC.findAll();
			if (session.getAttribute("dtoH") != null) {
				dtoH = (TrHeaderPenjualanDto) session.getAttribute("dtoH");
			}
			if (session.getAttribute("listDetail") != null) {
				dtoH.setDetailTransaksi((List<TrDetailPenjualanDto>) session.getAttribute("listDetail"));
			}
			
			model.addAttribute("dtoH", dtoH);
			model.addAttribute("customer", listCustomer);
			
			dtoH.setTanggalTransaksi(Date.valueOf(LocalDate.now()));
			dtoH.setKodeKaryawan(kar.getKodeKaryawan());
			dtoH.setNamaKaryawan(kar.getNamaKaryawan());
			
			session.setAttribute("kondisi", "add");
			model.addAttribute("kodeTerakhir", kodeTerakhir());
			
			return "addTransaksi";
			
//		} catch (Exception e) {
//			// TODO: handle exception
//			return "addTransaksi";
//		}

	}
	
	@RequestMapping("/save")
	public String saveHeader(
			@Valid @ModelAttribute("dtoH") TrHeaderPenjualanDto dtoH,
			BindingResult result, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null) {
			return "redirect:/karyawan/login";
		}
		try {
				String kondisi = (String)session.getAttribute("kondisi");
				List<MstCustomerDto> listCustomer = svcC.findAll();
				if (kondisi.equalsIgnoreCase("add")){
					if (!result.hasErrors()) {
						if (svcT.findOneHeaderDetail(dtoH.getNoNota()) != null) {
							model.addAttribute("error", "No Nota sudah pernah dibuat");
						}
						if (session.getAttribute("listDetail") == null){
							model.addAttribute("error", "Belum terdapat detail transaksi");
						}
						dtoH.setDetailTransaksi((List<TrDetailPenjualanDto>)session.getAttribute("listDetail"));
						int grandTotal=0;
						for (TrDetailPenjualanDto detail : dtoH.getDetailTransaksi()){
							grandTotal+=detail.getSubtotal();			
						}
						dtoH.setHargaTotal(grandTotal - (grandTotal*dtoH.getGlobalDiskon()/100));
						svcT.saveHeader(dtoH);
						return "redirect:/transaksi/all";				
					}
					model.addAttribute("customer", listCustomer);
					model.addAttribute("stat", 1);
					return "redirect:/transaksi/add";
				} else {
					if (!result.hasErrors()){
						if (session.getAttribute("listDetail") != null){
							dtoH.setDetailTransaksi((List<TrDetailPenjualanDto>)session.getAttribute("listDetail"));						
						}
						int grandTotal=0;
						for (TrDetailPenjualanDto detail : dtoH.getDetailTransaksi()){
							grandTotal+=detail.getSubtotal();			
						}
						dtoH.setHargaTotal(grandTotal - (grandTotal*dtoH.getGlobalDiskon()/100));
						svcT.saveHeader(dtoH);
						return "redirect:/transaksi/all";
					}
					model.addAttribute("customer", listCustomer);
					model.addAttribute("stat", 1);
					return "redirect:/transaksi/edit/"+dtoH.getNoNota();
				}

			} catch (Exception e) {
				// TODO: handle exception
				return "redirect:/transaksi/all";
			}
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
		model.addAttribute("usr", kar.getNamaKaryawan());
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
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session
				.getAttribute("loginUser");
//		try {
			if (kar == null) {
				return "redirect:/karyawan/login";
			}
			String kondisi = (String)session.getAttribute("kondisi");
			List<TrDetailPenjualanDto> listDetail = new ArrayList<TrDetailPenjualanDto>();
			MstBarangDto br = svcB.findOneBarang(dtoD.getKodeBarang());
			List<MstBarangDto> listbarang = svcB.findAllBarang();
			// Kondisi save detail pada add header
			if (kondisi.equalsIgnoreCase("add")){
				if (!result.hasErrors()){
					if (session.getAttribute("listDetail")!=null){
						if (svcT.findOneDetaiil(dtoD.getKodeDetail()) != null) {
							model.addAttribute("error", "KodeDetail sudah pernah dibuat");
						} else {
							listDetail = (List<TrDetailPenjualanDto>) session.getAttribute("listDetail");
							for (TrDetailPenjualanDto detail : listDetail){
								if (detail.getKodeDetail().equals(dtoD.getKodeDetail())){
									model.addAttribute("error", "kodeDetail sudah dibuat");
									break;
								}
							}
						}
						if (br.getStokBarang() < dtoD.getQty()){
							model.addAttribute("error", "Stok barang tidak tercukupi");							
						} else {
							dtoD.setNamaBarang(br.getNamaBarang());
							dtoD.setSubtotal((dtoD.getHargaSatuan()*dtoD.getQty()) - (dtoD.getHargaSatuan()*dtoD.getQty()*dtoD.getDiskon()/100));
							listDetail.add(dtoD);
							session.setAttribute("listDetail", listDetail);
							return "redirect:/transaksi/add";
						}
						model.addAttribute("usr", kar.getNamaKaryawan());
						model.addAttribute("dtoD", dtoD);
						model.addAttribute("barang", listbarang);
						return "redirect:/transaksi/addDetail";
						
					}
					// kondisi jika null pada session "listDetail"
					if (svcT.findOneDetaiil(dtoD.getKodeDetail()) != null) {
						model.addAttribute("error", "KodeDetail sudah pernah dibuat");						
					} else {
						if (br.getStokBarang() < dtoD.getQty()){
							model.addAttribute("error", "Stok barang tidak tercukupi");							
						} else {
							dtoD.setNamaBarang(br.getNamaBarang());
							dtoD.setSubtotal((dtoD.getHargaSatuan()*dtoD.getQty()) - (dtoD.getHargaSatuan()*dtoD.getQty()*dtoD.getDiskon()/100));
							listDetail.add(dtoD);
							session.setAttribute("listDetail", listDetail);
							return "redirect:/transaksi/add";
						}						
					}
				}
				model.addAttribute("usr", kar.getNamaKaryawan());
				model.addAttribute("dtoD", dtoD);
				model.addAttribute("barang", listbarang);
				return "redirect:/transaksi/addDetail";
			}
			// Kondisi save detail pada Edit header
			if (!result.hasErrors()){
				if (session.getAttribute("listDetail")!=null){
					listDetail = (List<TrDetailPenjualanDto>) session.getAttribute("listDetail");
					if (svcT.findOneDetaiil(dtoD.getKodeDetail()) == null) {
						for (TrDetailPenjualanDto detail : listDetail){
							if (detail.getKodeDetail().equals(dtoD.getKodeDetail())){
								model.addAttribute("error", "kodeDetail sudah dibuat");
								break;
							}
						}
					} else {
						model.addAttribute("error", "KodeDetail sudah pernah dibuat");
					}
					if (br.getStokBarang() < dtoD.getQty()){
						model.addAttribute("error", "Stok barang tidak tercukupi");						
					} else {
						dtoD.setNamaBarang(br.getNamaBarang());
						dtoD.setSubtotal((dtoD.getHargaSatuan()*dtoD.getQty()) - (dtoD.getHargaSatuan()*dtoD.getQty()*dtoD.getDiskon()/100));
						listDetail.add(dtoD);
						session.setAttribute("listDetail", listDetail);
						return "redirect:/transaksi/edit/"+session.getAttribute("noNota");
					}
					model.addAttribute("usr", kar.getNamaKaryawan());
					model.addAttribute("dtoD", dtoD);
					model.addAttribute("barang", listbarang);
					return "redirect:/transaksi/addDetail";
				}
				// kondisi jika null pada session "listDetail"
				if (svcT.findOneDetaiil(dtoD.getKodeDetail()) != null) {
					if (br.getStokBarang() < dtoD.getQty()){
						model.addAttribute("error", "Stok barang tidak tercukupi");
					} else {
						TrHeaderPenjualanDto dto =  (TrHeaderPenjualanDto) session.getAttribute("dtoH");
						listDetail = dto.getDetailTransaksi();
						dtoD.setNamaBarang(br.getNamaBarang());
						dtoD.setSubtotal((dtoD.getHargaSatuan()*dtoD.getQty()) - (dtoD.getHargaSatuan()*dtoD.getQty()*dtoD.getDiskon()/100));
						listDetail.add(dtoD);
						session.setAttribute("listDetail", listDetail);
						return "redirect:/transaksi/edit/"+session.getAttribute("noNota");
					}
				}
				model.addAttribute("error", "KodeDetail sudah pernah dibuat");
			}
			model.addAttribute("usr", kar.getNamaKaryawan());
			model.addAttribute("dtoD", dtoD);
			model.addAttribute("barang", listbarang);
			return "redirect:transaksi/addDetail";			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return "redirect:/transaksi/all";
//		}
		
		 
	}

	@RequestMapping("/edit/{noNota}")
	public String edit(@PathVariable("noNota") String noNota, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		MstKaryawanLoginDto kar = (MstKaryawanLoginDto) session.getAttribute("loginUser");
		if (kar == null) {
			return "redirect:/karyawan/login";
		}
		session.removeAttribute("listDetail");
		model.addAttribute("usr", kar.getNamaKaryawan());
		TrHeaderPenjualanDto dtoH = svcT.findOneHeaderDetail(noNota);
		if (session.getAttribute("listDetail")!= null){
			dtoH.setDetailTransaksi((List<TrDetailPenjualanDto>) session.getAttribute("listDetail"));			
		}
		List<MstCustomerDto> listCustomer = svcC.findAll();
		
		session.setAttribute("dtoH", dtoH);
		model.addAttribute("customer", listCustomer);
		model.addAttribute("dtoH", dtoH);
		
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
		HttpSession session = request.getSession();
		List<TrDetailPenjualanDto> list = (List<TrDetailPenjualanDto>) session.getAttribute("listDetail");
		if (session.getAttribute("kondisi") == "add"){
			for (TrDetailPenjualanDto detail : list){
				if (detail.getKodeDetail().equalsIgnoreCase(kodeDetail)){
					list.remove(detail);
				}
			}
			session.setAttribute("listDetail", list);
			return "redirect:/transaksi/add";
		}
		if (svcT.findOneDetaiil(kodeDetail) == null){
			for (TrDetailPenjualanDto detail : list){
				if (detail.getKodeDetail().equalsIgnoreCase(kodeDetail)){
					list.remove(detail);
				}
			}
			session.setAttribute("listDetail", list);
			return "redirect:/transaksi/edit/"+session.getAttribute("noNota");
		}
		svcT.deleteDetail(kodeDetail);			
		return "redirect:/transaksi/edit/"+session.getAttribute("noNota");
	}
	
	public String kodeTerakhir(){
		String out = ""; 
		List<TrHeaderPenjualanDto> kodeTerakhir = svcT.findAllHeader();
		if(kodeTerakhir.size() < 10){
			out = String.format(", No Nota yang terdaftar terakhir TR00%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 10 && kodeTerakhir.size() < 100){
			out = String.format(", No Nota yang terdaftar terakhir TR0%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 100){
			out = String.format(", No Nota yang terdaftar terakhir TR%d", kodeTerakhir.size());
		}return out;
	}
}
