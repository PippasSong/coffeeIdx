package coffeeIdx.coffeeIdx.controller;

import java.util.List;

import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;
import coffeeIdx.coffeeIdx.service.CoffeeIdxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;




//REST API를 적용 HTTP URI로 리소스를 정의(명사형)하고 HTTP메소드(4가지)로 리소스에 대한 행위를 정의

@Controller //컨트롤러 어노테이션
public class CoffeeIdxController {
	@Autowired
	private CoffeeIdxService coffeeIdxService;
	
	//요청에 대한 주소, 요청 방법을 지정
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView openCoffeeIdxList() throws Exception{
		//뷰
		ModelAndView mv = new ModelAndView("/coffeeIdx/coffeeList"); //templates 경로
		
		List<CoffeeIdxDto> list = coffeeIdxService.selectCoffeeList(); //게시글 목록 조회
		mv.addObject("list", list); //실행된 비즈니스로직의 결과 값을 뷰에 'list'라는 이름으로 저장
		
		return mv;
	}

}
