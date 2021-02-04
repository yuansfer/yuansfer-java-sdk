package com.yuansfer.payment.response.offline;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class InstoreCashierAddResponse extends YuanpayResponse {

	private static final long serialVersionUID = -7067527616659878204L;
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
}
