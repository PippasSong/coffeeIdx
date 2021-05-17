package coffeeIdx;

import java.util.List;

import javax.management.relation.Role;

//새로 추가
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import coffeeIdx.coffeeIdx.dto.CoffeeIdxDto;
import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.mapper.CoffeeIdxMapper;
import coffeeIdx.coffeeIdx.service.CoffeeIdxService;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CoffeeIdxApplicationTests {
	
//	@Test
//	public void contextLoads() {
//	}
	@Autowired
	private CoffeeIdxService coffeeIdxService;
	
	@Test
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

}
