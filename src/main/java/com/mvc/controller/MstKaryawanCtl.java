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
import com.mvc.dto.MstKaryawanDto;
import com.mvc.dto.MstKaryawanLoginDto;
import com.mvc.service.MstKaryawanSvc;

@Controller
@RequestMapping("karyawan")
public class MstKaryawanCtl {

	@Autowired
	private MstKaryawanSvc svc;
	String kondisi = "";
	
	@RequestMapping("add")
	public String add(Model model){
			MstKaryawanDto dto = new MstKaryawanDto();
			model.addAttribute("dto", dto);
			model.addAttribute("kodeTerakhir", kodeTerakhir());
			kondisi = "add";
			return "addKaryawan";
	}
	
	@RequestMapping("save")
	public String save(@Valid @ModelAttribute("dto") MstKaryawanDto dto, BindingResult result,
			Model model){
		MstKaryawanDto findOne = svc.findOneKaryawan(dto.getKodeKaryawan());
		if (findOne == null){
			if(result.hasErrors()){
				return "addKaryawan";
			} else {
				model.addAttribute("validPassword", isValidPassword(dto.getPassword()));
				if(!isValidPassword(dto.getPassword())){
			        String valid = "Password harus lebih dari 8 karakter dan kurang dari 20 karakter, "
			        		+ "harus memiliki paling tidak satu huruf kapital dan satu angka.";
					model.addAttribute("formatPassword", valid);
					model.addAttribute("stat", 1);
					return "addKaryawan";
				} else {
					svc.saveKaryawan(dto);
					return "redirect:/karyawan/pagekaryawan";
				}	
			}
		} else {
			if(kondisi.equalsIgnoreCase("add")){
				model.addAttribute("valid", "Kode Karyawan Sudah Ada");
				model.addAttribute("stat", 1);
				return "addKaryawan";
			} else {
				if(result.hasErrors()){
					return "editKaryawan";
				} else {
					model.addAttribute("validPassword", isValidPassword(dto.getPassword()));
					if(!isValidPassword(dto.getPassword())){
				        String valid = "Password harus lebih dari 8 karakter dan kurang dari 20 karakter, "
				        		+ "harus memiliki paling tidak satu huruf kapital dan satu angka.";
						model.addAttribute("formatPassword", valid);
						model.addAttribute("stat", 1);
						return "editKaryawan";
					} else {
						svc.saveKaryawan(dto);
						return "redirect:/karyawan/pagekaryawan";
					}
				}
			}
		}
	}
	
	@RequestMapping("findone/{kodeKaryawan}")
	public String detail(Model model, @PathVariable("kodeKaryawan") String kodeKaryawan){
		MstKaryawanDto dto = svc.findOneKaryawan(kodeKaryawan);
		model.addAttribute("dto", dto);
		kondisi = "detail";
		return "editKaryawan";
	}
	
	@RequestMapping("delete/{kodeKaryawan}")
	public String delete(@PathVariable("kodeKaryawan") String kodeKaryawan){
		svc.deleteKaryawan(kodeKaryawan);
		return "redirect:/karyawan/pagekaryawan";
	}
	
	@RequestMapping("pagekaryawan")
	public String listPendudukWithPaging(Model model, 
			@RequestParam(value = "cari", defaultValue = "", required = false) String cari,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page){

			Map<String, Object> map = svc.listAllPageKaryawan(cari, page);
			List<MstKaryawanDto> list = (List<MstKaryawanDto>) map.get("list");
			int totalHalaman = (int) map.get("jumlah");
			model.addAttribute("karyawan", list);
			model.addAttribute("total", totalHalaman);
			
			if(cari.length() > 0){
				String out = String.format("Berikut Adalah Hasil Pencarian : %s", cari);
				model.addAttribute("keterangan",out);
			}
			if(list.isEmpty()){
				String out = String.format("Hasil pencarian '%s' tidak ditemukan. ", cari);
				model.addAttribute("penjelasan",out);
			}
			return "pageKaryawan";	
		
	}
	
	@RequestMapping("login")
	public String login(Model model,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		
		MstKaryawanLoginDto dto = new MstKaryawanLoginDto();
		
		model.addAttribute("loginKaryawan", dto);
		session.removeAttribute("loginUser");
		return "loginMenu";
	}
	
	@RequestMapping("/id")
	public String checkLogin(@Valid @ModelAttribute("loginKaryawan")MstKaryawanLoginDto dto,
			BindingResult result, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		if (!result.hasErrors()){
			MstKaryawanLoginDto kar = svc.login(dto.getUsername());
			if (kar != null){
				if (!kar.getPassword().equals(dto.getPassword())){
					model.addAttribute("error", "Password salah");
					return "loginMenu";
				}
				session.setAttribute("loginUser", kar);
				return "redirect:/transaksi/all";
			}
			model.addAttribute("error", "Username salah");
		}
		return "loginMenu";
	}
	
	@RequestMapping("findoneLogin/{kode}")
	public String findoneLogin(Model model, @PathVariable("kode") String kode){
		MstKaryawanDto dto = svc.findOneKaryawan(kode);
		model.addAttribute("dto", dto);
		kondisi = "detail";
		return "findoneLogin";
	}
	
	public String kodeTerakhir(){
		String out = ""; 
		List<MstKaryawanDto> kodeTerakhir = svc.findAllKaryawan();
		if(kodeTerakhir.size() < 10){
			out = String.format(", Kode Karyawan yang terdaftar terakhir K00%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 10 && kodeTerakhir.size() < 100){
			out = String.format(", Kode Karyawan yang terdaftar terakhir K0%d", kodeTerakhir.size());
		}else if(kodeTerakhir.size() > 100){
			out = String.format(", Kode Karyawan yang terdaftar terakhir K%d", kodeTerakhir.size());
		}return out;
	}
	
	public boolean isValidPassword(String password){
		boolean isValid = true;
        if (password.length() > 15 || password.length() < 8)
        {
	        isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
        	isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            isValid = false;
        }
        return isValid; 
    }
}
