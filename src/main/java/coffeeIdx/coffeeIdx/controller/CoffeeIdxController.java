package coffeeIdx.coffeeIdx.controller;

import java.util.List;

import coffeeIdx.coffeeIdx.dto.CoffeeDetailDto;
import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;
import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.dto.RequestDto;
import coffeeIdx.coffeeIdx.service.CoffeeIdxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//REST API를 적용 HTTP URI로 리소스를 정의(명사형)하고 HTTP메소드(4가지)로 리소스에 대한 행위를 정의
@SessionAttributes("member")
@Controller //컨트롤러 어노테이션
public class CoffeeIdxController {
	//로거 적용
//	private Logger log = LoggerFactory.getLogger(this.getClass());  //로거의 이름에는 보통 클래스 객체를 넘겨준다
	
	@ModelAttribute("member") //setMember 메소드가 리턴한 memberDto객체가 가장 먼저 세션에 등록된다
	public MemberDto setMember() {
		return new MemberDto();
	}
	
	
	@Autowired
	private CoffeeIdxService coffeeIdxService;
	
	//요청에 대한 주소, 요청 방법을 지정
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView openCoffeeIdxList() throws Exception{
		//debug용의 로그를 출력
//		log.debug("openCoffeeIdxList");
		//뷰
		ModelAndView mv = new ModelAndView("/coffeeIdx/coffeeList"); //templates 경로
		
		List<CoffeeIdxDto> list = coffeeIdxService.selectCoffeeList(); //게시글 목록 조회
		mv.addObject("list", list); //실행된 비즈니스로직의 결과 값을 뷰에 'list'라는 이름으로 저장
		
		return mv;
	}
	
	//@pathvariable 은 메서드의 파라미터가 uri의 변수로 사용되는 것을 의미 cafename라는 이름의 파라미터가 {cafename}에 바인드된다 
	@RequestMapping(value="/index/{cafeName}", method=RequestMethod.GET)
	public ModelAndView openCoffeeIdxDetail(@PathVariable("cafeName") String cafeName) throws Exception{
		ModelAndView mv = new ModelAndView("/coffeeIdx/coffeeDetail");  //뷰 지정
		
		String[] nameAry = cafeName.split("\\*"); //이름과 주소로 구분)
		CoffeeDetailDto detail = coffeeIdxService.selectCoffeeDetail(nameAry[0], nameAry[1]);
		mv.addObject("detail", detail);
		
		return mv;
	}
	
	@RequestMapping(value="/index/request", method=RequestMethod.GET)  //요청 등록화면 요청
	public ModelAndView openCoffeeIdxRequest() throws Exception{
		ModelAndView mv = new ModelAndView("/coffeeIdx/requestList");
		
		
		
		
		List<RequestDto> list = coffeeIdxService.selectRequestList(); //요청 목록 조회
		mv.addObject("list", list);
		
		return mv;
	}
	
	//매개변수로 @ModelAttribute를 사용해 세션에 등록된 "member"라는 이름의 객체를 member 변수에 바인딩, 로그인 여부 활용에 사용
	@RequestMapping(value="/index/request", method=RequestMethod.POST)
	public String insertCoffeeIdxRequest(@ModelAttribute("member") MemberDto member, String address, String creatorId) throws Exception{
//security 사용으로 비활성화
//		if(member.getId() == null) {
//			return "redirect:/index/login";
//		}
		coffeeIdxService.insertRequest(address, creatorId);
		return "redirect:/index/request";
	}

}
