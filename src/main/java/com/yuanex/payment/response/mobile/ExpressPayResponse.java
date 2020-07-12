package com.yuanex.payment.response.mobile;

import com.yuanex.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class ExpressPayResponse extends YuanpayResponse {

	private static final long serialVersionUID = 1126607299376806813L;
	
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	
}
