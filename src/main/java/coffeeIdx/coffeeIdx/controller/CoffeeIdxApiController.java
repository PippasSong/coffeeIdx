package coffeeIdx.coffeeIdx.controller;

import coffeeIdx.coffeeIdx.dto.CoffeeDetailDto;
import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;
import coffeeIdx.coffeeIdx.service.CoffeeIdxService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController //@controller와 @responsebody어노테이션을 합친 어노테이션. 응답 결과를 JSON형식으로 만들어 준다
public class CoffeeIdxApiController {
	
	@Autowired
	CoffeeIdxService coffeeIdxService;
	
	@RequestMapping(value="/api/index", method=RequestMethod.GET)
	public List<CoffeeIdxDto> openCoffeeIdxList() throws Exception{  //결과를 view에 보내지 않고 조회 결과를 바로 api의 응답 결과로 사용
		return coffeeIdxService.selectCoffeeList();
	}
	
	@RequestMapping(value="/api/index/{cafeName}", method=RequestMethod.GET)
	public CoffeeDetailDto openCoffeeIdxDetail(@PathVariable("cafeName") String cafeName) throws Exception{
		
		String[] nameAry = cafeName.split("\\*"); //이름과 주소로 구분)
		
		return coffeeIdxService.selectCoffeeDetail(nameAry[0], nameAry[1]);
	}
	
	//get은 요청 주소에 파라미터를 같이 보내고 post는 파라미터를 http패킷의 바디에 담아서 전송. @requestbody는 메서드의 파라미터가 반드시 http패킷의 바디에 담겨 있어야 함을 의미.post나 put을 사용하는 메서드에 사용 
}
