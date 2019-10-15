package com.ec.common.cores.exception;

import com.ec.common.cores.enums.SystemErrorType;

/**
 * 自定义通用异常
 * 
 * @author CKW
 *
 */
public class ECException extends RuntimeException {
	/**
	 * 异常码
	 */
	private int status = 500;

	/**
	 * 异常对应的错误类型
	 */
	private final ErrorType errorType;

	/**
	 * 默认是系统异常
	 */
	public ECException() {
		this.errorType = SystemErrorType.SYSTEM_ERROR;
	}

	public ECException(ErrorType errorType) {
		this.errorType = errorType;
	}

	public ECException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public ECException(ErrorType errorType, String message, Throwable cause) {
		super(message, cause);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public int getStatus() {
		return status;
	}

	/**
	 * 
	 * 设置对外异常码
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
