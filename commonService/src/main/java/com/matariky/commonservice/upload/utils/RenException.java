package com.matariky.commonservice.upload.utils;

/**
 * Custom abnormal
 *
 */
public class RenException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String msg;

	public RenException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public RenException(String msg, String res) {
		this.msg = msg + res;
	}

	public RenException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}