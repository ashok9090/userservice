package com.connectpay.user.secuirity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvisor {
	
	@ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<?> illegalArgumentExceptionHandler(IllegalArgumentException e){
        return ResponseEntity.ok(e.getMessage());
    }

}
