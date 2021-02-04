package com.yuansfer.payment.response.online;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class ProcessResponse extends YuanpayResponse {

	private static final long serialVersionUID = -2739558423936200878L;
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}
