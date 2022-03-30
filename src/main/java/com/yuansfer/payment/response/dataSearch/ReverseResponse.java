package com.yuansfer.payment.response.dataSearch;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class ReverseResponse extends YuanpayResponse {

	private static final long serialVersionUID = 4617089503725402504L;

	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

}
