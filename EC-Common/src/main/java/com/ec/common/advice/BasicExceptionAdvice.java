package com.ec.common.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ec.common.exception.ECException;
import com.ec.common.vo.ExceptionResult;

/**
 * ControllerAdvice,默认所有的Controller被ControllerAdvice拦截
 * 
 * @author CKW
 *
 */
@ControllerAdvice
public class BasicExceptionAdvice {

	@ExceptionHandler(ECException.class)
	public ResponseEntity<ExceptionResult> handleException(ECException e) {
		return ResponseEntity.status(e.getExceptionEnum().value()).body(new ExceptionResult(e.getExceptionEnum()));
	}
}
