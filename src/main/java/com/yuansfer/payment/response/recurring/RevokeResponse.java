package com.yuansfer.payment.response.recurring;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class RevokeResponse extends YuanpayResponse {

	private static final long serialVersionUID = -4155223422252477939L;
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}
