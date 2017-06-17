package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.LedBoardVO;
import org.zerock.domain.LedSearchCriteria;
import org.zerock.domain.LedgerVO;
import org.zerock.domain.PageMaker;
import org.zerock.service.MainService;
import org.zerock.service.BoardService;
import org.zerock.service.LedBoardService;
import org.zerock.service.LedgerService;

@Controller
@RequestMapping("/ledger/*")
public class MainController {
	
	@Inject
	private MainService service;
	
	@Inject
	private LedBoardService Bservice;
	
	@Inject
	private LedgerService Lservice;
	
	
	private LedBoardVO ledBoardVO;
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void main(Model model) throws Exception {
	  logger.info("메인화면 호출");
	 
	  model.addAttribute("month1",service.countMonth("1"));
	  model.addAttribute("month2",service.countMonth("2"));
	  model.addAttribute("month3",service.countMonth("3"));
	  model.addAttribute("month4",service.countMonth("4"));
	  model.addAttribute("month5",service.countMonth("5"));
	  model.addAttribute("month6",service.countMonth("6"));
	  
	  //model.addAttribute("clickDate,")
	 
	}
	
	@RequestMapping(value = "/bbs", method = RequestMethod.GET)
	public void bbs(@ModelAttribute("cri") LedSearchCriteria cri, Model model) throws Exception {
		model.addAttribute("list", Bservice.listSearchCriteria(cri));
		logger.info("bbs 호출");
	}
	
	@RequestMapping(value = "/bbs/checkCreate", method = RequestMethod.POST)
	public String checkCreate(LedBoardVO board, Model model) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        String today = sdf.format(c1.getTime());
        
        
		if(Bservice.checkCreate(today) == 0){
			logger.info("create");
			Bservice.regist(board);
			Bservice.readToday(today);
		}
		else
		{
			logger.info("already");
			Bservice.readToday(today);
		}
		logger.info("checkCreate 호출");
		
		ledBoardVO = Bservice.readToday(today);
        int todayNum = ledBoardVO.getNum();
		
		return "redirect:/ledger/input?num="+todayNum;
	}
	

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public void input(@RequestParam("num") int num, @ModelAttribute("cri") LedSearchCriteria cri, Model model) throws Exception {
		ledBoardVO = Bservice.read(num);
		
		List<LedgerVO> ledgerVO = new ArrayList<LedgerVO>();
		ledgerVO = Lservice.listLedger(num);
		System.out.println("결과 값: " + ledgerVO.toString());
		
		String contents = ledBoardVO.getContents();
		String regdate = ledBoardVO.getDate();
		
		LedBoardVO vo = Bservice.read(num);
		
		
		System.out.println(vo);
		
		model.addAttribute("ledBoardVO", vo);
		model.addAttribute("ledgerVO", ledgerVO.toString());
		/*model.addAttribute("contents",contents);
		model.addAttribute("regdate",regdate);*/
		
		logger.info("input화면 호출 테스트");
		logger.info(contents);
		
		
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	  public String modifyPagingPOST(@RequestParam("num") int num, LedBoardVO board, RedirectAttributes rttr, Model model) throws Exception {

		System.out.println(Bservice.read(num));
		Bservice.modify(board);
	    rttr.addFlashAttribute("msg", "SUCCESS");
	    
	    return "redirect:/ledger/input?num=" + num;

	  }
	
	@RequestMapping("/list")
	public String showList() {
	  logger.info("show list method call");
	  return " show List Page";
	}
	
	@RequestMapping(value = "/readList", method = RequestMethod.GET)
	public String readList(@RequestParam("num") int num ) {
	  logger.info("show readlist method call");
	  return " show read List Page";
	}

	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	  public String remove(@RequestParam("bno") int bno, LedSearchCriteria cri, RedirectAttributes rttr) throws Exception {

	   // service.remove(bno);

	    rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("year", cri.getYear());
	    rttr.addAttribute("month", cri.getMonth());

	    rttr.addFlashAttribute("msg", "SUCCESS");

	    return "redirect:/sboard/list";
	  }

	 /* @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	  public void modifyPagingGET(int bno, @ModelAttribute("cri") LedSearchCriteria cri, Model model) throws Exception {

	   // model.addAttribute(service.read(bno));
	  }*/
	  
	  @RequestMapping("/getAttach/{num}")
	  @ResponseBody
	  public List<String> getAttach(@PathVariable("num")Integer num)throws Exception{
	    
	    return Bservice.getAttach(num);
	  } 

	
}
