package com.yuansfer.payment.response.recurring;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class ConsultResponse extends YuanpayResponse {

	private static final long serialVersionUID = 1407583342403492746L;
	
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}
