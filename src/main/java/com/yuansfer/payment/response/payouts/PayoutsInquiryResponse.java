package com.yuansfer.payment.response.payouts;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class PayoutsInquiryResponse extends YuanpayResponse {

	private static final long serialVersionUID = -8642884531692806921L;
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}
