package coffeeIdx.coffeeIdx.controller;

import coffeeIdx.coffeeIdx.dto.CoffeeDetailDto;
import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;
import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.dto.RequestDto;
import coffeeIdx.coffeeIdx.service.CoffeeIdxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Api(description="게시판 REST API")  //컨트롤러에 설명 추가
@SessionAttributes("member")
@RestController //@controller와 @responsebody어노테이션을 합친 어노테이션. 응답 결과를 JSON형식으로 만들어 준다
public class CoffeeIdxApiController {
	
	@Autowired
	CoffeeIdxService coffeeIdxService;
	
	@ApiOperation(value = "카페 목록 조회")
	@RequestMapping(value="/api/index", method=RequestMethod.GET)
	public List<CoffeeIdxDto> openCoffeeIdxList() throws Exception{  //결과를 view에 보내지 않고 조회 결과를 바로 api의 응답 결과로 사용
		return coffeeIdxService.selectCoffeeList();
	}
	
	@ApiOperation(value = "카페 상세정보 조회")
	@RequestMapping(value="/api/index/{cafeName}", method=RequestMethod.GET)
	public CoffeeDetailDto openCoffeeIdxDetail(@PathVariable("cafeName") @ApiParam(value = "카페명*카페 주소") String cafeName) throws Exception{ //매개변수 설명
		
		String[] nameAry = cafeName.split("\\*"); //이름과 주소로 구분)
		
		return coffeeIdxService.selectCoffeeDetail(nameAry[0], nameAry[1]);
	}
	
	//get은 요청 주소에 파라미터를 같이 보내고 post는 파라미터를 http패킷의 바디에 담아서 전송. @requestbody는 메서드의 파라미터가 반드시 http패킷의 바디에 담겨 있어야 함을 의미.post나 put을 사용하는 메서드에 사용
	@ApiOperation(value = "요청 등록화면 조회")
	@RequestMapping(value="/api/index/request", method=RequestMethod.GET)  //요청 등록화면 요청
	public ModelAndView openCoffeeIdxRequest() throws Exception{
		ModelAndView mv = new ModelAndView("/coffeeIdx/requestList");
		
		List<RequestDto> list = coffeeIdxService.selectRequestList(); //요청 목록 조회
		mv.addObject("list", list);
		
		return mv;
	}
	
	@ApiOperation(value = "요청 등록")
	@RequestMapping(value="/api/index/request", method=RequestMethod.POST)
	public String insertCoffeeIdxRequest(@ModelAttribute("member") MemberDto member, String address, String creatorId) throws Exception{
		coffeeIdxService.insertRequest(address, creatorId);
		return "redirect:/index/request";
	}
}
