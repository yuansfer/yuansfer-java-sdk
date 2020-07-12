package com.yuansfer.payment.response.auth;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class AuthVoucherCreateResponse extends YuanpayResponse {

	private static final long serialVersionUID = -5400307652809910204L;
	private JSONObject result;
	
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
}
