package com.yuansfer.payment.response.auth;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class AuthDetailQueryResponse extends YuanpayResponse {

	private static final long serialVersionUID = -7784810535134344432L;
	private JSONObject result;
	
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
}
