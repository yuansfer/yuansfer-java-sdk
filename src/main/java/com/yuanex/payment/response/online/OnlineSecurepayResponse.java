package com.yuanex.payment.response.online;

import com.yuanex.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class OnlineSecurepayResponse extends YuanpayResponse {

	private static final long serialVersionUID = 2458591733625822127L;
	
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	
}