package com.yuanex.payment.response;

import java.io.Serializable;

public abstract class YuanpayResponse implements Serializable {

	private static final long serialVersionUID = 3202674589165198263L;
	public static final String SUCCESS_MSG = "success";
	public static final String SUCCESS_CODE = "1";
	public static final String FAIL_CODE = "-1";
	public static final String UNKNOW = "0";
	
	private String retCode;
	private String retMsg;
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
}
