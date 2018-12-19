package com.ec.common.exception;

import com.ec.common.enums.ExceptionEnum;

import lombok.Getter;

/**
 * 自定义通用异常
 * 
 * @author CKW
 *
 */
@Getter
public class ECException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 异常的Enum
	 */
	private ExceptionEnum exceptionEnum;

	public ECException(ExceptionEnum exceptionEnum) {
		this.exceptionEnum = exceptionEnum;
	}
}
