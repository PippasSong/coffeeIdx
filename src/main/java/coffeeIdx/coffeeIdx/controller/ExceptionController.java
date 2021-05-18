package coffeeIdx.coffeeIdx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionController {
	//400
	@ExceptionHandler({AccessDeniedException.class})
	public ModelAndView handleAccessDeniedException(final AccessDeniedException ex) {
		ModelAndView mv = new ModelAndView("/error/error_default");
		mv.addObject("exception", ex);
		
		log.warn("accessDeniedExceptionHandler", ex);
		return mv;
	}
	
	
	
	//500
	@ExceptionHandler({Exception.class})
	public ModelAndView handleAll(final Exception ex){
		ModelAndView mv = new ModelAndView("/error/error_default");
		mv.addObject("exception", ex);
		
		log.error("defaultExceptionHandler", ex);
		
		return mv;
//		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}