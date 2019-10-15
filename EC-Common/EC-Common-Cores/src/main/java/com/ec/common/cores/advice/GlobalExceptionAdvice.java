package com.ec.common.cores.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ec.common.cores.exception.ECException;
import com.ec.common.cores.vo.ExceptionResult;

/**
 * ControllerAdvice,默认所有的Controller被ControllerAdvice拦截
 * 此处主要负责异常情况的拦截,并直接返回错误体
 * @author CKW
 *
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(ECException.class)
	public ResponseEntity<ExceptionResult> handleException(ECException e) {
		return ResponseEntity.status(e.getStatus()).body(new ExceptionResult(e.getErrorType()));
	}
}
