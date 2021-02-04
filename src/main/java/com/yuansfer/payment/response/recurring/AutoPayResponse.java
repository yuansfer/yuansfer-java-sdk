package com.yuansfer.payment.response.recurring;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class AutoPayResponse extends YuanpayResponse {

	private static final long serialVersionUID = 6783296011807467819L;
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}
