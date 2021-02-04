package com.yuansfer.payment.response.offline;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class InstorePayResponse extends YuanpayResponse {

	private static final long serialVersionUID = 5387576527153647388L;
	
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	
}
