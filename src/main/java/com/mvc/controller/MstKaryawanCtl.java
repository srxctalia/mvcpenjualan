package com.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.dto.MstKaryawanDto;
import com.mvc.service.MstKaryawanSvc;

@Controller
@RequestMapping("karyawan")
public class MstKaryawanCtl {

	@Autowired
	private MstKaryawanSvc svc;
	String kondisi = "";
	
//	@RequestMapping("add")
//	public String add(Model model, HttpServletRequest request){
//		HttpSession session = request.getSession();
//		if(session.getAttribute("login") == null){
//			return "redirect:/karyawan/login";
//		} else {
//			MstKaryawanDto dto = new MstKaryawanDto();
//			model.addAttribute("dto", dto);
//			return "addKaryawan";
//		}
//	}
	
	@RequestMapping("add")
	public String add(Model model){
			MstKaryawanDto dto = new MstKaryawanDto();
			model.addAttribute("dto", dto);
			return "addKaryawan";
		}
	
	
	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("dto") MstKaryawanDto dto, BindingResult result){
		MstKaryawanDto findOne = svc.findOneKaryawan(dto.getKodeKaryawan());
		if (findOne == null){
			if(result.hasErrors()){
				return "addKaryawan";
			} else {
				svc.saveKaryawan(dto);
				return "redirect:/karyawan/pagekaryawan";	
			}
		} else {
			if(kondisi.equalsIgnoreCase("add")){
				dto.setKodeKaryawan("Kode Karyawan sudah ada! masukkan input lain.");
				return "addKaryawan";
			} else {
				if(result.hasErrors()){
					return "editKaryawan";
				} else {
					svc.saveKaryawan(dto);
					return "redirect:/karyawan/pagekaryawan";	
				}
			}
		}
	}
	
	@RequestMapping("findone/{kode}")
	public String detail(Model model, @PathVariable("kode") String kode){
		MstKaryawanDto dto = svc.findOneKaryawan(kode);
		model.addAttribute("dto", dto);
		kondisi = "detail";
		return "editKaryawan";
	}
	
	@RequestMapping("delete/{kode}")
	public String delete(@PathVariable("kode") String kode){
		svc.deleteKaryawan(kode);
		return "redirect:/karyawan/pagekaryawan";
	}
	
//	@RequestMapping("pageKaryawan")
//	public String listPendudukWithPaging(Model model, 
//			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
//			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
//			HttpServletRequest request){
//		HttpSession session = request.getSession();
//		if(session.getAttribute("login") == null){
//			return "redirect:/user/login";
//		} else {
//			String userID = (String) session.getAttribute("login");
//			Map<String, Object> map = svc.listAllPageKaryawan(cari, page);
//			List<MstKaryawanDto> list = (List<MstKaryawanDto>) map.get("list");
//			int totalHalaman = (int) map.get("jumlah");
//			model.addAttribute("karyawan", list);
//			model.addAttribute("total", totalHalaman);
//			model.addAttribute("userID", userID);
//			
//			return "pageKaryawan";	
//		}
//	}
	
//	@RequestMapping("pagekaryawan")
//	public String listPendudukWithPaging(Model model,  
//			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
//			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
//			HttpServletRequest request){
//			
//			HttpSession session = request.getSession();
//			
//			Map<String, Object> map = svc.listAllPageKaryawan(cari, page);
//			List<MstKaryawanDto> list = (List<MstKaryawanDto>) map.get("list");
//			MstKaryawanDto level = (MstKaryawanDto) session.getAttribute("log");
//		
//			if (level.getLevel() == 1){
////				Map<String, Object> map = svc.listAllPageKaryawan(cari, page);
////				List<MstKaryawanDto> list = (List<MstKaryawanDto>) map.get("list");
//				int totalHalaman = (int) map.get("jumlah");
//				model.addAttribute("karyawan", list);
//				model.addAttribute("total", totalHalaman);
//			} else {
//				MstKaryawanDto dto = svc.findOneKaryawan(level.getKodeKaryawan());
////				model.addAttribute("dto", dto);
//				int totalHalaman = (int) map.get("jumlah");
//				model.addAttribute("karyawan", list);
//				model.addAttribute("total", totalHalaman);
//			}
//			
//			return "pageKaryawan";	
//	}
	
	@RequestMapping("pagekaryawan")
	public String listPendudukWithPaging(Model model, 
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			HttpServletRequest request){
		HttpSession session = request.getSession();
//		
//		String userID = (String) session.getAttribute("login");
//		Map<String, Object> map = svc.listAllPageKaryawan(cari, page);
//		List<MstKaryawanDto> list = (List<MstKaryawanDto>) map.get("list");
		
		if(session.getAttribute("log") == null){
			return "redirect:/karyawan/login";
		} else {
			MstKaryawanDto kar = (MstKaryawanDto) session.getAttribute("log");
			if(kar.getLevel() == 1){
				String userID = (String) session.getAttribute("login");
				Map<String, Object> map = svc.listAllPageKaryawan(cari, page);
				List<MstKaryawanDto> list = (List<MstKaryawanDto>) map.get("list");
				int totalHalaman = (int) map.get("jumlah");
				model.addAttribute("karyawan", list);
				model.addAttribute("total", totalHalaman);
				model.addAttribute("userID", userID);
			} else {
				MstKaryawanDto karyawan = (MstKaryawanDto) session.getAttribute("seorangKar");
//				String userID = (String) session.getAttribute("login");
//				MstKaryawanDto dto = svc.findOneKaryawan(karyawan.getKodeKaryawan());
//				List<MstKaryawanDto> list = (List<MstKaryawanDto>) map.get("list");
//				int totalHalaman = (int) map.get("jumlah");
//				model.addAttribute("karyawan", list);
//				model.addAttribute("total", totalHalaman);
				model.addAttribute("seorang", karyawan);
				model.addAttribute("userID", karyawan.getUsername());
			}
			
			return "pageKaryawan";	
		}
	}
	
	@RequestMapping("login")
	public String login(Model model, @Valid @ModelAttribute("dto") MstKaryawanDto dto, BindingResult result,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		try {
			MstKaryawanDto login = svc.login(dto.getUsername());
			if(result.hasErrors()){
				return "loginMenu";
			} else {
				if(login.getPassword().equals(dto.getPassword())){
					MstKaryawanDto karyawan = svc.login(dto.getUsername());
					session.setAttribute("seorangKar", karyawan);
//					session.setAttribute("login", login.getUsername());
//					session.setMaxInactiveInterval(5); //satuannya second
					return "redirect:/karyawan/pagekaryawan";
//					if(login.getLevel() == dto.getLevel()){
//						if(login.getLevel() == 1){
//							return "redirect:/karyawan/pagekaryawan";
//						} else {
//							return "redirect:/karyawan/findone";
//						}
//					} else {
//						return "redirect:/karyawan/pagekaryawan";
//					}
				} else {
					String error = "Username dan Password salah!";
					model.addAttribute("salah", error);
					return "loginMenu";
				}
			}
		} catch (Exception e) {
			String error = "Username dan Password salah!";
			model.addAttribute("salah", error);
			return "loginMenu";
		}
	}
	
	@RequestMapping("findoneLogin/{kode}")
	public String findoneLogin(Model model, @PathVariable("kode") String kode){
		MstKaryawanDto dto = svc.findOneKaryawan(kode);
		model.addAttribute("dto", dto);
		kondisi = "detail";
		return "findoneLogin";
	}
	
}
