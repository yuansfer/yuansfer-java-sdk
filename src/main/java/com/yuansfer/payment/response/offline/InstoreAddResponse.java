package com.yuansfer.payment.response.offline;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class InstoreAddResponse extends YuanpayResponse {

	private static final long serialVersionUID = 2458591733625822127L;
	
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

}
