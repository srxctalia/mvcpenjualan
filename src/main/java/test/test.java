package test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.mvc.dao.MstKaryawanDao;
import com.mvc.dto.MstKaryawanDto;
import com.mvc.entity.MstKaryawan;

public class test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		
		MstKaryawanDao dao = ctx.getBean(MstKaryawanDao.class);
		//untuk menampilkan data per page.
/*		String cari = "";
		Pageable page = new PageRequest(0, 2, //page 0, isi 2 tampilan 
				new Sort(new Sort.Order(
						Direction.fromString("asc"),"namaKaryawan")));
		
		List<MstKaryawan> list = dao.search(cari, page);
		for(MstKaryawan p :list){
			System.out.println("nama : "+p.getNamaKaryawan());
		}*/
		MstKaryawan findOne = dao.searchByUsername("Upin12");
		MstKaryawanDto dto = new MstKaryawanDto();
		
//		int countData = dao.countData(""); //untuk menghitung jumlah data
//		System.out.println(countData);
		
	}
}
