package coffeeIdx.coffeeIdx.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	//500
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAll(final Exception ex){
//		System.out.println(ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}