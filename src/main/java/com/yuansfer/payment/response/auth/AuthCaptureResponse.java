package com.yuansfer.payment.response.auth;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class AuthCaptureResponse extends YuanpayResponse {

	private static final long serialVersionUID = -1309752795784191292L;

	private JSONObject result;
	
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
}
