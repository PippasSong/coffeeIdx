package coffeeIdx.coffeeIdx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import coffeeIdx.coffeeIdx.dto.MemberDto;
import coffeeIdx.coffeeIdx.service.CoffeeIdxService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionAttributes("member") //세션에 상태정보 저장, "member"라는 이름으로 Model에 저장한 데이터는 자동으로 세션에 등록된다
@Controller
public class LoginController {
	
	@Autowired
	private CoffeeIdxService coffeeIdxService;
	
	@RequestMapping(value="/index/login", method=RequestMethod.GET)
	public ModelAndView openLogin() throws Exception {
		ModelAndView mv = new ModelAndView("/coffeeIdx/login");
		return mv;
		
	}
	
	@RequestMapping(value="/index/login", method=RequestMethod.POST)
	public String login(MemberDto member, Model model) throws Exception {
		MemberDto findMember = coffeeIdxService.getMember(member);
		
		if(findMember != null  && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);
			return "redirect:/index/request";
		} else {
			return "redirect:/index/login";
		}
		
	}
	
	@RequestMapping(value="/index/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status) throws Exception {
		status.setComplete(); //세션 강제 종료
		return "redirect:/index/request";
		
		
	}

}
