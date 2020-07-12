package com.yuansfer.payment.response.auth;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class AuthFreezeResponse extends YuanpayResponse {

	private static final long serialVersionUID = -5719528180888722733L;

	private JSONObject result;
	
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
}
