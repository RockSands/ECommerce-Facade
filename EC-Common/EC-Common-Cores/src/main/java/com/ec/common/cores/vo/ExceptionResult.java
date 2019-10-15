package com.ec.common.cores.vo;

import com.ec.common.cores.exception.ErrorType;

public class ExceptionResult {

	private String code;

	private String message;

	private long timestamp;

	public ExceptionResult(ErrorType em) {
		this.code = em.getCode();
		this.message = em.getMesg();
		this.timestamp = System.currentTimeMillis();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
