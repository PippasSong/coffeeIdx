package coffeeIdx.coffeeIdx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.mapper.CoffeeIdxMapper;
import coffeeIdx.coffeeIdx.service.CoffeeIdxService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionAttributes("member") //세션에 상태정보 저장, "member"라는 이름으로 Model에 저장한 데이터는 자동으로 세션에 등록된다
@Controller
public class LoginController {
	
	@Autowired
	private CoffeeIdxService coffeeIdxService;
	
	@Autowired
	private CoffeeIdxMapper coffeeIdxMapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@RequestMapping(value="/index/login", method=RequestMethod.GET)
	public ModelAndView openLogin() throws Exception {
		ModelAndView mv = new ModelAndView("/coffeeIdx/login");
		return mv;
		
	}
	
//	@RequestMapping(value="/index/login", method=RequestMethod.POST)
//	public String login(MemberDto member, Model model) throws Exception {
//		MemberDto findMember = coffeeIdxService.getMember(member);
//		
//		if(findMember != null  && findMember.getPassword().equals(member.getPassword())) {
//			model.addAttribute("member", findMember);
//			return "redirect:/index/request";
//		} else {
//			return "redirect:/index/login";
//		}
//		
//	}
	
	@RequestMapping(value="/index/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status) throws Exception {
		status.setComplete(); //세션 강제 종료
		return "redirect:/index/request";
		
		
	}
	
	//시큐리티 권한없음 페이지
	@RequestMapping(value="/index/accessDenied", method=RequestMethod.GET)
	public void accessDenied() throws Exception {
	
	}
	
	@RequestMapping(value="/index/insertMember", method=RequestMethod.GET)
	public ModelAndView memberInsert() throws Exception {
//		MemberDto member = new MemberDto();
//		member.setId("도우너");
//		member.setPassword(encoder.encode("1234"));
//		member.setName("도우너");
//		member.setRole("ROLE_GENERAL");
//		member.setEnabled(true);
//		coffeeIdxMapper.insertMember(member);
		ModelAndView mv = new ModelAndView("/coffeeIdx/signUp");
		return mv;

	}

}
