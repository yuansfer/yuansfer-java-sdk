package com.yuansfer.payment.response.recurring;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class ApplyTokenResponse extends YuanpayResponse {

	private static final long serialVersionUID = -1328250039727652924L;
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}
