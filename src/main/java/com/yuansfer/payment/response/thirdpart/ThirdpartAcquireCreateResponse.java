package com.yuansfer.payment.response.thirdpart;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class ThirdpartAcquireCreateResponse extends YuanpayResponse {
	
	
	private static final long serialVersionUID = -8301339272388381453L;
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	
}
