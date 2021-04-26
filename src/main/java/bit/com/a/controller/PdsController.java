package bit.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bit.com.a.dto.PdsDto;
import bit.com.a.service.PdsService;
import bit.com.a.util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;
	
	@RequestMapping(value = "pdslist.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdslist(Model model) {
		model.addAttribute("doc_title", "자료실 목록");
		
		List<PdsDto> list = service.getPdsList();
		model.addAttribute("pdslist", list);
		
		return "pdslist.tiles";
	}
	
	@RequestMapping(value = "pdswrite.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdswrite(Model model) {
		model.addAttribute("doc_title", "자료 올리기");
		
		return "pdswrite.tiles";
	}
	
	@RequestMapping(value = "pdsupload.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdsupload(PdsDto pdsdto, 
						@RequestParam(value = "fileload", required = false)
						MultipartFile fileload, 
						HttpServletRequest req) {
		
		// filename 취득
		String filename = fileload.getOriginalFilename();
		pdsdto.setFilename(filename);	// 원본 파일명을 설정
		
		// upload 경로 설정
		// server(tomcat)
		String fupload = req.getServletContext().getRealPath("/upload");
		
		// 폴더
		// String fupload = "d:\\tmp";
		
		System.out.println("fupload:" + fupload);
		
		// 파일명 변경 처리
		String newfilename = PdsUtil.getNewFileName(pdsdto.getFilename());		
		pdsdto.setNewfilename(newfilename);
		
		File file = new File(fupload + "/" + newfilename); 
		
		try {
			// 실제로 업로드 되는 부분
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			
			// db에 저장
			service.uploadPds(pdsdto);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/pdslist.do";
	}
	
	@RequestMapping(value = "fileDownload.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String fileDownload(String newfilename, int seq, HttpServletRequest req, Model model) {
		
		// 경로
		// server
		String fupload = req.getServletContext().getRealPath("/upload");
		
		// 폴더
	//	String fupload = "d:\\tmp";
		
		File downloadFile = new File(fupload + "/" + newfilename);
		
		model.addAttribute("downloadFile", downloadFile);
		model.addAttribute("seq", seq);
		
		return "downloadView";
	}
	
	@RequestMapping(value = "pdsdetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdsdetail(int seq, Model model) {
		model.addAttribute("doc_title", "자료실 상세글");
		
		service.readCount(seq);
		PdsDto pds = service.getPds(seq);
		model.addAttribute("pds", pds);
		
		return "pdsdetail.tiles";
		
	}
	
	@RequestMapping(value = "pdsupdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdsupdate(int seq, Model model) {
		
		  model.addAttribute("doc_title", "수정"); PdsDto pds = service.getPds(seq);
		  model.addAttribute("pds", pds);
		 
		
		return "pdsupdate.tiles";
	}
	@RequestMapping(value = "pdsupdateAf.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String pdsupdateAf(PdsDto pdsdto, Model model,
			@RequestParam(value = "fileload", required = false)
			MultipartFile fileload, 
			HttpServletRequest req) {
		// filename 취득
		String filename = fileload.getOriginalFilename();
		pdsdto.setFilename(filename);	// 원본 파일명을 설정
				
		System.out.println("dto ="+pdsdto.toString()); 
		
		// upload 경로 설정
		// server(tomcat)
		String fupload = req.getServletContext().getRealPath("/upload");
				
		// 폴더
		// String fupload = "d:\\tmp";
				
			System.out.println("fupload:" + fupload);
				
		// 파일명 변경 처리
		String newfilename = PdsUtil.getNewFileName(pdsdto.getFilename());		
		pdsdto.setNewfilename(newfilename);
				
		File file = new File(fupload + "/" + newfilename); 
				
		try {
		// 실제로 업로드 되는 부분
		FileUtils.writeByteArrayToFile(file, fileload.getBytes());
					
		// db에 저장
		service.updateBbs(pdsdto);
					
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}	
		return "redirect:/pdslist.do";
	}
	@RequestMapping(value = "pdsdelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String deletePds(int seq, Model model) {
		service.deletePds(seq);
		return "redirect:/pdslist.do";
	}
	
}

























