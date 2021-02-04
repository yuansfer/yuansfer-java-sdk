package com.yuansfer.payment.response.payouts;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class PayoutsPayResponse extends YuanpayResponse {

	private static final long serialVersionUID = -5374850524477752149L;
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}
