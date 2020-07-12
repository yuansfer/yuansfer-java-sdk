package com.yuanex.payment.response.auth;

import com.yuanex.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class AuthUnfreezeResponse extends YuanpayResponse {

	private static final long serialVersionUID = -4471729181528373200L;

	private JSONObject result;
	
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
}
