package coffeeIdx.coffeeIdx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {
	
	@RequestMapping(value="/index/login", method=RequestMethod.GET)
	public ModelAndView openLogin() {
		ModelAndView mv = new ModelAndView("/coffeeIdx/login");
		return mv;
		
	}

}
