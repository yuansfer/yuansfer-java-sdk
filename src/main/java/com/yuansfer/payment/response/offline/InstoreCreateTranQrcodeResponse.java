package com.yuansfer.payment.response.offline;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class InstoreCreateTranQrcodeResponse extends YuanpayResponse {

	private static final long serialVersionUID = -6556411072876231793L;
	
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
}
