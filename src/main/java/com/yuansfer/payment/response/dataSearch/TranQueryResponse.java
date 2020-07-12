package com.yuansfer.payment.response.dataSearch;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class TranQueryResponse extends YuanpayResponse {

	private static final long serialVersionUID = -6886252858670465096L;
	
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
}	
