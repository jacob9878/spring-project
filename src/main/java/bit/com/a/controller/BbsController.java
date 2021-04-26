package bit.com.a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.a.dto.BbsDto;
import bit.com.a.dto.BbsParam;
import bit.com.a.service.BbsService;

@Controller
public class BbsController {
	
	@Autowired
	BbsService service;
	
	@RequestMapping(value = "bbslist.do", method = RequestMethod.GET)
	public String bbslist(Model model) {		
		model.addAttribute("doc_title", "글목록");
		return "bbslist.tiles";
	}

	@ResponseBody
	@RequestMapping(value = "bbslistData.do", method = RequestMethod.GET)
	public List<BbsDto> bbslistData(BbsParam param) {		
		
		// paging 처리
		int sn = param.getPage();
		int start = sn * 10 + 1;	// 1 	11
		int end = (sn + 1) * 10; 	// 10   20
		
		param.setStart(start);
		param.setEnd(end);
				
		List<BbsDto> list = service.getBbslist(param);		
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "bbslistCount.do", method = RequestMethod.GET)
	public int bbslistCount(BbsParam param) {
		int count = service.getBbsCount(param);
		return count;
	}
	
	@RequestMapping(value = "bbswrite.do", method = RequestMethod.GET)
	public String bbswrite(Model model) {		
		model.addAttribute("doc_title", "글쓰기");
		
		return "bbswrite.tiles";
	}
	
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.POST)
	public String bbswriteAf(BbsDto bbs, Model model) throws Exception {
		if(bbs.getContent().equals("") || bbs.getTitle().equals("")){
			return "bbswrite.tiles";
		}
		service.writeBbs(bbs);
		return "redirect:/bbslist.do";
	}
	
	@RequestMapping(value = "bbsdetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String bbsdetail(int seq, Model model) {		
		model.addAttribute("doc_title", "상세글보기");
		
		service.readCount(seq);	// 조회수 증가		
		
		BbsDto bbs = service.getBbs(seq);
		model.addAttribute("bbs", bbs);
		
		return "bbsdetail.tiles";
	}
	
	@RequestMapping(value = "answer.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String answer(int seq, Model model) throws Exception {
		model.addAttribute("doc_title", "답글추가");
		BbsDto bbs=null;		
		bbs = service.getBbs(seq);
		model.addAttribute("bbs", bbs);
		return "answer.tiles";
	}
	
	@RequestMapping(value = "answerAf.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String answerAf(BbsDto bbs, Model model) {		
		service.reply(bbs);		
		return "redirect:/bbslist.do";
	}
	
	@RequestMapping(value = "bbsdelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteBbs(int seq, Model model) {		
		service.deleteBbs(seq);
		return "redirect:/bbslist.do";		
	}
	
	@RequestMapping(value = "bbsupdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String bbsupdate(int seq, Model model){	
		model.addAttribute("doc_title", "글수정");
		BbsDto bbs=service.getBbs(seq);		
		model.addAttribute("bbs", bbs);		
		return "bbsupdate.tiles";
	}
	
	@RequestMapping(value = "bbsupdateAf.do", method = RequestMethod.POST)
	public String bbsupdateAf(BbsDto bbs,Model model) {		
		service.updateBbs(bbs);
		return "redirect:/bbslist.do";
	}
}







