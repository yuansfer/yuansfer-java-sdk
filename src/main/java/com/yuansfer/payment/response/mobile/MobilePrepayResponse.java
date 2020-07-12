package com.yuanex.payment.response.mobile;

import com.yuanex.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class MobilePrepayResponse extends YuanpayResponse {

	private static final long serialVersionUID = 4560755644148624007L;
	
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}
